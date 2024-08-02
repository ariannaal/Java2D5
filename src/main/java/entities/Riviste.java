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


}
