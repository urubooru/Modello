package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Classifica {
    private ArrayList<Team> teams;

    public Classifica() {
        teams = new ArrayList<>();
    }

    public void aggiungiTeam(Team team) {
        teams.add(team);
    }

    public ArrayList<Team> getClassifica() {
        // Ordina i team per punteggio decrescente
        Collections.sort(teams, new Comparator<Team>() {
            @Override
            public int compare(Team t1, Team t2) {
                return Integer.compare(t2.getPunteggio(), t1.getPunteggio());
            }
        });
        return teams;
    }
}