package entities;

public class Libri extends Catalogo {

    private String autore;
    private String genere;

    public Libri(String isbn, String titolo, int annoPubblicazione, int nPagine, String autore, String genere) {
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

    @Override
    public String toString() {
        return "Libri{" +
                "autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                ", titolo='" + getTitolo() + '\'' +
                ", anno pubblicazione='" + getAnnoPubblicazione() + '\'' +
                ", numero di pagine='" + getnPagine() + '\'' +
                ", codice ISBN='" + getIsbn() + '\'' +
                '}';
    }
}
