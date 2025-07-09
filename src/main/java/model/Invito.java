package model;

public class Invito {
    Hackathon hackathon;
    Team team; //IF TEAM == NULL -> GIUDICE
    Utente invitato;

    public Invito(Utente invitato, Hackathon hackathon, Team team) {
        this.invitato = invitato;
        this.hackathon = hackathon;
        this.team = team;
    }

    public Hackathon getHackathon() {
        return hackathon;
    }
    public Team getTeam() {
        return team;
    }
//    public Utente getInvitato() {
//        return invitato;
//    }
}
