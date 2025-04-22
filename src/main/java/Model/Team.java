package Model;

import java.util.ArrayList;
import java.util.Date;

public class Team {
    private String nome;
    private ArrayList<Partecipante> membri;
    private ArrayList<Documento> documenti;
    private int punteggio;

    public Team(String nome, Partecipante p) {
        this.nome = nome;
        this.membri = new ArrayList<>();
        this.membri.add(p);
        this.documenti = new ArrayList<>();
        this.punteggio = 0;
    }

    public float getPunteggio() {
        int punteggioTot, numeroVoti;
        for (/*Voto v: voti (voti è un arraylist)*/) {
            //punteggioTot += v.getValore();
            numeroVoti++;
        }

        return ((float)punteggioTot /(float)numeroVoti);
    }

    public void pubblicaDocumento(Date data, String descrizione) {
        Documento doc = new Documento(data, descrizione);
        documenti.add(doc);
    }

    public void aggiungiMembro(Partecipante p) {
        membri.add(p);
    }
}