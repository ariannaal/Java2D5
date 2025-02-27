package org.example;

import com.github.javafaker.Faker;
import entities.Catalogo;
import entities.Libri;
import entities.Riviste;
import enums.Periodicita;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {


    public static void main(String[] args) throws IOException {

        File file = new File("src/saveDisco.txt");

        Scanner scanner = new Scanner(System.in);

        Faker faker = new Faker(Locale.ITALY);

        List<Catalogo> archivio = new ArrayList<>();

        for (int i = 0; i < 4; i++) { // creo 4 libri a caso con faker
            Libri libro = new Libri(
                    faker.code().isbn10(),
                    faker.book().title(),
                    faker.number().numberBetween(1900, 2024),
                    faker.number().numberBetween(100, 1000),
                    faker.book().author(),
                    faker.book().genre()
            );
            archivio.add(libro);
        }

        for (int i = 0; i < 4; i++) {
            Riviste rivista = new Riviste(
                    faker.code().isbn13(),
                    faker.book().title(),
                    faker.number().numberBetween(1970, 2024),
                    faker.number().numberBetween(30, 150),
                    Periodicita.values()[faker.number().numberBetween(0, Periodicita.values().length)]
            );
            archivio.add(rivista);
        }

        System.out.println(archivio);


        while (true) {
            System.out.println("Quale operazione vuoi eseguire?");
            System.out.println("Premi 1 per aggiungere un elemento.");
            System.out.println("Premi 2 per rimuovere un elemento dato un codice ISBN.");
            System.out.println("Premi 3 per effettuare una ricerca tramite ISBN.");
            System.out.println("Premi 4 per effettuare una ricerca per anno di pubblicazione");
            System.out.println("Premi 5 per effettuare una ricerca tramite autore");
            System.out.println("Premi 6 per effettuare un salvataggio sul disco dell'archivio.");
            System.out.println("Premi 7 per effettuare un caricamento dal disco dell'archivio in una nuova lista.");
            System.out.println("Premi 0 per chiudere il programma.");

            int nOperazione = scanner.nextInt();
            scanner.nextLine();

            switch (nOperazione) {
                case 1:
                    aggiungiElemento(archivio);
                    break;

                case 2:
                    rimuoviElemento(archivio);
                    break;
                case 3:
                    ricercaISBN(archivio);
                    break;
                case 4:
                    ricercaAnno(archivio);
                    break;
                case 5:
                    ricercaAutore(archivio);
                    break;
                case 6:
                    salvataggioSuDisco(archivio, file);
                    break;
                case 0:
                    System.out.println("Chiudo...");
                    scanner.close();
                    return;

            }

        }

    }

    // aggiunta elemento
    private static void aggiungiElemento(List<Catalogo> archivio) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vuoi aggiungere un libro o una rivista? Scrivi 'libro' per aggiungere un libro o 'rivista' per aggiungere una rivista.");
        String elAggiunto = scanner.nextLine();

        try {
            switch (elAggiunto) {
                case "libro":
                    System.out.println("Inserisci il codice ISBN del libro");
                    String isbnLibro = scanner.nextLine();
                    System.out.println("Inserisci il titolo del libro");
                    String titoloLibro = scanner.nextLine();
                    System.out.println("Inserisci l'anno di pubblicazione del libro");
                    int annoLibro = errAnno();
                    System.out.println("Inserisci lil numero di pagine del libro");
                    int pagineLibro = errPagine();
                    System.out.println("Inserisci l'autore del libro");
                    String autoreLibro = scanner.nextLine();
                    System.out.println("Inserisci il genere del libro");
                    String genereLibro = scanner.nextLine();

                    Libri libro = new Libri(isbnLibro, titoloLibro, annoLibro, pagineLibro, autoreLibro, genereLibro);
                    archivio.add(libro);
                    System.out.println("Il libro " + titoloLibro + " e' stata aggiunta all'archivio.");
                    break;

                case "rivista":
                    System.out.println("Inserisci il codice ISBN della rivista");
                    String isbnRivista = scanner.nextLine();
                    System.out.println("Inserisci il titolo della rivista");
                    String titoloRivista = scanner.nextLine();
                    System.out.println("Inserisci l'anno di pubblicazione della rivista");
                    int annoRivista = errAnno();
                    System.out.println("Inserisci lil numero di pagine della rivista");
                    int pagineRivista = errPagine();
                    System.out.println("Inserisci l'autore della rivista");
                    System.out.println("Inserisci la periodicità della rivista (SETTIMANALE, MENSILE, SEMESTRALE)");
                    String periodicitaRivistaInput = scanner.nextLine();
                    Periodicita periodicitaRivista = Periodicita.valueOf(periodicitaRivistaInput.toUpperCase());// prende il valore di input e lo trasforma in enum

                    Riviste rivista = new Riviste(isbnRivista, titoloRivista, annoRivista, pagineRivista, periodicitaRivista);
                    archivio.add(rivista);
                    System.out.println("La rivista " + titoloRivista + " e' stata aggiunta all'archivio.");
                    break;

                default:
                    System.out.println("Elemento inserito nonm valido. Inserire 'libro' o 'rivista'.");

            }

        } catch (Exception e) {
            System.out.println("Errore durante l'inserimento dell'elemento " + e.getMessage());

        }

    }

    // rimuopvi elemento
    private static void rimuoviElemento(List<Catalogo> archivio) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci il codice ISBN dell'elemento da rimuovere:");
        String isbn = scanner.nextLine();

        // verifico prima che l'elemento esista
        boolean elEsiste = archivio.stream()
                .anyMatch(catalogo -> catalogo.getIsbn()
                        .equals(isbn));

        if (elEsiste) {
            // rimuove l'elemento dall'archivio
            archivio.removeIf(catalogo -> catalogo.getIsbn().equals(isbn));
            System.out.println("L'elemento con ISBN " + isbn + " è stato rimosso dall'archivio.");
        } else {
            System.out.println("Nessun elemento trovato con ISBN " + isbn + ".");
        }

    }

    // ricerca con isbn
    private static void ricercaISBN(List<Catalogo> archivio) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci il codice ISBN dell'elemento da cercare:");
        String isbnInserito = scanner.nextLine();

        try {
            Catalogo elTrovato = archivio.stream()
                    .filter(catalogo -> catalogo.getIsbn()
                            .equals(isbnInserito))
                    .findFirst()
                    .orElse(null);

            if (elTrovato instanceof Libri) {
                Libri libroTrovato = (Libri) elTrovato;
                System.out.print("Libro trovato. Autore: " + libroTrovato.getAutore() + ", titolo: " + libroTrovato.getTitolo());
                return;
            }

            if (elTrovato instanceof Riviste) {
                Riviste rivistaTrovata = (Riviste) elTrovato;
                System.out.print("Rivista trovato. Titolo: " + rivistaTrovata.getTitolo());
                return;
            }

            System.out.println("Nessun elemento trovato nel catalogo con codice ISBN " + isbnInserito);

        } catch (NumberFormatException e) {
            System.out.println("Codice ISBN inserito non valido.");

        }

    }

    // ricerca per anno
    private static void ricercaAnno(List<Catalogo> archivio) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci l'anno di pubblicazione degli elementi da cercare:");
        int annoInserito = scanner.nextInt();


        List<Catalogo> elTrovati = archivio.stream()
                .filter(catalogo -> catalogo.getAnnoPubblicazione() == annoInserito)
                .toList();

        if (elTrovati instanceof Libri) {
            Libri libriTrovati = (Libri) elTrovati;
            System.out.print("Libri con anno di pubblicazione " + libriTrovati.getAnnoPubblicazione() + " trovati. Autore: " + libriTrovati.getAutore() + ", titolo: " + libriTrovati.getTitolo());
        }

        if (elTrovati instanceof Riviste) {
            Riviste rivisteTrovate = (Riviste) elTrovati;
            System.out.print("Libri con anno di pubblicazione " + rivisteTrovate.getAnnoPubblicazione() + " trovati. Titolo: " + rivisteTrovate.getTitolo());
        } else {
            System.out.println("Nessun libro/rivista trovato con anno di pubblicazione " + annoInserito);

        }

    }

    // ricerca per autore
    private static void ricercaAutore(List<Catalogo> archivio) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Inserisci l'autore degli elementi da cercare:");
        String autoreInserito = scanner.nextLine();

        try {
            List<Libri> libriTrovati = archivio.stream()
                    .filter(catalogo -> catalogo instanceof Libri)
                    .map(catalogo -> (Libri) catalogo)
                    .filter(libro -> libro.getAutore().equals(autoreInserito))
                    .collect(Collectors.toList());

            if (libriTrovati.isEmpty()) {
                System.out.println("Nessun libro trovato per l'autore " + autoreInserito);
            } else {
                libriTrovati.forEach(libro -> {
                    System.out.println("Libro trovato. Titolo: " + libro.getTitolo() + ", anno di Pubblicazione: " + libro.getAnnoPubblicazione());
                });
            }
        } catch (Exception e) {
            System.out.println("Errore durante la ricerca: " + e.getMessage());
        }
    }

    private static Periodicita errPeriodicita() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String input = scanner.nextLine().toUpperCase();
                return Periodicita.valueOf(input);
            } catch (IllegalArgumentException e) {
                System.out.println("Periodicita' non valida. Inserisci una delle seguenti opzioni: SETTIMANALE, MENSILE, SEMESTRALE.");
            }
        }
    }

    // salvataggio sul disco
    public static void salvataggioSuDisco(List<Catalogo> archivio, File file) throws IOException {

        StringBuilder stringa = new StringBuilder();

        for (Catalogo catalogo : archivio) {
            stringa.append(catalogo.getIsbn()).append(",")
                    .append(catalogo.getTitolo()).append(",")
                    .append(catalogo.getAnnoPubblicazione()).append(",")
                    .append(catalogo.getnPagine()).append(",");

            if (catalogo instanceof Libri) {
                Libri libro = (Libri) catalogo;
                stringa.append("libro,")
                        .append(libro.getAutore()).append(",")
                        .append(libro.getGenere()).append("\n");
            } else if (catalogo instanceof Riviste) {
                Riviste rivista = (Riviste) catalogo;
                stringa.append("rivista,")
                        .append(rivista.getPeriodicita()).append("\n");
            }
        }

        FileUtils.writeStringToFile(file, stringa.toString(), StandardCharsets.UTF_8);
        System.out.println("Elementi del catalogo aggiunti a " + file.getAbsolutePath());

    }

    private static int errPagine() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value <= 0) {
                    throw new NumberFormatException("Il numero di pagine deve essere maggiore di zero.");
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Input non valido per il numero di pagine. Inserisci un valore positivo.");
            }
        }
    }

    private static int errAnno() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                int year = Integer.parseInt(scanner.nextLine());
                if (year < 1700 || year > 2024) {
                    throw new NumberFormatException("L'anno deve essere composto da 4 cifre.");
                }
                return year;
            } catch (NumberFormatException e) {
                System.out.println("Input non valido per l'anno. Inserisci un anno valido.");
            }
        }
    }

}



