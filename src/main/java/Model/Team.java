package Model;

import java.util.ArrayList;

public class Team {
    private String nome;
    private ArrayList<Partecipante> membri;
    private ArrayList<Documento> documenti;
    private ArrayList<Voto> voti;
    private Classifica classifica;

    public Team(String nome, Classifica classifica) {
        this.nome = nome;
        this.membri = new ArrayList<>();
        this.documenti = new ArrayList<>();
        this.voti = new ArrayList<>();
        this.classifica = classifica;
        classifica.aggiungiTeam(this);
    }

    public int getPunteggio() {
        if (voti.isEmpty()) return 0;
        int sum = 0;
        for (Voto voto : voti) {
            sum += voto.getValore();
        }
        return sum / voti.size();
    }

    public void aggiungiMembro(Partecipante partecipante) {
        membri.add(partecipante);
        partecipante.setTeam(this);
    }

    public ArrayList<Documento> getDocumenti() {
        return new ArrayList<>(documenti);
    }
}