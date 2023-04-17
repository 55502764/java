package services;

import entities.Ordonnance;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

public interface IServiceOrdonnance <O>{
    public void ajouter (O r) throws SQLException, ParseException;
    public void modifier(O r) throws SQLException, ParseException;


    public boolean supprimer(int id) throws SQLException, ParseException;



    public List<O> recuperer() throws SQLException, ParseException;

    List<Ordonnance> recuperer2() throws SQLException;
}
