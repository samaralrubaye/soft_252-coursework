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
     */
    public Doctor(String uuid, String givenName, String lastName, String address) {
        super(uuid, givenName, lastName, address);
    }

    /**
     * Factory method for creating a new Doctor from it's persisted text format
     * @param txtFormat - the persisted text format
     * @return new Patient
     */
    private static Doctor newDoctor(String txtFormat) {
        String[] tokens = txtFormat.split(",");
        return new Doctor(
                tokens[0],
                tokens[1],
                tokens[2],
                tokens[3]
        );
    }

    @Override
    public String toPersistableTxtFormat() {
        return String.format("%s,%s,%s,%s", this.uuid, this.givenName, this.lastName, this.address);
    }
}
