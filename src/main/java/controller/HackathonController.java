package controller;

import Model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Date;

public class HackathonController {
    private ArrayList<Utente> utenti;
    private ArrayList<Hackathon> hackathons;

    public HackathonController() {
        this.utenti = new ArrayList<Utente>();
        this.hackathons = new ArrayList<Hackathon>();

        //TEST
        Utente u = new Utente("1","1","1");
        this.utenti.add(u);
        Organizzatore Org1 = new Organizzatore(u);
        Date startDate = new Date(2024,12,20);
        Date endDate = new Date(2024,12,30);
        Date inizioIscrizioni = new Date(2024,12,21);
        Date fineIscrizioni = new Date(2024,12,28);
        this.hackathons.add(new Hackathon("Prova1","Napoli", startDate, endDate, inizioIscrizioni, fineIscrizioni,20,5, Org1));
    }

    public ArrayList<Hackathon> getHackathons() {
        return hackathons;
    }
    public ArrayList<String> getTitoloHackathons(ArrayList<Hackathon> hackathons) {
        ArrayList<String> retValue = new ArrayList<String>();
        for(Hackathon h : hackathons) {
            retValue.add(h.getTitolo());
        }
        return retValue;
    }
    public Classifica getClassifica(String Hackathon) {
        for(Hackathon h : hackathons) {
            if(h.getTitolo().equals(Hackathon)) {
                return h.getClassifica();
            }
        }
        return null;
    }
    public ArrayList<String> getTeamClassifica(Classifica c) {
        ArrayList<String> str = new ArrayList<String>();
        ArrayList<Team> teams = c.getClassifica();
        for(Team t : teams) {
            str.add(t.getNome());
        }
        return str;
    }
    public ArrayList<Integer> getVotiClassifica(Classifica c) {
        ArrayList<Integer> voti = new ArrayList<Integer>();
        ArrayList<Team> teams = c.getClassifica();
        for(Team t : teams) {
            voti.add(t.getPunteggio());
        }
        return voti;
    }

    public void addUtente(Utente utente) {
        for(Utente u: utenti) {
            if(u.getUsername().equals(utente.getUsername())) {
                throw new RuntimeException("User already exists");
            }
        }

        this.utenti.add(utente);
    }

    public Utente login(String username, String password) {
        if(username.isEmpty() || password.isEmpty()) { throw new RuntimeException("Username or password are empty"); }

        for(Utente u: utenti) {
            if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }

        throw new RuntimeException("Credenziali non valide");
    }
}
