package entities;

import java.time.LocalDate;
import java.util.Date;

public class Ordonnance {
    private int id;
    private String medicament;
    private Date dateo;
    private String description;
    //private Consultation consultation;

    public Ordonnance() {

    }

   // public Consultation getConsultation() {return consultation;}

   // public void setConsultation(Consultation consultation) {this.consultation = consultation;}




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicament() {
        return medicament;
    }

    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }

    public Date getdateo() {
        return dateo;
    }

    public void setdateo(Date dateo) {
        this.dateo = dateo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ordonnance(int id,String medicament,Date dateo,String description) {
        this.id = id;
        this.medicament=medicament;
        this.dateo=dateo;
        this.description=description;


    }

    @Override
    public String toString() {
        return "Ordonnance{" +
                "id=" + id +
                ", medicament='" + medicament + '\'' +
                ", dateo=" + dateo +
                ", description='" + description + '\'' +
                '}';
    }
}
