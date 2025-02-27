package entities;

import enums.Periodicita;

public class Riviste extends Catalogo {

    private Periodicita periodicita;

    public Riviste(String isbn, String titolo, int annoPubblicazione, int nPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, nPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    @Override
    public String toString() {
        return "Riviste{" +
                ", titolo='" + getTitolo() + '\'' +
                ", anno pubblicazione='" + getAnnoPubblicazione() + '\'' +
                ", numero di pagine='" + getnPagine() + '\'' +
                ", codice ISBN='" + getIsbn() + '\'' +
                "periodicita=" + periodicita +
                '}';
    }
}
