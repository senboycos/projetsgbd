package models;

import java.util.Date;

public class Communique {
    private Integer id;
    private Integer nbreTest,nbNouveauxCas,nbCasContacts,nbCasCommunautaires,nbrGueris,nbDeces;
    private String nomFichierSource;
    private String  dateHeureExtraction;
    public Communique(Integer id, Integer nbreTest, Integer nbNouveauxCas, Integer nbCasContacts, Integer nbCasCommunautaires, Integer nbrGueris, Integer nbDeces, String nomFichierSource, String dateHeureExtraction) {
        this.id = id;
        this.nbreTest = nbreTest;
        this.nbNouveauxCas = nbNouveauxCas;
        this.nbCasContacts = nbCasContacts;
        this.nbCasCommunautaires = nbCasCommunautaires;
        this.nbrGueris = nbrGueris;
        this.nbDeces = nbDeces;
        this.nomFichierSource = nomFichierSource;
        this.dateHeureExtraction = dateHeureExtraction;
    }

    public Communique() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNbreTest() {
        return nbreTest;
    }

    public void setNbreTest(Integer nbreTest) {
        this.nbreTest = nbreTest;
    }

    public Integer getNbNouveauxCas() {
        return nbNouveauxCas;
    }

    public void setNbNouveauxCas(Integer nbNouveauxCas) {
        this.nbNouveauxCas = nbNouveauxCas;
    }

    public Integer getNbCasContacts() {
        return nbCasContacts;
    }

    public void setNbCasContacts(Integer nbCasContacts) {
        this.nbCasContacts = nbCasContacts;
    }

    public Integer getNbCasCommunautaires() {
        return nbCasCommunautaires;
    }

    public void setNbCasCommunautaires(Integer nbCasCommunautaires) {
        this.nbCasCommunautaires = nbCasCommunautaires;
    }

    public Integer getNbrGueris() {
        return nbrGueris;
    }

    public void setNbrGueris(Integer nbrGueris) {
        this.nbrGueris = nbrGueris;
    }

    public Integer getNbDeces() {
        return nbDeces;
    }

    public void setNbDeces(Integer nbDeces) {
        this.nbDeces = nbDeces;
    }

    public String getNomFichierSource() {
        return nomFichierSource;
    }

    public void setNomFichierSource(String nomFichierSource) {
        this.nomFichierSource = nomFichierSource;
    }

    public String getDateHeureExtraction() {
        return dateHeureExtraction;
    }

    public void setDateHeureExtraction(String dateHeureExtraction) {
        this.dateHeureExtraction = dateHeureExtraction;
    }
}
