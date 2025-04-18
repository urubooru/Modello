package Model;

import java.util.ArrayList;
import java.util.Date;

public class Team {
    private String nome;
    private ArrayList<Partecipante> membri;
    private ArrayList<Documento> documenti;
    private ArrayList<Commento> commenti;
    private int punteggio;

    public Team(String nome) {
        this.nome = nome;
        this.membri = new ArrayList<>();
        this.documenti = new ArrayList<>();
        this.commenti = new ArrayList<>();
        this.punteggio = 0;
    }

    public void pubblicaDocumento(Date data, String descrizione) {
        Documento doc = new Documento(data, descrizione);
        documenti.add(doc);
    }

    public ArrayList<Commento> getCommenti() {
        return commenti;
    }

    public void aggiungiCommento(Commento commento) {
        commenti.add(commento);
    }

    public void aggiungiMembro(Partecipante p) {
        membri.add(p);
    }
}