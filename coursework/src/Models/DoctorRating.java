/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Class represents a Doctors rating, given by a Patient
 */
public class DoctorRating implements IPersistable {
    
    private final double rating;
    private final String doctorUUID;
    private final String patientUUID;
    private final String uuid;
    private final String comment;
    
    /**
     * Constructor for creating a new Doctor rating
     * @param uuid - unique identifier of the rating
     * @param patientUUID - unique identifier of the patient who gave the Doctor rating
     * @param doctorUUID - unique identifier of the doctor owning the rating
     * @param comment - comment about this rating
     * @param rating - rating value (1 - 5)
     */
    public DoctorRating(String uuid, String patientUUID, String doctorUUID, String comment, double rating) {
        super();
        this.uuid = uuid;
        this.doctorUUID = doctorUUID;
        this.rating = rating;
        this.patientUUID = patientUUID;
        this.comment = comment;
    }
    
    /**
     * Factory method for creating a new DoctorRating from its persisted text format
     * @param persistedTxt - String representing it's persisted text format
     * @return new DoctroRating object
     */
    public static DoctorRating newDoctorRating(String persistedTxt) {
        String[] tokens = persistedTxt.split(",");
        return new DoctorRating(
                tokens[0].trim(),
                tokens[1].trim(),
                tokens[2].trim(),
                tokens[3].trim(),
                Double.valueOf(tokens[4])
        );
    }
    
    /**
     * Getter for the unique identifier for the Doctor owning this rating
     * @return String representing the Doctor unique identifier
     */
    public String getDoctorUUID() {
        return this.doctorUUID;
    }
    
    /**
     * Getter for the unique identifier of the Patient who gave the DoctorRating
     * @return String representing the Patient unique identifier
     */
    public String getPatientUUID() {
        return this.patientUUID;
    }
    
    /**
     * Getter for the rating value
     * @return Integer representing the rating value
     */
    public double getRating() {
        return this.rating;
    }
    
    /**
     * Getter for the unique identifier of this rating
     * @return String representing the unique identifier of this rating
     */
    public String getUUID() {
        return this.uuid;
    }
    
    /**
     * Getter for the comment of this rating
     * @return comment associated with this rating
     */
    public String getComment() {
        return this.comment;
    }

    @Override
    public String toPersistableTxtFormat() {
        return String.format("%s~%s~%s~%s~%f", this.uuid, this.patientUUID, this.uuid, this.comment, this.rating);
    }
    
}
