package entities;

import java.time.LocalDate;

public class Libri extends Catalogo {

    private String autore;
    private String genere;

    public Libri(String isbn, String titolo, LocalDate annoPubblicazione, int nPagine, String autore, String genere) {
        super(isbn, titolo, annoPubblicazione, nPagine);
        this.autore = autore;
        this.genere = genere;

    }

    public String getAutore() {
        return autore;
    }

    public String getGenere() {
        return genere;
    }
}
