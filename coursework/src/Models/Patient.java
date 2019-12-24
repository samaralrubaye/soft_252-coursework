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
     */
    public Patient(String uuid, String givenName, String lastName, String address, String gender, int age) {
        super(uuid, givenName, lastName, address);
        this.gender = gender;
        this.age = age;
    }
    
    /**
     * Factory method for creating a new Patient from it's persisted text format
     * @param txtFormat - the persisted text format
     * @return new Patient
     */
    private static Patient newPatient(String txtFormat) {
        String[] tokens = txtFormat.split(",");
        return new Patient(
                tokens[0],
                tokens[1],
                tokens[2],
                tokens[3],
                tokens[4],
                Integer.valueOf(tokens[5])
        );
    }

    @Override
    public String toPersistableTxtFormat() {
        return String.format("%s,%s,%s,%s,%s,%d", this.uuid, this.givenName, this.lastName, this.address, this.gender, this.age);
    }
}
