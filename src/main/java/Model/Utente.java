package Model;

public class Utente {
    protected String email;
    protected String username;
    protected String password;

    public Utente(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getEmail(){ return email; }
    protected void setEmail(String newEmail){ this.email = newEmail; }

    public String getUsername(){ return username; }
    protected void setUsername(String newUser){ this.username = newUser; }

    //non implementiamo getPassword perché non si dovrebbe potere in generale su un sito
    protected void setPassword(String password){ this.password = password; }

    //to be added
    private void iscrivitiHackathon(){ return;}
}
