/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Class represents an Administrator feedback to a Doctor as seen from Patients feedback
 */
public class AdministratorFeedback implements IPersistable {
    private final String uuid;
    private final String doctorUUID;
    private final String feedback;
    private boolean delivered;
    
    /**
     * AdministratorFeedback constructor
     * @param uuid - unique identifier for this Administrator feedback
     * @param delivered - boolean specifying if this feedback is already delivered/read
     * @param feedback - the feedback text
     * @param doctorUUID - the unique identifier of the doctor who owns this feedback
     */
    public AdministratorFeedback(String uuid, String doctorUUID, boolean delivered, String feedback) {
        this.uuid = uuid;
        this.delivered = delivered;
        this.feedback = feedback;
        this.doctorUUID = doctorUUID;
    }
    
    /**
     * Factory method for creating an AdministratorFeedback from it's persisted format
     * @param txtFormat - String of text representing an Administrator
     * @return new AdministratorFeedback
     */
    public static AdministratorFeedback newAdministratorFeedback(String txtFormat) {
        String[] tokens = txtFormat.split(",");
        return new AdministratorFeedback(
                tokens[0], 
                tokens[1], 
                Boolean.valueOf(tokens[2]), 
                tokens[3]
        );
    }
    
    /**
     * Method to get the Feedback text
     * @return string representing the feedback;
     */
    public String getFeedback() {
        return this.feedback;
    }
    
    /**
     * Getter for the unique identifier of this AdministratorFeedback
     * @return String representing the unique identifier of this AdministratorFeedback
     */
    public String getUUID() {
        return this.uuid;
    }
    
    /**
     * Getter for the unique identifier of the doctor who owns this AdministratorFeedback
     * @return String representing the Doctor unique identifier;
     */
    public String getDoctorUUID() {
        return this.doctorUUID;
    }
    
    /**
     * Method to set the delivery status of this feedback
     * @param delivered boolean to be used as new delivery status
     */
    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    @Override
    public String toPersistableTxtFormat() {
        return String.format("%s,%s,%b,%s", this.uuid, this.doctorUUID, this.delivered, this.feedback);
    }
}
