package controller;

import Model.*;

import javax.swing.*;
import java.util.ArrayList;

public class HackathonController {
    private ArrayList<Utente> utenti;

    public HackathonController() {this.utenti = new ArrayList<Utente>();}

    public void addUtente(Utente utente) {
        for(Utente u: utenti) {
            if(u.getUsername().equals(utente.getUsername())) {
                throw new RuntimeException("User already exists");
            }
        }

        this.utenti.add(utente);
    }

    public boolean login(String username, String password) {
        if(username.isEmpty() || password.isEmpty()) { throw new RuntimeException("Username or password are empty"); }

        for(Utente u: utenti) {
            if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return true;
            }
        }

        throw new RuntimeException("Credenziali non valide");
    }
}
