package ua.kpi.iasa.entities;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Diagnostic {
    private int id;
    private int id_patient;
    private String medicines;
    private String treatment;
    private String complains;
    private String diagnose;
    private String operation;
    private float price;
    private Timestamp date_updated;
    private Timestamp date_created;

    public Diagnostic(){}

    public Diagnostic(int id_patient, String medicines, String treatment, String complains,
                      String diagnose, String operation, float price, Timestamp date_updated,
                      Timestamp date_created) {
        this.id_patient = id_patient;
        this.medicines = medicines;
        this.treatment = treatment;
        this.complains = complains;
        this.diagnose = diagnose;
        this.operation = operation;
        this.price = price;
        this.date_updated = date_updated;
        this.date_created = date_created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getComplains() {
        return complains;
    }

    public void setComplains(String complains) {
        this.complains = complains;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Timestamp getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(Timestamp date_updated) {
        this.date_updated = date_updated;
    }

    public Timestamp getDate_created() {
        return date_created;
    }

    public void setDate_created(Timestamp date_created) {
        this.date_created = date_created;
    }
}
