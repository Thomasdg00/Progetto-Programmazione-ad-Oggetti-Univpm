public class Aeromobile {
    private String id;
    private int maxPassegeri;
    private int serbatoioMax;
    private int viaggiUltimoAnno;

    //Costruttore
    public Aeromobile(String id, int maxPassegeri, int serbatoioMax, int viaggiUltimoAnno){
        this.id = id;
        this.maxPassegeri = maxPassegeri;
        this.serbatoioMax = serbatoioMax;
        this.viaggiUltimoAnno = viaggiUltimoAnno;
    }

    //Metodi
    public String getId() {
        return id;
    }

    public int getMaxPassegeri() {
        return maxPassegeri;
    }

    public int getSerbatoioMax() {
        return serbatoioMax;
    }

    public int getViaggiUltimoAnno() {
        return viaggiUltimoAnno;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMaxPassegeri(int maxPassegeri) {
        this.maxPassegeri = maxPassegeri;
    }

    public void setSerbatoioMax(int serbatoioMax) {
        this.serbatoioMax = serbatoioMax;
    }

    public void setViaggiUltimoAnno(int viaggiUltimoAnno) {
        this.viaggiUltimoAnno = viaggiUltimoAnno;
    }
}

