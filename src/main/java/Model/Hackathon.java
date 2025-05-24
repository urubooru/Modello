package Model;

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
        this.classificaPubblicata = false;
    }

    public void aggiungiGiudice(Giudice giudice) {
        giudici.add(giudice);
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public void pubblicaClassifica(){
        this.classificaPubblicata = true;
    }

    // Altri getter...
    public String getTitolo() { return titolo; }
    public Classifica getClassifica() {
        if(!(this.classificaPubblicata)) throw new RuntimeException("Classifica non pubblicata");
        return classifica;
    }
}