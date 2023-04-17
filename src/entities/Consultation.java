package entities;

import java.time.LocalDate;
import java.sql.Date;

public class Consultation {
    private int id ;
    private String typec;
    private String timec;
    private Date datec;
    private int patient_id ;
    //private Ordonnance ordonnance;

    public Consultation() {

    }
    //public Ordonnance getOrdonnance() {return ordonnance;}

   // public void setOrdonnance(Ordonnance ordonnance) {this.ordonnance = ordonnance;}



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypec() {
        return typec;
    }

    public void setTypec(String typec) {
        this.typec = typec;
    }

    public String getTimec() {
        return timec;
    }

    public void setTimec(String timec) {
        this.timec = timec;
    }

    public Date getDatec() {
        return datec;
    }

    public void setDatec(Date datec) {
        this.datec = datec;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public Consultation(int id ,int patient_id,String typec,Date datec,String timec) {
        this.id = id;
        this.patient_id=patient_id;
        this.timec=timec;
        this.datec=datec;
        this.typec=timec;

    }

    @Override
    public String toString() {
        return "Consultation{" +
                "id=" + id +
                ", typec='" + typec + '\'' +
                ", timec='" + timec + '\'' +
                ", datec=" + datec +
                ", patient_id=" + patient_id +
                '}';
    }
}
