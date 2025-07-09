package daoImplementation;

import dao.DAO;
import db.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;

public class DAOImplementation implements DAO {
    Connection conn;

    public void retrieveUsers(ArrayList<String> mails, ArrayList<String> users, ArrayList<String> passwords) throws SQLException {
        conn = DatabaseConnection.getInstance();

        //Create string for query
        String query = "SELECT * FROM " + '"' + "Utente" + '"' + ";";
        //Create statement using our connection + put it in the try
        try(Statement statement = conn.createStatement()) {
            //get our resultSet executing our statement
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                mails.add(rs.getString("email"));
                users.add(rs.getString("username"));
                passwords.add(rs.getString("password"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        conn = null;
    }

    public void retrieveHackathons(ArrayList<String> titles, ArrayList<String> sedi, ArrayList<String> problemi, ArrayList<String> organizzatori,
                                   ArrayList<Date> dateInizi, ArrayList<Date> dateFini, ArrayList<Date> iniziIscr, ArrayList<Date> finiIscr, ArrayList<Integer> maxIscr,
                                   ArrayList<Integer> maxTeam, ArrayList<Boolean> clasP, ArrayList<Boolean> regOpen) throws SQLException {
        conn = DatabaseConnection.getInstance();
        String query = "SELECT * FROM " + '"' + "Hackathon" + '"' + ";";
        try(Statement statement = conn.createStatement()){
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                titles.add(rs.getString("titolo"));
                sedi.add(rs.getString("sede"));
                problemi.add(rs.getString("problema"));
                organizzatori.add(rs.getString("organizzatore"));
                dateInizi.add(rs.getDate("dataInizio"));
                dateFini.add(rs.getDate("dataFine"));
                iniziIscr.add(rs.getDate("inizioIscrizioni"));
                finiIscr.add(rs.getDate("fineIscrizioni"));
                maxIscr.add(rs.getInt("maxIscritti"));
                maxTeam.add(rs.getInt("dimMaxTeam"));
                clasP.add(rs.getBoolean("pubblicata"));
                regOpen.add(rs.getBoolean("apriRegistrazioni"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        conn = null;
    }

    public void retrieveOrganizzatori(ArrayList<String> users) throws SQLException {
        conn = DatabaseConnection.getInstance();

        String query = "SELECT * FROM " + '"' + "Organizzatore" + '"' + ";";
        try(Statement statement = conn.createStatement()){
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                users.add(rs.getString("username"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        conn = null;
    }

    public void retrievePartecipanti(ArrayList<String> users, ArrayList<String> teamNames) throws SQLException {
        conn = DatabaseConnection.getInstance();

        String query = "SELECT * FROM " + '"' + "Partecipante" + '"' + ";";
        try(Statement statement = conn.createStatement()){
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                users.add(rs.getString("username"));
                teamNames.add(rs.getString("team"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        conn = null;
    }

    public void retrieveTeams(ArrayList<String> teamNames, ArrayList<String> hackathonTeam) throws SQLException {
        conn = DatabaseConnection.getInstance();

        String query = "SELECT * FROM " + '"' + "Team" + '"' + ";";
        try(Statement statement = conn.createStatement()){
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                teamNames.add(rs.getString("nome"));
                hackathonTeam.add(rs.getString("hackathon"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        conn = null;
    }

    public void retrieveInviti(ArrayList<String> teamInvito, ArrayList<String> hackathonInvito, ArrayList<String> invitato) throws SQLException {
        conn = DatabaseConnection.getInstance();

        String query = "SELECT * FROM " + '"' + "Invito" + '"' + ";";
        try(Statement statement = conn.createStatement()){
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                teamInvito.add(rs.getString("team"));
                hackathonInvito.add(rs.getString("hackathon"));
                invitato.add(rs.getString("invitato"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        conn = null;
    }

    public void retrieveDocs(ArrayList<Integer> ids, ArrayList<Date> dates, ArrayList<String> descrizioni, ArrayList<String> teamDocs) throws SQLException {
        conn = DatabaseConnection.getInstance();

        String query = "SELECT * FROM " + '"' + "Documento" + '"' + ";";
        try(Statement statement = conn.createStatement()){
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                ids.add(rs.getInt("id"));
                dates.add(rs.getDate("data"));
                descrizioni.add(rs.getString("descrizione"));
                teamDocs.add(rs.getString("team"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        conn = null;
    }

    public void retrieveGiudici(ArrayList<String> users, ArrayList<String> hackathonGiudici) throws SQLException {
        conn = DatabaseConnection.getInstance();

        String query = "SELECT * FROM " +'"' + "Giudice" + '"' + ";";
        try(Statement statement = conn.createStatement()){
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                users.add(rs.getString("username"));
                hackathonGiudici.add(rs.getString("hackathon"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        conn = null;
    }

    public void retrieveVotes(ArrayList<Integer> values, ArrayList<String> teamVoti, ArrayList<String> giudiciVoti) throws SQLException {
        conn = DatabaseConnection.getInstance();

        String query = "SELECT * FROM " + '"' + "Voto" + '"' + ";";
        try(Statement statement = conn.createStatement()){
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                values.add(rs.getInt("id"));
                teamVoti.add(rs.getString("team"));
                giudiciVoti.add(rs.getString("giudice"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        conn = null;
    }

    public void retrieveComments(ArrayList<Integer> documenti, ArrayList<Date> datesComm, ArrayList<String> testo, ArrayList<String> giudice) throws SQLException {
        conn = DatabaseConnection.getInstance();

        String query = "SELECT * FROM " + '"' + "Commento" + '"' + ";";
        try(Statement statement = conn.createStatement()){
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                documenti.add(rs.getInt("documento"));
                datesComm.add(rs.getDate("data"));
                testo.add(rs.getString("testo"));
                giudice.add(rs.getString("giudice"));
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        conn = null;
    }
}
