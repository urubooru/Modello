package Model;

import java.util.ArrayList;

public class Classifica {
    private Hackathon hackathon;
    private ArrayList<Team> teams;
    private boolean classificaPubblicata;

    public Classifica(Hackathon hackathon) {
        this.hackathon = hackathon;
        this.teams = new ArrayList<>();
    }

    public void aggiornaClassifica() {
        int n = teams.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (teams.get(j).getPunteggio() < teams.get(j + 1).getPunteggio()) {
                    Team temp = teams.get(j);
                    teams.set(j, teams.get(j + 1));
                    teams.set(j + 1, temp);
                }
            }
        }
    }

    public void pubblicaClassifica(){
        this.classificaPubblicata = true;
    }

    public ArrayList<Team> getClassifica() {
        if(!(this.classificaPubblicata)) throw new RuntimeException("Classifica non pubblicata");
        aggiornaClassifica();
        return new ArrayList<>(teams);
    }

    public void aggiungiTeam(Team team) {
        teams.add(team);
    }


}