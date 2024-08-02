package org.example;

import entities.Catalogo;
import entities.Libri;
import entities.Riviste;
import enums.Periodicita;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Catalogo> archivio = new ArrayList<>();

        while (true) {
            System.out.println("Quale operazione vuoi eseguire?");
            System.out.println("Premi 1 per aggiungere un elemento.");
            System.out.println("Premi 2 per rimuovere un elemento dato un codice ISBN.");
            System.out.println("Premi 3 per effettuare una ricerca tramite ISBN.");
            System.out.println("Premi 4 per effettuare una ricerca per anno di pubblicazione");
            System.out.println("Premi 5 per effettuare una ricerca tramite autore");
            System.out.println("Premi 6 per effettuare un salvataggio sul disco dell'archivio.");
            System.out.println("Premi 7 per effettuare un caricamento dal disco dell'archivio in una nuova lista.");

            int nOperazione = scanner.nextInt();
            scanner.nextLine();

            switch (nOperazione) {
                case 1:
                    aggiungiElemento(archivio);
                    break;

                case 2:
                    rimuoviElemento(archivio);


            }

        }

    }

    // metodi

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



