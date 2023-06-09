package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyDB {

    public Connection cnx;
    String url = "jdbc:mysql://localhost:3306/docare_dbtest?";
    String user = "root";
    String password = "";
    Connection getcnx;

    private static MyDB instance;
    private MyDB() {
        try {
            cnx = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }

    }
    public static MyDB getInstance() {
        if (instance == null) {
            instance = new MyDB();
        }
        return instance;
    }
    public Connection getCnx() {
        return cnx;
    }
}
