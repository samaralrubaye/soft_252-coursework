/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Class for creating an Appointment object
 */
public class Appointment implements IPersistable {
    public static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
    private final Date date;
    private final String uuid;
    private final String doctorUUID;
    private final String patientUUID;
    private boolean patientNotified;
    private boolean doctorNotified;
    
    /**
     * Constructor for creating a new Appointment object
     * @param uuid - unique identifier for this Appointment
     * @param doctorUUID - unique identifier for the Doctor in this Appointment
     * @param patientUUID - unique identifier for the Patient in this Appointment
     * @param date - Date of this Appointment
     * @param patientNotified - boolean specifying if the patient has been notified of this Appointment
     * @param doctorNotified - boolean specifying if the doctor has been notified of this Appointment
     */
    public Appointment(String uuid, String doctorUUID, String patientUUID, Date date, boolean patientNotified, boolean doctorNotified) {
        super();
        this.uuid = uuid;
        this.doctorUUID = doctorUUID;
        this.patientUUID = patientUUID;
        this.date = date;
        this.patientNotified = patientNotified;
        this.doctorNotified = doctorNotified;
    }
    
    /**
     * 
     * Factory method for creating a new Appointment from it's persisted text format
     * @param txtFormat - the persisted text format
     * @return new Appointment
     */
    public static Appointment newAppointment(String txtFormat) {
        String[] tokens = txtFormat.split(",");
        Date date = null;
        try {
            date = sdf.parse(tokens[3]);
        } catch (ParseException ex) {
            Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Appointment(
                tokens[0],
                tokens[1],
                tokens[2],
                date,
                Boolean.valueOf(tokens[4]),
                Boolean.valueOf(tokens[5])
        );
    }
    
    /**
     * Getter method for the Date of this Appointment
     * @return Date of this Appointment
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Getter method for the unique identifier of this Appointment
     * @return 
     */
    public String getUUID() {
        return this.uuid;
    }

    /**
     * Getter method for the unique identifier of the Doctor in this Appointment
     * @return Doctor unique identifier
     */
    public String getDoctorUUID() {
        return this.doctorUUID;
    }

    /**
     * Getter method for the unique identifier of the Patient in this Appointment
     * @return Patient unique identifier
     */
    public String getPatientUUID() {
        return this.patientUUID;
    }
    
    /**
     * Getter method for the Patient notification state of this Appointment
     * @return Patient notification state of the Appointment (true if the patient has seen the Appointment notification, otherwise false)
     */
    public boolean getPatientNotified() {
        return this.patientNotified;
    }

    /**
     * Getter method for the Doctor notification state of this Appointment
     * @return Doctor notification state of the Appointment (true if the patient has seen the Appointment notification, otherwise false)
     */
    public boolean getDoctorNotified() {
        return this.doctorNotified;
    }
    
    /**
     * Method to set the notification state for the Doctor
     * @param notified true or false
     */
    public void setDoctorNotified(boolean notified) {      
        this.doctorNotified = notified;
    }
    
    /**
     * Method to set the notification state for the patient
     * @param notified true or false
     */
    public void setPatientNotified(boolean notified) {
        this.patientNotified = notified;
    }
    
    @Override
    public String toPersistableTxtFormat() {
        return String.format("%s,%s,%s,%s,%b,%b", this.uuid, this.doctorUUID, this.patientUUID, sdf.format(this.date), this.patientNotified, this.doctorNotified);
    }
    
}
