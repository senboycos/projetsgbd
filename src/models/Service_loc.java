package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Service_loc {
    private Connection conn;
    public List<Localite> findAll(){
        List<Localite> localites = new ArrayList<>();
        try {
            conn = DBCPDataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM localite");
            while (rs.next()) {
                Localite localite = new Localite();
                localite.setId(rs.getInt("id"));
                localite.setNomLocalite(rs.getString("nomLocalite"));
                localite.setNbCas(rs.getInt("nbCas"));
                localite.setId_communique(rs.getInt("id_communique"));
                localites.add(localite);
            }
            conn.close();
            return localites;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            try {
                conn.close();
                return localites;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return localites;
        }

    }
    public Localite findById(final Integer id) {
        Localite localite = new Localite();
        Communique communique = new Communique();
        try {
            conn = DBCPDataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM localite where id ="+ id);
            while (rs.next()) {
                localite.setNomLocalite(rs.getString("nomLocalite"));
                localite.setId(rs.getInt("id"));
                localite.setNbCas(rs.getInt("nbCas"));
                localite.setId_communique(rs.getInt("id_communique"));
            }
            conn.close();
            return localite;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return localite;
    }
    public void add (Localite localite){
        try{
            String query = " insert into communiques (nomLocalite,nbCas,id_communique)"
                    + " values (?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(2,localite.getNomLocalite());
            preparedStmt.setInt(3,localite.getNbCas());
            preparedStmt.setInt(4,localite.getId_communique());
            // execute the preparedstatement
            preparedStmt.execute();

            conn.close();

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
