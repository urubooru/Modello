package Model;

import java.util.Date;

public class Commento {
    private String testo;
    private Giudice autore;
    private Date data;

    public Commento(String testo, Giudice autore, Date data) {
        this.testo = testo;
        this.autore = autore;
        this.data = data;
    }

    public String getCommento() {
        return testo;
    }

    public Giudice getAutore() {
        return autore;
    }

    public Date getData() {
        return data;
    }
}