/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * PatientHistory class represents an history of a Patients appointment, prescription and relevant doctors note
 */
public class PatientHistory implements IPersistable {
    private final String uuid;
    private final String appointmentUUID;
    private final String patientUUID;
    
    /**
     * Constructor for creating a new PatientHistory
     * @param uuid - unique identifier for this PatientHistory
     * @param patientUUID - unique identifier for the patient that owns this PatientHistory
     * @param appointmentUUID  - unique identifier for the Appointment of this PatientHistory
     */
    public PatientHistory(String uuid, String patientUUID, String appointmentUUID) {
        super();
        this.uuid = uuid;
        this.patientUUID = patientUUID;
        this.appointmentUUID = appointmentUUID;
    }
    
    
    /**
     * Factory method for creating a new PatientHistory for it's text format
     * @param txtFormat - text format for this PatientHistory
     * @return new PatientHistory
     */
    public static PatientHistory newPatientHistory(String txtFormat) {
        String[] tokens = txtFormat.split(",");
        return new PatientHistory(
                tokens[0],
                tokens[1],
                tokens[2]
        );
    }
    
    /**
     * Getter for the unique identifier for this PatientHistory
     * @return String representing this PatientHistory unique identifier
     */
    public String getUUID() {
        return this.uuid;
    }
  
    
    /**
     * Getter for the unique identifier of the Appointment associated to this PatientHistory
     * @return String representing the Appointment unique identifier
     */
    public String getAppointmentUUID() {
        return this.appointmentUUID;
    }
    
    /**
     * Getter for the unique identifier of the Patient who owns this PatientHistory
     * @return String representing the Patient unique identifier
     */
    public String getPatientUUID() {
        return this.patientUUID;
    }
    

    @Override
    public String toPersistableTxtFormat() {
        return String.format("%s,%s,%s", this.uuid, this.patientUUID, this.appointmentUUID);
    }
    
}
