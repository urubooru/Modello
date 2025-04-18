package Model;

import java.util.ArrayList;
import java.util.Date;

public class Hackathon {
    private String titolo;
    private String sede;
    private Date dataInizio;
    private Date dataFine;
    private Date dataInizioIscrizioni;
    private Date dataFineIscrizioni;
    private int maxIscritti;
    private int dimMaxTeam;
    private ArrayList<Team> teams;
    private Classifica classifica;

    public Hackathon(String titolo, String sede, Date dataInizio, Date dataFine,
                     Date dataInizioIscrizioni, Date dataFineIscrizioni,
                     int maxIscritti, int dimMaxTeam) {
        this.titolo = titolo;
        this.sede = sede;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.dataInizioIscrizioni = dataInizioIscrizioni;
        this.dataFineIscrizioni = dataFineIscrizioni;
        this.maxIscritti = maxIscritti;
        this.dimMaxTeam = dimMaxTeam;
        this.teams = new ArrayList<>();
        this.classifica = new Classifica();
    }

    public String getTitolo() { return titolo; }
    public String getSede() { return sede; }
    public String getDate() { return "Data inizio: " + dataInizio + " Data fine: " + dataFine; }
    public String getDateIscrizioni() { return "Data inizio iscrizioni: " + dataInizioIscrizioni +
            " Data fine iscrizioni: " + dataFineIscrizioni; }
    public int getMaxIscritti() { return maxIscritti; }
    public int getDimMaxTeam() { return dimMaxTeam; }
    public Classifica getClassifica() { return classifica; }
}