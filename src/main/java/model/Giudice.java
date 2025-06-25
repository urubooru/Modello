package model;

import java.util.ArrayList;

public class Giudice extends Utente{
    //attributi usati per le relazioni
    ArrayList<Voto> Voti= new ArrayList<Voto>();
    ArrayList<Commento> Commenti = new ArrayList<Commento>();

    //attributi per gli inviti
    private ArrayList<Invito> inviti;
    private ArrayList<Invito> invitiAccettati;

    public Giudice(Utente user) {
        super(user.email, user.username, user.password);
        inviti = new ArrayList<Invito>();
        invitiAccettati = new ArrayList<Invito>();
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

    public void addInvito(Hackathon hackathon, Team team) {
        if(team!=null) {
            for (Invito i : inviti) {
                if (i.getHackathon().getTitolo().equals(hackathon.getTitolo()) && i.getTeam().getNome().equals(team.getNome())) {
                    throw new RuntimeException("L'invito è già presente!");
                }
            }
        } else{
            for(Invito i : inviti){
                if(i.getHackathon().getTitolo().equals(hackathon.getTitolo()) && i.getTeam() == null){
                    throw new RuntimeException("L'invito è già presente!");
                }
            }
        }

        Invito invito = new Invito(this, hackathon, team);
        inviti.add(invito);
    }

    public ArrayList<Invito> getInviti() {
        return inviti;
    }

    public void rifiutaInvito(String hackathonName, String teamName) {
        for(Invito i : inviti){
            if(i.getHackathon().getTitolo().equals(hackathonName) && i.getTeam()==null && teamName.equals("ORGANIZZATORE")){
                inviti.remove(i);
                break;
            }
            else if(i.getHackathon().getTitolo().equals(hackathonName) && i.getTeam().getNome().equals(teamName)) {
                inviti.remove(i);
                break;
            }
        }
    }

    public void accettaInvito(String hackathonName, String teamName) {
        for(Invito i : inviti){
            if(i.getHackathon().getTitolo().equals(hackathonName)){
                invitiAccettati.add(i);
                inviti.remove(i);
                break;
            }
        }
    }
}
