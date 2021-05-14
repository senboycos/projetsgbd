package models;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Service_com {

    private Connection conn;

    public List<Communique> findAll(){
        List<Communique> communiques = new ArrayList<>();
        try {
            conn = DBCPDataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM communiques");
            while (rs.next()) {
                Communique communique = new Communique();
                communique.setId(rs.getInt("id"));
                communique.setNbreTest(rs.getInt("nbreTest"));
                communique.setNbNouveauxCas(rs.getInt("nbNouveauxCas"));
                communique.setNbCasContacts(rs.getInt("nbCasContacts"));
                communique.setNbCasCommunautaires(rs.getInt("nbCasCommunautaires"));
                communique.setNbrGueris(rs.getInt("nbrGueris"));
                communique.setNbDeces(rs.getInt("nbDeces"));
                communique.setNomFichierSource(rs.getString("nomFichierSource"));
                communique.setDateHeureExtraction(rs.getString("dateHeureExtraction"));
                communiques.add(communique);
            }
            conn.close();
            return communiques;
        } catch (Exception throwables) {
            throwables.printStackTrace();
            try {
                conn.close();
                return communiques;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return communiques;
        }

    }
    public Communique findById(final Integer id) {
        Communique communique = new Communique();
        try {
            conn = DBCPDataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM communiques where id ="+ id);
            while (rs.next()) {
                communique.setNbreTest(rs.getInt("NbreTest"));
                communique.setNbNouveauxCas(rs.getInt("nbNouveauxCas"));
                communique.setNbCasContacts(rs.getInt("nbCasContacts"));
                communique.setNbCasCommunautaires(rs.getInt("nbCasCommunautaires"));
                communique.setNbrGueris(rs.getInt("nbrGueris"));
                communique.setNbDeces(rs.getInt("nbDeces"));
                communique.setNomFichierSource(rs.getString("nomFichierSource"));
                communique.setDateHeureExtraction(rs.getString("dateHeureExtraction"));
            }
            conn.close();
            return communique;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return communique;
    }
    public void add (Communique communique){
        try{
            String query = " insert into communiques (nbreTest,nbNouveauxCas,nbCasContacts, nbCasCommunautaires, nbrGueris, nbDeces, nomFichierSource,dateHeureExtraction)"
                    + " values (?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt    (1, communique.getNbreTest());
            preparedStmt.setInt (2, communique.getNbCasContacts());
            preparedStmt.setInt(3,communique.getNbCasContacts());
            preparedStmt.setInt (4, communique.getNbCasCommunautaires());
            preparedStmt.setInt    (5, communique.getNbrGueris());
            preparedStmt.setInt    (6, communique.getNbDeces());
            preparedStmt.setString    (7, communique.getNomFichierSource());
            preparedStmt.setString   (8, communique.getDateHeureExtraction().toString());
            // execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
