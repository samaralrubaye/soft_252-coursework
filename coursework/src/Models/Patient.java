/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Class for creating Patient Users
 */
public class Patient extends User {
    private final String gender;
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
    public Patient(String uuid, String givenName, String lastName, String address, String gender, String password, int age) {
        super(uuid, givenName, lastName, address, password);
        this.gender = gender;
        this.age = age;
    }
    
    /**
     * Factory method for creating a new Patient from it's persisted text format
     * @param txtFormat - the persisted text format
     * @return new Patient
     */
    public static Patient newPatient(String txtFormat) {
        String[] tokens = txtFormat.split("~");
        System.out.println("tokens: " + tokens.length);
        return new Patient(
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
     * Getter method for the Patient age
     * @return age of the Patient
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Getter method for the gender of the Patient
     * @return gender of the Patient
     */
    public String getGender() {
        return this.gender;
    }

    @Override
    public String toPersistableTxtFormat() {
        return String.format("%s~%s~%s~%s~%s~%s~%d", this.uuid, this.givenName, this.lastName, this.address, this.gender, this.password, this.age);
    }
}
