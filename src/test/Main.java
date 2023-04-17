package test;

import entities.Consultation;
import services.ConsultationService;

import java.sql.SQLException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws RuntimeException, SQLException, ParseException {
        Consultation c = new Consultation(2,1,"therapie",new java.sql.Date(2022,05,15),"17:50");
        ConsultationService rvs = new ConsultationService();
         //rvs.ajouter(c);
         rvs.recuperer();

    }
}