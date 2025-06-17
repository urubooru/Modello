package Model;

import java.util.ArrayList;

public class Partecipante extends Utente{
    private ArrayList<Invito> invitoTeam;
    private ArrayList<Invito> invitiAccettati;

    public Partecipante(Utente user) {
        super(user.email, user.username, user.password);
        invitoTeam = new ArrayList<>();
        invitiAccettati = new ArrayList<>();
    }

    public Team getTeam(Hackathon h){
        for(Invito i : invitiAccettati){
            if(i.getHackathon().getTitolo().equals(h.getTitolo())){
                return i.getTeam();
            }
        }
        return null;
    }

    public void setPartecipazione(Hackathon h, Team t){
        Invito i = new Invito(this, h, t);
        invitoTeam.add(i);
        invitiAccettati.add(i);
    }

    public void addInvito(Hackathon hackathon, Team team) {
        for(Invito i: invitoTeam){
            if(i.getHackathon().getTitolo().equals(hackathon.getTitolo()) && i.getTeam().getNome().equals(team.getNome())){
                throw new RuntimeException("L'invito è già presente!");
            }
        }
        Invito invito = new Invito(this, hackathon, team);
        invitoTeam.add(invito);
    }

    public void accettaInvito(Hackathon hackathon, Team team) {
        for(Invito invito : invitoTeam) {
            if(hackathon.getTitolo().equals(invito.getHackathon().getTitolo()) && team.getNome().equals(invito.getTeam().getNome())) {
                invitiAccettati.add(invito);
                break;
            }
        }
    }
}
