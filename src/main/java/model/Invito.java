package model;

public class Invito {
    Hackathon hackathon;
    Team team; //IF TEAM == NULL -> ORGANIZZATORE
    Partecipante invitato;

    public Invito(Partecipante invitato, Hackathon hackathon, Team team) {
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
    public Partecipante getInvitato() {
        return invitato;
    }
}
