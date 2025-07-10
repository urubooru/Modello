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
        for(Commento c : commenti) {
            if(commento.getGiudice().getUsername().equals(c.getGiudice().getUsername())) {
                throw new RuntimeException("Il giudice ha già commentato");
            }
        }

        commenti.add(commento);
    }

    public ArrayList<Commento> getCommenti() {
        return commenti;
    }

    public String getDescrizione() { return descrizione; }

    public Date getData() {
        return this.data;
    }

    public Team getTeam() {
        return team;
    }
}