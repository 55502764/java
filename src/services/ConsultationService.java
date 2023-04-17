package services;

import entities.Consultation;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

public class ConsultationService implements IServiceConsultation<Consultation>{
    Connection cnx;

    public ConsultationService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Consultation r) throws SQLException {
        PreparedStatement statement;
        statement = cnx.prepareStatement("INSERT INTO  consultation (id,patient_id,typec,datec,timec) VALUES" +
                "( ?,?,?,?,?)");

        statement.setDate(4, (Date) r.getDatec());

        statement.setString(3, r.getTypec());
        statement.setString(5, r.getTimec());
        statement.setInt(2, r.getPatient_id());
        statement.setInt(1,r.getId());


        statement.executeUpdate();
        System.out.println("Ajout réussi !");
    }

    @Override
    public void modifier(Consultation r) throws SQLException {
        String query = "UPDATE  consultation set datec=?,typec=? ,timec=?,patient_id=? Where id ='" + r.getId() + "'";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);

            ste.setDate(1, (Date) r.getDatec());
            ste.setString(2, r.getTypec());
            ste.setString(3, r.getTimec());
            ste.setInt(4, r.getPatient_id());
            // ste.setBoolean(3, r.isIs_confirmed());

            ste.executeUpdate();
            System.out.println("La consultation a ete modifié avec succés");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }



    @Override
    public boolean supprimer(int id) throws SQLException {
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from consultation where id = ? ");
            req.setInt(1, id);
            req.executeUpdate();
            ok = true;
            System.out.println("consultation supprimé !");
        } catch (SQLException ex) {
            System.out.println("Error in delete " + ex);
        }
        return ok;
    }

    @Override
    public List<Consultation> recuperer() throws SQLException {
        List<Consultation> consultations = new ArrayList<>();
        String req = "select * from Consultation";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Consultation p = new Consultation();
            p.setId(rs.getInt("id"));
            p.setTypec(rs.getString("typec"));
            p.setDatec(rs.getDate("datec"));
            p.setTimec(rs.getString("timec"));
            p.setPatient_id(rs.getInt("patient_id"));
            consultations.add(p);
        }
        return consultations;
    }




}
