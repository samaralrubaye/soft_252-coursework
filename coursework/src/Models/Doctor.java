/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Class for creating a Doctor object
 */
public class Doctor extends User {
    /**
     * Constructor for creating a Doctor User
     * @param givenName - user given name
     * @param lastName - user last name
     * @param address - user address
     * @param uuid - user unique identification number
     * @param password - user Password
     */
    public Doctor(String uuid, String givenName, String lastName, String address, String password) {
        super(uuid, givenName, lastName, address, password);
    }

    /**
     * Factory method for creating a new Doctor from it's persisted text format
     * @param txtFormat - the persisted text format
     * @return new Patient
     */
    public static Doctor newDoctor(String txtFormat) {
        String[] tokens = txtFormat.split("~");
        return new Doctor(
                tokens[0].trim(),
                tokens[1].trim(),
                tokens[2].trim(),
                tokens[3].trim(),
                tokens[4].trim()
        );
    }

    @Override
    public String toPersistableTxtFormat() {
        return String.format("%s~%s~%s~%s~%s", this.uuid, this.givenName, this.lastName, this.address, this.password);
    }
}
