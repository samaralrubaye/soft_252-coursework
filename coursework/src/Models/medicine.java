/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Class for creating a Medicine object
 */
public class Medicine implements IPersistable {
    private final String uuid;
    private final String doctorUUID;
    private int quantity;
    private final String name;
    private final String description;
    
    /**
     * Constructor for creating a new Appointment
     * @param uuid - unique identifier of the Medicine
     * @param doctorUUID - unique identifier of the Doctor that created the Medicine
     * @param name - name of the Medicine
     * @param description - description of the Medicine
     * @param quantity - quantity of the Medicine
     */
    public Medicine(String uuid, String doctorUUID, String name, String description, int quantity) {
        super();
        this.uuid = uuid;
        this.doctorUUID = doctorUUID;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }
    
    /**
     * Factory method for creating a new Medicine from it's persisted text format
     * @param txtFormat - persisted text format for this Medicine
     * @return new Medicine
     */
    public static Medicine newMedicine(String txtFormat) {
        String[] tokens = txtFormat.split("~");
        return new Medicine(
                tokens[0].trim(),
                tokens[1].trim(),
                tokens[2].trim(),
                tokens[3].trim(),
                Integer.valueOf(tokens[4].trim())
        );
    }
    
    
    /**
     * Getter method for the unique identifier of this Medicine
     * @return String representing the unique identifier of this Medicine
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Getter method for the unique identifier of the Doctor who created this medicine
     * @return return unique identifier of the Doctor that created the Medicine
     */
    public String getDoctorUUID() {
        return doctorUUID;
    }

    /**
     * Getter method for the quantity of this Medicine
     * @return integer representing the quantity of this Medicine left in stock
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Getter method for the name of this Medicine
     * @return the name of this Medicine
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for the description of this Medicine
     * @return the description of this Medicine
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Method for updating the quantity of this Medicine
     * @param difference the change to the quantity (could be negative in case of a dispensation or positive in case of a re-stock)
     */
    public void updateQuantity(int difference) {
        this.quantity += difference;
    }

    @Override
    public String toPersistableTxtFormat() {
        return String.format("%s~ %s~ %s~ %s~ %d", this.uuid, this.doctorUUID, this.name, this.description, this.quantity);
    }
    
}
