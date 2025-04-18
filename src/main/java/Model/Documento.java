package Model;

import java.util.Date;

public class Documento {
    private Date data;
    private String descrizione;

    public Documento(Date data, String descrizione) {
        this.data = data;
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Date getData() {
        return data;
    }
}