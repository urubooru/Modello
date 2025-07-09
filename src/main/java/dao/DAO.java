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
}
