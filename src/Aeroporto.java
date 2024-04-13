public class Aeroporto {
    private String codice;
    private String nazione;
    private int numeroMaxAeromobiliAlGiorno;

    //Costruttore
    public Aeroporto(String codice, String nazione, int numeroMaxAeromobiliAlGiorno) {
        this.codice = codice;
        this.nazione = nazione;
        this.numeroMaxAeromobiliAlGiorno = numeroMaxAeromobiliAlGiorno;
    }

    //Metodi
    public String getCodice() {
        return codice;
    }
    public String getNazione() {
        return nazione;
    }
    public int getNumeroMaxAeromobiliAlGiorno() {
        return numeroMaxAeromobiliAlGiorno;
    }
    public void setCodice(String codice) {
        this.codice = codice;
    }
    public void setNazione(String nazione) {
        this.nazione = nazione;
    }
    public void setNumeroMaxAeromobiliAlGiorno(int numeroMaxAeromobiliAlGiorno) {
        this.numeroMaxAeromobiliAlGiorno = numeroMaxAeromobiliAlGiorno;
    }
}

