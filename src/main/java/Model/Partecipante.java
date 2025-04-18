package Model;

public class Partecipante extends Utente{
    private String invitoTeam;
    private Team team;
    private boolean hasTeam;

    public Partecipante(Utente user) {
        super(user.email, user.username, user.password);
        this.team = null;
        this.invitoTeam = null;
        this.hasTeam = false;
    }

    public Team getTeam(){ return this.team; }
    private void setTeam(Team team){ this.team = team; }

    private void setInvitoTeam(String invito){ this.invitoTeam = invito; }

    private int invitaTeam(Partecipante partecipante, String invito){
        if(partecipante.hasTeam == false) return -1;
        partecipante.setTeam(this.team);
        partecipante.setInvitoTeam(invito);
        return 0;
    }

    private void rispondiInvito(boolean risposta){
        if(risposta == false){
            this.setTeam(null);
        }
        else{
            this.hasTeam = true;
            //do something (change team and add to a list)
        }
        this.setInvitoTeam(null);
    }
}
