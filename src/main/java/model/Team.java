package model;

import java.util.ArrayList;
import java.util.Date;

public class Team {
    private String nome;
    private ArrayList<Partecipante> membri;
    private ArrayList<Documento> documenti;
    private ArrayList<Voto> voti;
    private Hackathon hackathon;
    private Classifica classifica;

    public Team(String nome, Classifica classifica) {
        this.nome = nome;
        this.membri = new ArrayList<>();
        this.documenti = new ArrayList<>();
        this.voti = new ArrayList<>();
        this.classifica = classifica;
        classifica.aggiungiTeam(this);
    }

    public String getNome() {
        return nome;
    }

    public int getPunteggio() {
        if (voti.isEmpty()) return 0;
        int sum = 0;
        for (Voto voto : voti) {
            sum += voto.getValore();
        }
        return sum / voti.size();
    }

    public void iscriviHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

    public void aggiungiMembro(Partecipante partecipante) {
        membri.add(partecipante);
    }

    public ArrayList<Documento> getDocumenti() {
        return new ArrayList<>(documenti);
    }

    public void addDocumento(String text) {
        Documento doc = new Documento(new Date(System.currentTimeMillis()), text, this);
        documenti.add(doc);
    }

    public ArrayList<Partecipante> getMembri() { return membri; }

    public void addMembro(Partecipante partecipante) {
        if(hackathon.getDimMaxTeam() <= this.membri.size()) {
            throw new RuntimeException("Troppi membri nel team!");
        }

        int sum = 0;
        for(Team t : hackathon.getTeams()){
            sum += t.getMembri().size();
        }
        if(hackathon.getMaxIscritti() <= sum) {
            throw new RuntimeException("Troppi partecipanti all'hackathon!");
        }

        this.membri.add(partecipante);
    }

    public void setHackathon(Hackathon h) {
        this.hackathon = h;
    }

    public void addVoto(Voto v) {
        for(Voto v1 : voti){
            //il giudice ha già votato
            if(v1.getGiudice() == v.getGiudice()){
                throw new RuntimeException("Il voto è già stato dato!");
            }
        }

        voti.add(v);
    }
}