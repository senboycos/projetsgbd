package models;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCPDataSource {


    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/coviddb","root","");
        return con;
    }


}
