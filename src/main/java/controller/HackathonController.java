package controller;

import Model.*;

import java.util.ArrayList;
import java.util.Date;

public class HackathonController {
    private ArrayList<Utente> utenti;
    private ArrayList<Partecipante> partecipanti;
    private ArrayList<Hackathon> hackathons;
    private ArrayList<Team> teams;
    private Utente currentUser;

    public HackathonController() {
        this.utenti = new ArrayList<Utente>();
        this.hackathons = new ArrayList<Hackathon>();
        this.teams = new ArrayList<Team>();
        this.partecipanti = new ArrayList<Partecipante>();

        //TEST
        Utente u = new Utente("1","1","1");
        this.utenti.add(u);
        Utente u2 = new Utente("2","2","2");
        this.utenti.add(u2);
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

    public ArrayList<String> getUserNames() {
        ArrayList<String> ret = new ArrayList<String>();
        for(Utente u : utenti) {
            ret.add(u.getUsername());
        }
        return ret;
    }

    public void creaAggiungiUtente(String email, String user, String pass) {
        Utente u = new Utente(email, user, pass);
        this.addUtente(u);
    }

    public void addUtente(Utente utente) {
        for(Utente u: utenti) {
            if(u.getUsername().equals(utente.getUsername())) {
                throw new RuntimeException("User already exists");
            }
        }

        this.utenti.add(utente);
    }

    public void login(String username, String password) {
        if(username.isEmpty() || password.isEmpty()) { throw new RuntimeException("Username or password are empty"); }

        for(Utente u: utenti) {
            if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
                this.currentUser = u;
                return;
            }
        }

        throw new RuntimeException("Credenziali non valide");
    }

    public String getUsername() {
        return this.currentUser.getUsername();
    }

    public void setPassword(String password) {
        this.currentUser.setPassword(password);
    }

    public void creaTeam(String teamName, Object hackathon) {
        if(teamName.isEmpty() || hackathon.toString().isEmpty()) { throw new RuntimeException("Empty team name or hackathon"); }

        for(Hackathon h : hackathons) {
            if(h.getTitolo().equals(hackathon.toString())) {
                ArrayList<Team> teamDiH = h.getTeams();
                for(Team t: teamDiH) {
                    if(t.getNome().equals(teamName)) {
                        throw new RuntimeException("Team already exists");
                    }
                    for(Partecipante p : t.getMembri()){
                        if(p.getUsername().equals(this.currentUser.getUsername()))
                            throw new RuntimeException("Fai già parte di un team : " + p.getTeam(h).getNome());
                    }
                }
                Team t1 = new Team(teamName, h.getClassifica());
                Partecipante p2 = new Partecipante(this.currentUser);
                p2.setPartecipazione(h, t1);
                t1.aggiungiMembro(p2);
                h.addTeam(t1);
                this.partecipanti.add(p2);
                this.teams.add(t1);
                return;
            }
        }
        throw new RuntimeException("Failed");
    }

    public Utente getUtenteFromUsername(String username) {
        for(Utente u : utenti) {
            if(u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

    public void invita(Object hackathon, Object username) {
        String invitatoUsername = username.toString();
        if(invitatoUsername.isEmpty()) { throw new RuntimeException("Invitato non valido"); }
        if(hackathon.toString().isEmpty()) { throw new RuntimeException("Hackathon non valido"); }

        Partecipante invitato = getOrCreatePartecipante(invitatoUsername);
        Hackathon hack = null;
        //retrieve right hackathon
        for(Hackathon h : hackathons) {
            if(h.getTitolo().equals(hackathon.toString())) {
                hack = h;
                break;
            }
        }

        //We know that the hackathon is not going to be null if its not empty
        assert hack != null;
        for(Team t: hack.getTeams()){
            if(t.getMembri().contains(invitato)) {
                throw new RuntimeException("L'invitato ha già un team!");
            }
        }

        //The user is not participating in any way in the current hackathon
        Partecipante currentUserPartecipante = getOrCreatePartecipante(currentUser.getUsername());
        if(currentUserPartecipante.getTeam(hack) == null){
            throw new RuntimeException("L'utente invitante non ha un team.");
        }
        invitato.addInvito(hack, currentUserPartecipante.getTeam(hack));

    }

    private Partecipante getOrCreatePartecipante(String invitatoUsername) {
        Utente u = getUtenteFromUsername(invitatoUsername);
        for(Partecipante p : partecipanti) {
            if(p.getUsername().equals(u.getUsername())) {
                return p;
            }
        }
        Partecipante p = new Partecipante(u);
        partecipanti.add(p);
        return p;
    }
}
