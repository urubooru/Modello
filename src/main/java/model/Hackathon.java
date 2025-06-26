package model;

import java.util.ArrayList;
import java.util.Date;

public class Hackathon {
    private String titolo;
    private String sede;
    private Date dataInizio;
    private Date dataFine;
    private Date inizioIscrizioni;
    private Date fineIscrizioni;
    private int maxIscritti;
    private int dimMaxTeam;
    private String problema;
    private ArrayList<Team> teams;
    private ArrayList<Giudice> giudici;
    private Organizzatore organizzatore;
    private Classifica classifica;
    private boolean classificaPubblicata;
    public boolean apriRegistrazioni;

    public Hackathon(String titolo, String sede, Date dataInizio, Date dataFine,
                     Date inizioIscrizioni, Date fineIscrizioni, int maxIscritti,
                     int dimMaxTeam, Organizzatore organizzatore) {
        this.titolo = titolo;
        this.sede = sede;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.inizioIscrizioni = inizioIscrizioni;
        this.fineIscrizioni = fineIscrizioni;
        this.maxIscritti = maxIscritti;
        this.dimMaxTeam = dimMaxTeam;
        this.organizzatore = organizzatore;
        this.teams = new ArrayList<>();
        this.giudici = new ArrayList<>();
        this.classifica = new Classifica(this);
        classificaPubblicata = false;
    }

    public void aggiungiGiudice(Giudice giudice) {
        giudici.add(giudice);
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    // Altri getter...
    public String getTitolo() { return titolo; }

    public Classifica getClassifica() {
        return classifica;
    }

    public String getProblema() {
        return problema;
    }


    public ArrayList<Team> getTeams(){
        return this.teams;
    }

    public void addTeam(Team t){
        if(this.teams.contains(t)) throw new RuntimeException("Team already exists");
        if(t.getMembri().size() > dimMaxTeam) throw new RuntimeException("Il team ha troppi elementi");

        int npart = 0;
        for(Team t2: this.teams){
            npart += t2.getMembri().size();
        }
        if(npart + t.getMembri().size() > maxIscritti) throw new RuntimeException("Troppi iscritti");

        this.teams.add(t);
    }

    public int getMaxIscritti() {
        return maxIscritti;
    }
    public int getDimMaxTeam() {
        return dimMaxTeam;
    }

    public Organizzatore getOrganizzatore() {
        return organizzatore;
    }

    public boolean isClassificaPubblicata() {
        return classificaPubblicata;
    }

    public void pubblicaClassifica() {
        if(classificaPubblicata) throw new RuntimeException("Classifica già pubblicata!");

        classifica = new Classifica(this);
        classificaPubblicata = true;
    }

    public void apriRegistrazioni() {
        Date data = new Date(System.currentTimeMillis());
        if(data.before(inizioIscrizioni) || data.after(fineIscrizioni)) {throw new RuntimeException("Impossibile aprire le registrazioni (troppo presto/tardi)!");}
        if(apriRegistrazioni) { throw new RuntimeException("Registrazioni già aperte!"); }

        this.apriRegistrazioni = true;
    }

    public ArrayList<Giudice> getGiudici() {
        return giudici;
    }
}