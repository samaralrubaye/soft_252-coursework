/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Class for creating PatientCreationRequest objects
 */
public class PatientCreationRequest implements IPersistable {
    private final String uuid;
    private final String givenName;
    private final String lastName;
    private final String address;
    private final String gender;
    private final String password;
    private final int age;
    /**
     * Constructor for creating a Patient User
     * @param givenName - Patient given name
     * @param lastName - Patient last name
     * @param address - Patient address
     * @param uuid - Patient unique identification number
     * @param age - Age of this Patient
     * @param gender - Gender of this patient
     * @param password - Password of this User
     */
    public PatientCreationRequest(String uuid, String givenName, String lastName, String address, String gender, String password, int age) {
        this.uuid = uuid;
        this.givenName = givenName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
        this.age = age;
        this.password = password;
    }
    
    /**
     * Factory method for creating a new Patient from it's persisted text format
     * @param txtFormat - the persisted text format
     * @return new Patient
     */
    public static PatientCreationRequest newPatientCreationRequest(String txtFormat) {
        String[] tokens = txtFormat.split("~");
        System.out.println("tokens: " + tokens.length);
        return new PatientCreationRequest(
                tokens[0].trim(),
                tokens[1].trim(),
                tokens[2].trim(),
                tokens[3].trim(),
                tokens[4].trim(),
                tokens[5].trim(),
                Integer.valueOf(tokens[6])
        );
    }
    
    /**
     * Method to get the Patient of this PatientCreationRequest but with an already generated unique identification number
     * @param uuid - the unique identification number the created patient would have
     * @return Patient from this PatientCreationRequest
     */
    public Patient getPatient(String uuid) {
        return new Patient(uuid, this.givenName, this.lastName, this.address, this.gender, this.password, this.age);
    }
    
    /**
     * Getter method for the PatientCreationRequest password
     * @return - String representing the PatientCreationRequest password 
     */
    public String getPassword() {
        return this.password;
    }
    
    
    /**
     * Getter method for the given name of the PatientCreationRequest
     * @return given name of the PatientCreationRequest
     */
    public String getGivenName() {
        return this.givenName;
    }

    /**
     * Getter method for the last name of the PatientCreationRequest
     * @return last name of the PatientCreationRequest
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Getter method for the PatientCreationRequest address
     * @return address of the PatientCreationRequest
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Getter method for the gender of the PatientCreationRequest
     * @return gender of the PatientCreationRequest
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * Getter method for the PatientCreationRequest age
     * @return age of the PatientCreationRequest
     */
    public int getAge() {
        return this.age;
    }
    
    /**
     * Getter method for the PatientCreationRequest unique identifier
     * @return the unique identifier for the PatientCreationRequest
     */
    public String getUUID() {
        return this.uuid;
    }

    @Override
    public String toPersistableTxtFormat() {
        return String.format("%s~%s~%s~%s~%s~%s~%d", this.uuid, this.givenName, this.lastName, this.address, this.gender, this.password, this.age);
    }
}
