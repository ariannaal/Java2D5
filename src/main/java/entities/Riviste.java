package entities;

import enums.Periodicita;

import java.time.LocalDate;

public class Riviste extends Catalogo {

    private Periodicita periodicita;

    public Riviste(String isbn, String titolo, LocalDate annoPubblicazione, int nPagine, Periodicita periodicita) {
        super(isbn, titolo, annoPubblicazione, nPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }
    
}
