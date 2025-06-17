package model;

import java.util.ArrayList;
import java.util.Date;

public class Documento {
    private Date data;
    private String descrizione;
    private Team team;
    private ArrayList<Commento> commenti;

    public Documento(Date data, String descrizione, Team team) {
        this.data = data;
        this.descrizione = descrizione;
        this.team = team;
        this.commenti = new ArrayList<>();
        team.getDocumenti().add(this);
    }

    public void aggiungiCommento(Commento commento) {
        commenti.add(commento);
    }

    public ArrayList<Commento> getCommenti() {
        return new ArrayList<>(commenti);
    }

    public String getDescrizione() {
        return descrizione;
    }
}