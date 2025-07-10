package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public interface DAO {
    //this will have all the function signatures

    void retrieveUsers(ArrayList<String> mails, ArrayList<String> users, ArrayList<String> passwords) throws SQLException;

    void retrieveHackathons(ArrayList<String> titles, ArrayList<String> sedi, ArrayList<String> problemi, ArrayList<String> organizzatori, ArrayList<Date> dateInizi, ArrayList<Date> dateFini, ArrayList<Date> iniziIscr, ArrayList<Date> finiIscr, ArrayList<Integer> maxIscr, ArrayList<Integer> maxTeam, ArrayList<Boolean> clasP, ArrayList<Boolean> regOpen) throws SQLException;

    void retrieveOrganizzatori(ArrayList<String> users) throws SQLException;

    void retrievePartecipanti(ArrayList<String> users, ArrayList<String> teamNames) throws SQLException;

    void retrieveTeams(ArrayList<String> teamNames, ArrayList<String> hackathonTeam) throws SQLException;

    void retrieveInviti(ArrayList<String> teamInvito, ArrayList<String> hackathonInvito, ArrayList<String> invitato) throws SQLException;

    void retrieveDocs(ArrayList<Integer> ids, ArrayList<Date> dates, ArrayList<String> descrizioni, ArrayList<String> teamDocs) throws SQLException;

    void retrieveGiudici(ArrayList<String> users, ArrayList<String> hackathonGiudici) throws SQLException;

    void retrieveVotes(ArrayList<Integer> values, ArrayList<String> teamVoti, ArrayList<String> giudiciVoti) throws SQLException;

    void retrieveComments(ArrayList<Integer> documenti, ArrayList<Date> datesComm, ArrayList<String> testo, ArrayList<String> giudice) throws SQLException;

    void addUser(String email, String user, String pass) throws SQLException;

    void updatePassword(String username, String password) throws SQLException;

    void addTeam(String nome, String titolo) throws SQLException;

    void addPartecipante(String username, String nome) throws SQLException;

    void addInvito(String titolo, String nome, String invitatoUsername) throws SQLException;

    void addDocumento(String text, String nome) throws SQLException;

    void pubblicaClassifica(String titolo) throws SQLException;

    void apriRegistrazioni(String titolo) throws SQLException;

    void addVoto(int voto, String nome, String username) throws SQLException;

    void setProblema(String titolo, String problema) throws SQLException;

    void addCommento(String commento, String username, String descDoc, Date dataDocumento, String nomeTeam) throws SQLException;

    void deleteInvito(String hackathonName, String teamName, String username) throws SQLException;
}
