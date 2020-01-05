/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Objects;

/**
 *
 * Class for creating AccountTerminationRequest objects
 */
public class AccountTerminationRequest implements IPersistable {
    private final String patientUUID;
    private final boolean approved;
    
    /**
     * Constructor for creating a new AccountTerminationRequestObject
     * @param patientUUID - unique identification of the patient who's account should be terminated
     * @param approved - boolean indicating if the approval has been granted
     */
    public AccountTerminationRequest(String patientUUID, boolean approved) {
        super();
        this.patientUUID = patientUUID;
        this.approved = approved;
    }
    
    /**
     * Method to create an new AccountTerminationRequest object from it's persisted text format
     * @param txtFormat - text format to create the AccountTerminationObject from
     * @return new AccountTerminationObject
     */
    public static AccountTerminationRequest newAccountTerminationRequest(String txtFormat) {
        String[] tokens = txtFormat.split(",");
        return new AccountTerminationRequest(
                tokens[0],
                Boolean.valueOf(tokens[1])
        );
    }
    
    /**
     * Getter method for the patient unique identification number
     * @return the unique identification number of the patient who's account should be terminated
     */
    public String getPatientUUID() {
        return this.patientUUID;
    }

    /**
     * Getter method of the approval status of the account termination request
     * @return true if the Account has been terminated, otherwise false
     */
    public boolean isApproved() {
        return this.approved;
    }

    @Override
    public String toPersistableTxtFormat() {
        return String.format("%s,%b", this.patientUUID, this.approved);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        
        AccountTerminationRequest atr = (AccountTerminationRequest)obj;
        return atr.getPatientUUID().equals(this.patientUUID);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.patientUUID);
        return hash;
    }
}
