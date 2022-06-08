package ua.kpi.iasa.entities;

public class Declarations {
    private int id;
    private int id_patient;
    private int id_doctor;

    public Declarations(){}

    public Declarations(int id_patient, int id_doctor) {
        this.id_patient = id_patient;
        this.id_doctor = id_doctor;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }
}
