package model;

//Supponiamo voto 1-100
public class Voto {
    private int valore;
    private Team team;
    private Giudice giudice;

    public int getValore() {    return valore;  }
    public void setValore(int valore) {  this.valore = valore;  }

    public Voto(Team team, Giudice giudice, int valore) {
        if(valore <= 0 || valore > 100) { throw new RuntimeException("Valore invalido"); }

        this.valore = valore;
        this.team = team;
        this.giudice = giudice;
    }

    public Object getGiudice() {
        return giudice;
    }
}
