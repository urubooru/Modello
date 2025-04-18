package Model;

public class Voto {
    private int valore;
    private Team team;
    private Giudice giudice;

    public int getValore() {    return valore;  }
    public void setValore(int valore) {  this.valore = valore;  }

    public Voto(Team team, Giudice giudice, int valore) {
        this.valore = valore;
        this.team = team;
        this.giudice = giudice;
    }
}
