package Model;

public class Utente {
    protected String email;
    protected String username;
    protected String password;

    public Utente(String email, String username, String password) {
        if(email.isEmpty() || username.isEmpty() || password.isEmpty()) { throw new NullPointerException("Manca qualcosa per la registrazione"); }
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getEmail(){ return email; }
    protected void setEmail(String newEmail){ this.email = newEmail; }

    public String getUsername(){ return username; }
    protected void setUsername(String newUser){ this.username = newUser; }

    public void setPassword(String password){
        if(password.isEmpty() || password==null)
            throw new RuntimeException("Password vuota");
        this.password = password;
    }
    public String getPassword(){ return password; }

    //to be added
    private void iscrivitiHackathon(){ return;}
}
