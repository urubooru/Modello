package daoImplementation;

import dao.DAO;
import db.DatabaseConnection;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DAOImplementation implements DAO {
    Connection conn;

    public void retrieveUsers(ArrayList<String> mails, ArrayList<String> users, ArrayList<String> passwords) throws SQLException {
        conn = DatabaseConnection.getInstance();

        //Create string for query
        String query = "SELECT * FROM " + '"' + "Utente" + '"' + " ORDER BY username ASC";
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
        String query = "SELECT * FROM " + '"' + "Hackathon" + '"' + " ORDER BY titolo ASC";
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

        String query = "SELECT * FROM " + '"' + "Organizzatore" + '"' + " ORDER BY username ASC";
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

        String query = "SELECT * FROM " + '"' + "Partecipante" + '"' + " ORDER BY username ASC";
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

        String query = "SELECT * FROM " + '"' + "Team" + '"' + " ORDER BY nome ASC";
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

        String query = "SELECT * FROM " + '"' + "Invito" + '"' + " ORDER BY hackathon ASC";
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

        String query = "SELECT * FROM " + '"' + "Documento" + '"' + " ORDER BY id ASC";
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

        String query = "SELECT * FROM " +'"' + "Giudice" + '"' + " ORDER BY username ASC";
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

        String query = "SELECT * FROM " + '"' + "Voto" + '"' + " ORDER BY giudice ASC";
        try(Statement statement = conn.createStatement()){
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                values.add(rs.getInt("valore"));
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

        String query = "SELECT * FROM " + '"' + "Commento" + '"' + " ORDER BY data ASC";
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

    public void addUser(String email, String user, String pass) throws SQLException {
        conn = DatabaseConnection.getInstance();

        String query = "INSERT INTO " + '"' + "Utente" + '"' + "(username, email, password) VALUES(?, ?, ?)";
        try(PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1, user);
            statement.setString(2, email);
            statement.setString(3, pass);
            statement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
        conn = null;
    }

    public void updatePassword(String username, String password) throws SQLException {
        conn = DatabaseConnection.getInstance();

        String query = "UPDATE " + '"' + "Utente" + '"' + " SET password = ? WHERE username = ?";
        try(PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1, password);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }

        conn = null;
    }

    public void addTeam(String nome, String titolo) throws SQLException {
        conn = DatabaseConnection.getInstance();

        String query = "INSERT INTO " + '"' + "Team" + '"' + "(nome,hackathon) VALUES (?,?)";
        try(PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1, nome);
            statement.setString(2, titolo);
            statement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }

        conn = null;
    }

    public void addPartecipante(String username, String nome) throws SQLException {
        conn = DatabaseConnection.getInstance();

        String query = "INSERT INTO " + '"' + "Partecipante" + '"' + "(username,team) VALUES (?,?)";
        try(PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1, username);
            statement.setString(2, nome);
            statement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }

        conn = null;
    }

    public void addInvito(String titolo, String nome, String invitatoUsername) throws SQLException {
        conn = DatabaseConnection.getInstance();

        String query = "INSERT INTO " + '"' + "Invito" + '"' + "(hackathon,team,invitato) VALUES (?,?,?)";
        try(PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1, titolo);
            statement.setString(2, nome);
            statement.setString(3, invitatoUsername);
            statement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }

        conn = null;
    }

    public void addDocumento(String text, String nome) throws SQLException {
        conn = DatabaseConnection.getInstance();

        String query = "INSERT INTO " + '"' + "Documento" + '"' + "(data,descrizione,team) VALUES (?,?,?)";
        try(PreparedStatement statement = conn.prepareStatement(query)){
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
            statement.setDate(1, java.sql.Date.valueOf(date));
            statement.setString(2,text);
            statement.setString(3,nome);
            statement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
        conn = null;
    }

    public void pubblicaClassifica(String titolo) throws SQLException {
        conn = DatabaseConnection.getInstance();

        String query = "UPDATE " + '"' + "Hackathon" + '"' + " SET pubblicata = TRUE WHERE titolo = ?";
        try(PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1, titolo);
            statement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
        conn = null;
    }

    public void apriRegistrazioni(String titolo) throws SQLException {
        conn = DatabaseConnection.getInstance();

        String query = "UPDATE " + '"' + "Hackathon" + '"' + " SET apriregistrazioni = TRUE WHERE titolo = ?";
        try(PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1, titolo);
            statement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
        conn = null;
    }

    public void addVoto(int voto, String nome, String username) throws SQLException {
        conn = DatabaseConnection.getInstance();

        String query = "INSERT INTO " + '"' + "Voto" + '"' + "(valore,team,giudice) VALUES (?,?,?)";
        try(PreparedStatement statement = conn.prepareStatement(query)){
            statement.setInt(1, voto);
            statement.setString(2, nome);
            statement.setString(3, username);
            statement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
        conn = null;
    }

    public void setProblema(String titolo, String problema) throws SQLException {
        conn = DatabaseConnection.getInstance();

        String query = "UPDATE " + '"' + "Hackathon" + '"' + " SET problema = ? WHERE titolo = ?";
        try(PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1, problema);
            statement.setString(2, titolo);
            statement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
        conn = null;
    }

    public void addCommento(String commento, String username, String descDoc, Date dataDocumento, String nomeTeam) throws SQLException {
        conn = DatabaseConnection.getInstance();

        //Prima cerchiamo il documento in base alla data, il team e il contenuto
        int id = 0;
        String date = new SimpleDateFormat("yyyy-MM-dd").format(dataDocumento);
        String query = "SELECT id FROM " + '"' + "Documento" + '"' + " WHERE descrizione = '" + descDoc +
                "' AND team = '" + nomeTeam + "' AND data = '" +  date + "'";
        try(Statement statement = conn.createStatement()){
            ResultSet rs = statement.executeQuery(query);
            rs.next();
            id = rs.getInt("id");
        } catch(SQLException e){
            e.printStackTrace();
        }

        query = "INSERT INTO " + '"' + "Commento" + '"' + "(testo,giudice,documento,data) VALUES (?,?,?,?)";
        try(PreparedStatement statement = conn.prepareStatement(query)){
            statement.setString(1, commento);
            statement.setString(2, username);
            statement.setInt(3, id);
            statement.setDate(4, java.sql.Date.valueOf(date));
            statement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
        conn = null;
    }

    public void deleteInvito(String hackathonName, String teamName, String username) throws SQLException {
        conn = DatabaseConnection.getInstance();
        if (teamName.equals("GIUDICE")){ teamName = "";}

        String query = "DELETE FROM " + '"' + "Invito" + '"' + " WHERE hackathon = ? AND team = ? AND invitato = ?";
        try(PreparedStatement prepareStatement = conn.prepareStatement(query)){
            prepareStatement.setString(1, hackathonName);
            prepareStatement.setString(2, teamName);
            prepareStatement.setString(3, username);
            prepareStatement.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
        conn = null;
    }
}
