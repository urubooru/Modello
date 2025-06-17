package model;

public class Organizzatore extends Utente{
    //non aggiungiamo altri attributi, non dovrebbe servire

    public Organizzatore(Utente user) {
        super(user.email, user.username, user.password);
    }

    //to be implemented
    private void pubblicaClassifica(){
        //dai output di team, e voti (con posizioni)
        return;
    }

    //il giudice può rifiutare?
    private void invitaGiudice(Giudice giudice){

        return;
    }

    //set una variabile a boolean in Hackaton
    private void apriRegistrazioni(){

        return;
    }
}
