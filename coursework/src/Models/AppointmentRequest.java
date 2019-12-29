/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Class for creating Appointment Request objects
 */
public class AppointmentRequest implements IPersistable {
    private String doctorUUID;
    private String patientUUID;
    private Date date;
    private boolean approved;
    
    /**
     * Constructor for an AppointmentRequest
     * @param doctorUUID - the unique identifier of the Doctor requested for 
     * @param patientUUID - the unique identifier of the patient that made the request
     * @param date - the date the requested for the appointment
     * @param approved - status of the appointment (true if approved otherwise false)
     */
    public AppointmentRequest(String doctorUUID, String patientUUID, Date date, boolean approved) {
        super();
        this.doctorUUID = doctorUUID;
        this.patientUUID = patientUUID;
        this.date = date;
        this.approved = approved;
    }
    
    /**
     * Factory method for creating a new AppointmentRequest from it's persisted text format
     * @param txtFormat Persisted text format of this AppointmentRequest
     * @return new AppointmentRequest
     */
    public static AppointmentRequest newAppointmentRequest(String txtFormat) {
        String[] tokens = txtFormat.split(",");
        Date dt = null;
        try {
            dt = Appointment.sdf.parse(tokens[2]);
        } catch (ParseException ex) {
            Logger.getLogger(AppointmentRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new AppointmentRequest(
                tokens[0],
                tokens[1],
                dt,
                Boolean.valueOf(tokens[3])
        );
    }

    @Override
    public String toPersistableTxtFormat() {
        return String.format("%s,%s,%s,%b", this.doctorUUID, this.patientUUID, Appointment.sdf.format(this.date), this.approved);
    }
    
    /**
     * Getter method for the Doctor unique id
     * @return String representing the doctors unique id
     */
    public String getDoctorUUID() {
        return this.doctorUUID;
    }

    /**
     * Getter method for the Patient unique id
     * @return String representing the Patient unique id
     */
    public String getPatientUUID() {
        return this.patientUUID;
    }

    /**
     * Getter method for the Date suggested for the Appointment
     * @return Date suggested for the appointment
     */
    public Date getDate() {
        return this.date;
    }

    /**
     * Getter method for the approval status of the AppointmentRequest
     * @return true if the AppointmentRequest is approved by a secretary, otherwise it returns false;
     */
    public boolean isApproved() {
        return approved;
    }
}
