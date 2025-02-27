package entities;

public abstract class Catalogo {

    private String isbn;
    private String titolo;
    private int annoPubblicazione;
    private int nPagine;

    public Catalogo(String isbn, String titolo, int annoPubblicazione, int nPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.nPagine = nPagine;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public int getnPagine() {
        return nPagine;
    }


}
