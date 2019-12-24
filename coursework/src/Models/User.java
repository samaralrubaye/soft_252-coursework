/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Applying Decorator pattern by creating a
 * base abstract User class for all other types of Users in the system
 */
public abstract class User implements IPersistable {
    protected String givenName;
    protected String lastName;
    protected String address;
    protected String uuid;
    
    /**
     * Constructor for creating a user
     * @param givenName - user given name
     * @param lastName - user last name
     * @param address - user address
     * @param uuid - user unique identification number
     */
    public User(String uuid, String givenName, String lastName, String address) {
        this.givenName = givenName;
        this.lastName = lastName;
        this.address = address;
        this.uuid = uuid;
    }
    
    /**
     * Method to be implemented by all concrete user classes. It defines how the user should be persisted as a .txt file
     *
     * @return String representing the format that this Object should be persisted as String
     */
    @Override
    public abstract String toPersistableTxtFormat();
}
