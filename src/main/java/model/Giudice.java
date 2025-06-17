package model;

import java.util.ArrayList;

public class Giudice extends Utente{
    //attributi usati per le relazioni
    ArrayList<Voto> Voti= new ArrayList<Voto>();
    ArrayList<Commento> Commenti = new ArrayList<Commento>();

    public Giudice(Utente user) {
        super(user.email, user.username, user.password);
    }

    //to be implemented
    private void pubblicaProblema(){
        return;
    }
    private void commentaProgressi(){
        Commento c = null; //temporaneo
        Commenti.add(c);
    }

    //to implement, maybe isn't needeed?
    private void rispondiInvito(boolean risposta){
        if(risposta == true){
                return;
        }
    }

    private void daiVoto(Team team, int voto){
        Voto v = new Voto(team, this, voto);
        Voti.add(v);
        //aggiungi ad array di voti di team il voto! ----------
    }
}
