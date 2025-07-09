package controller;

import dao.DAO;
import daoImplementation.DAOImplementation;
import model.*;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class HackathonController {
    private DAO dao;

    private ArrayList<Organizzatore> organizzatori;
    private ArrayList<Utente> utenti;
    private ArrayList<Partecipante> partecipanti;
    private ArrayList<Giudice> giudici;
    private ArrayList<Hackathon> hackathons;
    private ArrayList<Team> teams;
    private Utente currentUser;

    public HackathonController() throws SQLException {
        this.organizzatori = new ArrayList<>(); //input from db done
        this.utenti = new ArrayList<Utente>(); //input from db done
        this.hackathons = new ArrayList<Hackathon>(); //input from db done
        this.teams = new ArrayList<Team>();
        this.partecipanti = new ArrayList<Partecipante>();
        this.giudici = new ArrayList<Giudice>();

//      commento
//      documento
//      giudice
//      invito
//      partecipante
//      team
//      voto

        this.dao = new DAOImplementation();

        ArrayList<String> mails = new ArrayList<>(), users = new ArrayList<>(), passwords = new ArrayList<>();
        dao.retrieveUsers(mails, users, passwords);
        int i;
        for(i = 0; i < mails.size(); i++){
            Utente u = new Utente(mails.get(i), users.get(i), passwords.get(i));
            this.utenti.add(u);
        }
        //pulizia per garbage collection
        mails = passwords = users = null;

        users = new ArrayList<>();
        dao.retrieveOrganizzatori(users);
        for(i = 0; i < users.size(); i++){
            Utente u = getUtenteFromUsername(users.get(i));
            Organizzatore o = new Organizzatore(u);
            this.organizzatori.add(o);
        }
        users = null;

        ArrayList<String> titles = new ArrayList<>(), sedi = new ArrayList<>(), problemi = new ArrayList<>(), organizzatoriH = new ArrayList<>();
        ArrayList<Date> dateInizi = new ArrayList<>(), dateFini = new ArrayList<>(), iniziIscr = new ArrayList<>(), finiIscr = new ArrayList<>();
        ArrayList<Integer> maxIscr = new ArrayList<>(), maxTeam = new ArrayList<>();
        ArrayList<Boolean> clasP = new ArrayList<>(), regOpen = new ArrayList<>();
        dao.retrieveHackathons(titles, sedi, problemi, organizzatoriH, dateInizi, dateFini, iniziIscr, finiIscr, maxIscr, maxTeam, clasP, regOpen);
        for(i = 0; i < titles.size(); i++){
            //Cerchiamo l'organizzatore basandoci sull'username dalla tabella SQL
            Organizzatore o = null;

            for(int j = 0; j < organizzatori.size(); j++){
                if(organizzatoriH.get(i).equals(organizzatori.get(j).getUsername())){
                    o = organizzatori.get(j);
                    break;
                }
            }

            Hackathon h = new Hackathon(titles.get(i), sedi.get(i), dateInizi.get(i), dateFini.get(i), iniziIscr.get(i), finiIscr.get(i),
                    maxIscr.get(i), maxTeam.get(i), o);
            if(clasP.get(i)){
                h.pubblicaClassifica();
            }
            if(regOpen.get(i)){
                h.apriRegistrazioni();
            }

            this.hackathons.add(h);
        }
        titles = sedi = problemi = organizzatoriH = null;
        dateInizi = dateFini = iniziIscr = finiIscr = null;
        maxIscr = maxTeam = null; clasP = regOpen = null;
        //prima di poter effettivamente scrivere gli hackathon in memoria
        //a livello implementativo non abbiamo ancora ne i teams ne i giudici ne la classifica(che dipende dai team)

        users = new ArrayList<>();
        ArrayList<String> teamPartecipante = new ArrayList<>();
        dao.retrievePartecipanti(users, teamPartecipante);
        //per poter aggiungere i partecipanti necessitiamo sia dei team, che degli inviti


        ArrayList<String> teamNames = new ArrayList<>(), hackathonTeam = new ArrayList<>();
        dao.retrieveTeams(teamNames, hackathonTeam);

        ArrayList<String> teamInvito = new ArrayList<>(), hackathonInvito = new ArrayList<>(), invitato = new ArrayList<>();
        dao.retrieveInviti(teamInvito, hackathonInvito, invitato);

        //Iniziamo a popolare i partecipanti


        /*
        //TEST PRE DAO
        Utente u = new Utente("1","1","1");
        this.utenti.add(u);
        Utente u2 = new Utente("2","2","2");
        this.utenti.add(u2);
        Organizzatore Org1 = new Organizzatore(u);
        Date startDate = new Date(2024-1900,12-1,20);
        //NOTA : Date gestisce una data partendo dal 1900 quindi se si vuole inserire il
        //2026 dobbiamo togliere 1900 anni dagli anni + i mesi partono da 0
        Date endDate = new Date(2026-1900,12-1,30);
        Date inizioIscrizioni = new Date(2024-1900,12-1,21);
        Date fineIscrizioni = new Date(2026-1900,12-1,28);
        this.hackathons.add(new Hackathon("Prova1","Napoli", startDate, endDate, inizioIscrizioni, fineIscrizioni,20,5, Org1));
        */
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

    //PROBLEMA : NON CAPISCO PERCHÉ MA FA SEMPRE IL THROW RUNTIMEEXCEPTION
    //anche se non è stato cambiato nulla dall'ultimo commit
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
                t1.setHackathon(h);
                Partecipante p2 = getOrCreatePartecipante(this.currentUser.getUsername());
                p2.setPartecipazione(h, t1);
                t1.aggiungiMembro(p2);

                //THIS ALWAYS GIVES AN EXCEPTION, CAN'T EXACTLY FIGURE OUT WHY
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
        //Retrieve right hackathon
        for(Hackathon h : hackathons) {
            if(h.getTitolo().equals(hackathon.toString())) {
                hack = h;
                break;
            }
        }

        //Check if he is a judge
        for(Giudice g : hack.getGiudici()){
            if(g.getUsername().equals(invitatoUsername)) {
                throw new RuntimeException("L'utente è un giudice!");
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

    public void aggiungiDocumento(Object h, String text){
        if(h == null){ throw new RuntimeException("Hackathon non valido"); }
        if(text.isEmpty()){ throw new RuntimeException("Documento non valido"); }

        Team team = null;

        for(Hackathon hackathon : hackathons) {
            if(hackathon.getTitolo().equals(h)) {
                Partecipante p = getOrCreatePartecipante(currentUser.getUsername());
                team = p.getTeam(hackathon);
                break;
            }
        }

        if(team == null){
            throw new RuntimeException("Errore l209");
        }

        team.addDocumento(text);
    }

    public ArrayList<Invito> getInviti() {
        Partecipante currentPartecipante = getOrCreatePartecipante(currentUser.getUsername());
        ArrayList<Invito> one = currentPartecipante.getInviti();
        Giudice currentGiudice = getOrCreateGiudice(currentUser.getUsername());
        ArrayList<Invito> two = currentGiudice.getInviti();
        ArrayList<Invito> three = new ArrayList<Invito>();
        three.addAll(one);
        three.addAll(two);
        return three;
    }

    //1 : from partecipante ; 2 : from giudice
    private int checkInvito(String hackathonName, String teamName) {
        if(teamName.equals("GIUDICE")){
            Giudice g = getOrCreateGiudice(currentUser.getUsername());
            for(Invito i : g.getInviti()) {
                if(i.getHackathon().getTitolo().equals(hackathonName) && i.getTeam()==null && teamName.equals("GIUDICE")){
                    return 2;
                }
            }
        }

        else {
            Partecipante p = getOrCreatePartecipante(currentUser.getUsername());
            for (Invito i : p.getInviti()) {
                if (i.getHackathon().getTitolo().equals(hackathonName) && i.getTeam() != null && i.getTeam().getNome().equals(teamName)) {
                    return 1;
                }
            }
        }



        return 0;
    }

    public void rifiutaInvito(Object hackathon, Object team) {
        String hackathonName = hackathon.toString();
        String teamName = team.toString();

        int checkInvito = checkInvito(hackathonName, teamName);
        if(checkInvito == 0){ throw new RuntimeException("L'invito non esiste."); }

        if(checkInvito == 1) {
            Partecipante p = getOrCreatePartecipante(currentUser.getUsername());
            p.rifiutaInvito(hackathonName, teamName);
            return;
        }

        if(checkInvito == 2) {
            Giudice g = getOrCreateGiudice(currentUser.getUsername());
            g.rifiutaInvito(hackathonName, teamName);
        }
    }

    public void accettaInvito(Object hackathon, Object team) {
        String hackathonName = hackathon.toString();
        String teamName = team.toString();

        int checkInvito = checkInvito(hackathonName, teamName);

        if(checkInvito == 0){ throw new RuntimeException("L'invito non esiste."); }

        if(checkInvito == 1) {
            //check if the person is already a judge
            for(Hackathon h : hackathons) {
                if(h.getTitolo().equals(hackathonName)){
                    for(Giudice g : h.getGiudici()) {
                        if(g.getUsername().equals(currentUser.getUsername())){
                            throw new RuntimeException("L'utente è già un giudice.");
                        }
                    }
                }
            }

            Partecipante p = getOrCreatePartecipante(currentUser.getUsername());
            p.accettaInvito(hackathonName, teamName);
            return;
        }

        if(checkInvito == 2) {
            for(Hackathon h : hackathons) {
                if(h.getTitolo().equals(hackathonName)){
                    for(Team t : h.getTeams()) {
                        for(Partecipante p : t.getMembri()) {
                            if(p.getUsername().equals(currentUser.getUsername())) {
                                throw new RuntimeException("L'utente è già un partecipante");
                            }
                        }
                    }
                }
            }
            Giudice g = getOrCreateGiudice(currentUser.getUsername());
            g.accettaInvito(hackathonName, teamName);
            //ADD GIUDICE TO HACKATHON
            for(Hackathon h : hackathons) {
                if(h.getTitolo().equals(hackathonName)) {
                    h.aggiungiGiudice(g);
                }
            }
        }
    }

    public void populateHackathonBoxOrganizzatore(JComboBox hackComboBox) {
        for(Hackathon h : hackathons) {
            if(h.getOrganizzatore().getUsername().equals(currentUser.getUsername())) {
                hackComboBox.addItem(h.getTitolo());
            }
        }
    }

    public void publishRankings(Object obj) {
        if (obj == null){ throw new RuntimeException("Hackathon non valido"); }

        String hackName = obj.toString();

        if(hackName == null || hackName.isEmpty()){ throw new RuntimeException("Hackathon non valido"); }

        for(Hackathon h : hackathons) {
            if(h.getTitolo().equals(hackName)) {
                h.pubblicaClassifica();
                return;
            }
        }
    }

    public void openRegistration(Object obj) {
        if (obj == null){ throw new RuntimeException("Hackathon non valido"); }

        String hackName = obj.toString();

        if(hackName == null || hackName.isEmpty()){ throw new RuntimeException("Hackathon non valido"); }

        for(Hackathon h : hackathons){
            if(h.getTitolo().equals(hackName)){
                h.apriRegistrazioni();
            }
        }
    }

    public void invitaGiudice(Object hackathon, Object username) {
        String invitatoUsername = username.toString();
        if(invitatoUsername.isEmpty()) { throw new RuntimeException("Invitato non valido"); }
        if(hackathon.toString().isEmpty()) { throw new RuntimeException("Hackathon non valido"); }

        Giudice invitato = getOrCreateGiudice(invitatoUsername);
        Hackathon hack = null;
        //Retrieve right hackathon
        for(Hackathon h : hackathons) {
            if(h.getTitolo().equals(hackathon.toString())) {
                hack = h;
                break;
            }
        }

        //We know that the hackathon is not going to be null if it's not empty
        assert hack != null;
        for(Team t: hack.getTeams()){
            if(t.getMembri().contains(invitato)) {
                throw new RuntimeException("L'invitato partecipa all'hackathon!");
            }
        }

        //Is the user a judge already?
        for(Giudice g : hack.getGiudici()){
            if(g.getUsername().equals(invitatoUsername)) {
                throw new RuntimeException("L'invitato è già un giudice!");
            }
        }

        invitato.addInvito(hack, null);
    }

    private Giudice getOrCreateGiudice(String invitatoUsername) {
        Utente u = getUtenteFromUsername(invitatoUsername);
        for(Giudice g : giudici) {
            if(g.getUsername().equals(u.getUsername())) {
                return g;
            }
        }
        Giudice g = new Giudice(u);
        giudici.add(g);
        return g;
    }

    public void populateHackathonBoxGiudice(JComboBox hackComboBox) {
        for(Hackathon h : hackathons) {
            for(Giudice g : h.getGiudici()) {
                if(g.getUsername().equals(currentUser.getUsername())) {
                    hackComboBox.addItem(h.getTitolo());
                    break;
                }
            }
        }
    }

    public String getProblema(Object hackathon) {
        if(hackathon == null || hackathon.toString().isEmpty()) { throw new RuntimeException("Hackathon non valido"); }

        for(Hackathon h : hackathons) {
            if(h.getTitolo().equals(hackathon.toString())) {
                return h.getProblema();
            }
        }

        return "";
    }

    public void setProblema(Object hackathon, String problema) {
        if(hackathon == null || hackathon.toString().isEmpty()) { throw new RuntimeException("Hackathon non valido"); }

        for(Hackathon h: hackathons) {
            if(h.getTitolo().equals(hackathon.toString())) {
                h.setProblema(problema);
            }
        }
    }

    public void populateTeamComboBox(Object hackathon, JComboBox teamComboBox) {
        teamComboBox.removeAllItems();
        if(hackathon == null || hackathon.toString().isEmpty()) { throw new RuntimeException("Hackathon non valido"); }

        for(Hackathon h : hackathons) {
            if(h.getTitolo().equals(hackathon.toString())) {
                for(Team t : h.getTeams()) {
                    teamComboBox.addItem(t.getNome());
                }
                break;
            }
        }
    }

    public void votaTeam(Object hackathon, Object team, String vote) {
        if(hackathon == null || hackathon.toString().isEmpty()) { throw new RuntimeException("Hackathon non valido"); }
        if(team== null || team.toString().isEmpty()) { throw new RuntimeException("Team non valido"); }

        int voto = 0;
        try{
            voto = Integer.parseInt(vote);
        } catch(NumberFormatException ex){
            throw new RuntimeException("Il voto inserito non è parsabile come int!");
        }

        for(Hackathon h : hackathons) {
            if(h.getTitolo().equals(hackathon.toString())) {
                for(Team t : h.getTeams()) {
                    if(t.getNome().equals(team.toString())) {
                        Giudice g = getOrCreateGiudice(currentUser.getUsername());
                        Voto v = new Voto(t, g, voto);
                        t.addVoto(v);
                        break;
                    }
                }
                break;
            }
        }
    }

    public void populateDataComboBox(JComboBox dataComboBox, String team, String hackathon) {
        dataComboBox.addItem("");
        for (Hackathon h : hackathons) {
            if (h.getTitolo().equals(hackathon.toString())) {
                for (Team t : h.getTeams()) {
                    if (t.getNome().equals(team)) {
                        for (Documento d : t.getDocumenti()) {
                            dataComboBox.addItem(d.getData().toString());
                        }
                        break;
                    }
                }

                break;
            }
        }
    }

    public String getDescDoc(Object data, String team, String hackathon) {
        if(data == null || data.toString().isEmpty()) { return ""; }

        for (Hackathon h : hackathons) {
            if (h.getTitolo().equals(hackathon.toString())) {
                for (Team t : h.getTeams()) {
                    if (t.getNome().equals(team)) {
                        for (Documento d : t.getDocumenti()) {
                            if(d.getData().toString().equals(data.toString())) {
                                return d.getDescrizione();
                            }
                        }
                    }
                }
            }
        }

        return "";
    }

    public void addCommento(String commento, Object data, String hackathonName, String teamName) {
        if(commento.isEmpty()){ throw new RuntimeException("Commento vuoto"); }
        if(data == null || data.toString().isEmpty()) { throw new RuntimeException("Data non valida"); }
        for(Hackathon h : hackathons) {
            if(h.getTitolo().equals(hackathonName.toString())) {
                for(Team t : h.getTeams()) {
                    if(t.getNome().equals(teamName)) {
                        for(Documento d : t.getDocumenti()) {
                            if(d.getData().toString().equals(data.toString())) {
                                Giudice g = getOrCreateGiudice(currentUser.getUsername());
                                Commento c = new Commento(commento, g, d);
                                d.aggiungiCommento(c);
                            }
                        }
                    }
                }
            }
        }
    }

    public String getTeam(String hackathonName) {
        for(Hackathon h : hackathons) {
            if(h.getTitolo().equals(hackathonName.toString())) {
                for(Team t : h.getTeams()) {
                    for(Partecipante p : t.getMembri()){
                        if(p.getUsername().equals(currentUser.getUsername())) {
                            return t.getNome();
                        }
                    }
                }
            }
        }
        return null;
    }

    public void populateDocumentiBox(JComboBox giudiceComboBox, String teamName, String hackathonName, Object data) {
        giudiceComboBox.removeAllItems();
        giudiceComboBox.addItem("");
        if(data != null && (!data.toString().isEmpty())) {
            for (Hackathon h : hackathons) {
                if (h.getTitolo().equals(hackathonName)) {
                    for (Team t : h.getTeams()) {
                        if (t.getNome().equals(teamName)) {
                            for (Documento d : t.getDocumenti()) {
                                if(d.getData().toString().equals(data.toString())) {
                                    for(Commento c : d.getCommenti()) {
                                        giudiceComboBox.addItem(c.getGiudice().getUsername());
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public String getDescComm(Object dataDocumento, String teamName, String hackathonName, Object giudice) {
        if(dataDocumento == null || dataDocumento.toString().isEmpty()) { return ""; }
        if(giudice == null || giudice.toString().isEmpty()) { return ""; }

        for(Hackathon h : hackathons) {
            if(h.getTitolo().equals(hackathonName)) {
                for(Team t : h.getTeams()) {
                    if(t.getNome().equals(teamName)) {
                        for (Documento d : t.getDocumenti()) {
                            if (d.getData().toString().equals(dataDocumento.toString())) {
                                for(Commento c : d.getCommenti()) {
                                    if(c.getGiudice().getUsername().equals(giudice.toString())) {
                                        return c.getTesto();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return "";
    }
}
