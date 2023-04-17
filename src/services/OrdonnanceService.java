package services;

import entities.Ordonnance;
import utils.MyDB;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdonnanceService implements IServiceOrdonnance<Ordonnance>{

    Connection cnx;

    public OrdonnanceService() {
        cnx = MyDB.getInstance().getCnx();
    }
    @Override
    public void ajouter(Ordonnance r) throws SQLException, ParseException {
        PreparedStatement statement;
        statement = cnx.prepareStatement("INSERT INTO  ordonnance (id,medicament,dateo,description) VALUES" +
                "( ?,?,?,?)");
        statement.setInt(1,r.getId());
        statement.setString(2, r.getMedicament());
        statement.setDate(3, (Date) r.getdateo());
        statement.setString(4, r.getDescription());





        statement.executeUpdate();
        System.out.println("Ajout réussi !");

    }

    @Override
    public void modifier(Ordonnance r) throws SQLException, ParseException {

    }

    @Override
    public boolean supprimer(int id) throws SQLException, ParseException {
        return false;
    }

    @Override
    public List<Ordonnance> recuperer() throws SQLException, ParseException {
        return null;
    }

    @Override
    public List<Ordonnance> recuperer2() throws SQLException {

        List<Ordonnance> consultations = new ArrayList<>();
        String req = "select * from ordonnance";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Ordonnance p = new Ordonnance();
            p.setId(rs.getInt("id"));
            p.setMedicament(rs.getString("medicament"));
            p.setdateo(rs.getDate("dateo"));
            p.setDescription(rs.getString("description"));

            consultations.add(p);
        }
        return consultations;
    }
    public Ordonnance getById(int id) {
        String query = "SELECT * FROM ordonnance WHERE id = ?";
        Ordonnance ordonnance = null;

        try {
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                Date dateo = result.getDate("dateo");
                String medicament = result.getString("medicament");
                String description = result.getString("description");


                System.out.println("Retrieved dateo value: " + dateo);
                System.out.println("Retrieved dateo value: " + medicament);
                System.out.println("Retrieved dateo value: " + description);

                ordonnance = new Ordonnance(id,  medicament,dateo, description);
                System.out.println("Retrieved dateo value: " + ordonnance);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(ordonnance);
        return ordonnance;

    }
}
