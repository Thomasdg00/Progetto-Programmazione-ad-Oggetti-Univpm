import java.util.List;
public class Viaggio {
    private String aeromobile;
    private int numeroPasseggeri;
    private String aeroportoDiPartenza;
    private String aeroportoDiArrivo;
    private int giornoDelVolo;
    enum Tipo {OVERBOARD, UNDERBOARD, ORDINARIO}

    //Costruttore
    public Viaggio(String aeromobile, int numeroPasseggeri, String aeroportoDiPartenza, String aeroportoDiArrivo, int giornoDelVolo){
        this.aeromobile = aeromobile;
        this.numeroPasseggeri = numeroPasseggeri;
        this.aeroportoDiPartenza = aeroportoDiPartenza;
        this.aeroportoDiArrivo = aeroportoDiArrivo;
        this.giornoDelVolo = giornoDelVolo;
    }

    //Metodi
    public String getAeromobile() {
        return aeromobile;
    }
    public void setId(String aeromobile) {
        this.aeromobile = aeromobile;
    }
    public int getNumeroPasseggeri() {
        return numeroPasseggeri;
    }
    public void setNumeroPasseggeri(int numeroPasseggeri) {
        this.numeroPasseggeri = numeroPasseggeri;
    }
    public String getAeroportoDiPartenza() {
        return aeroportoDiPartenza;
    }
    public void setAeroportoDiPartenza(String aeroportoDiPartenza) {
        this.aeroportoDiPartenza = aeroportoDiPartenza;
    }
    public String getAeroportoDiArrivo() {
        return aeroportoDiArrivo;
    }
    public void setAeroportoDiArrivo(String aeroportoDiArrivo) {
        this.aeroportoDiArrivo = aeroportoDiArrivo;
    }
    public int getGiornoDelVolo() {
        return giornoDelVolo;
    }
    public void setGiornoDelVolo(int giornoDelVolo) {
        this.giornoDelVolo = giornoDelVolo;
    }
    public Tipo getTipoViaggio(Aeromobile aeromobile){
        double percentuale = (double) numeroPasseggeri / aeromobile.getMaxPassegeri() * 100;
        if(percentuale < 10){
            return Tipo.UNDERBOARD;
        }
        else if(percentuale > 90){
            return Tipo.OVERBOARD;
        }
        else{
            return Tipo.ORDINARIO;
        }
    }
}
