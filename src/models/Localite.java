package models;

public class Localite {
    private Integer id;
    private String nomLocalite;
    private Integer nbCas;
    private int id_communique;

    public Localite(int id, String nomLocalite, Integer nbCas, int id_communique) {
        this.id = id;
        this.nomLocalite = nomLocalite;
        this.nbCas = nbCas;
        this.id_communique = id_communique;
    }

    public Localite(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomLocalite() {
        return nomLocalite;
    }

    public void setNomLocalite(String nomLocalite) {
        this.nomLocalite = nomLocalite;
    }

    public Integer getNbCas() {
        return nbCas;
    }

    public void setNbCas(Integer nbCas) {
        this.nbCas = nbCas;
    }

    public int getId_communique() {
        return id_communique;
    }

    public void setId_communique(int id_communique) {
        this.id_communique = id_communique;
    }
}
