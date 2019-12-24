/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Prescription class for creating a Prescription for a Doctor to a Patient during an Appointment
 */
public class Prescription implements IPersistable {
    private final String uuid;
    private final String patientUUID;
    private final String doctorUUID;
    private final String description;
    private final String appointmentUUID;
    
    public Prescription(String uuid, String patientUUID, String doctorUUID, String appointmentUUID, String description) {
        super();
        this.uuid = uuid;
        this.patientUUID = patientUUID;
        this.doctorUUID = doctorUUID;
        this.appointmentUUID = appointmentUUID;
        this.description = description;
    }
    
    public static Prescription newPrescription(String txtFormat) {
        String[] tokens = txtFormat.split("~");
        return new Prescription(
                tokens[0].trim(),
                tokens[1].trim(),
                tokens[2].trim(),
                tokens[3].trim(),
                tokens[4].trim()
        );
    }
    
    /**
     * Getter for the unique identifier of this Prescription
     * @return the unique identifier for this Prescription
     */
    public String getUuid() {
        return this.uuid;
    }

    /**
     * Getter for the unique identifier for the Patient that owns this Prescription
     * @return unique identifier for the patient that owns this prescription
     */
    public String getPatientUUID() {
        return this.patientUUID;
    }

    /**
     * Getter for the unique identifier for the Doctor that wrote this prescription
     * @return unique identifier of the Doctor that made the prescription
     */
    public String getDoctorUUID() {
        return this.doctorUUID;
    }

    /**
     * Getter for the description of this Prescription
     * @return the description of this Prescription
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter for the unique identifier of the Appointment of this Prescription
     * @return the unique identifier for the Appointment associated to this Prescription
     */
    public String getAppointmentUUID() {
        return this.appointmentUUID;
    }

    @Override
    public String toPersistableTxtFormat() {
        return String.format("%s~ %s~ %s~ %s~ %s", this.uuid, this.patientUUID, this.doctorUUID, this.appointmentUUID, this.description);
    }
    
}
