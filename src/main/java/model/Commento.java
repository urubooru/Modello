package model;

import java.util.Date;

public class Commento {
    private String testo;
    private Giudice giudice;
    private Documento documento;
    private Date data;

    public Commento(String testo, Giudice giudice, Documento documento) {
        this.testo = testo;
        this.giudice = giudice;
        this.documento = documento;
        this.data = new Date(System.currentTimeMillis());
    }

    public String getTesto() {
        return testo;
    }

    public Giudice getGiudice() {
        return giudice;
    }

    public Documento getDocumento() {
        return documento;
    }
}