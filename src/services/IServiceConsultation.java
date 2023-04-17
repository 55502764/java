package services;

import entities.Consultation;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IServiceConsultation <C> {
    public void ajouter (C r) throws SQLException, ParseException;
    public void modifier(C r) throws SQLException, ParseException;


    public boolean supprimer(int id) throws SQLException, ParseException;



    public List<C> recuperer() throws SQLException, ParseException;
}
