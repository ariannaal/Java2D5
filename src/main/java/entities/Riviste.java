package entities;

import java.time.LocalDate;

public class Riviste extends Catalogo {

    public Riviste(String isbn, String titolo, LocalDate annoPubblicazione, int nPagine) {
        super(isbn, titolo, annoPubblicazione, nPagine);
    }


}
