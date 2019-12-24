/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Class for creating a MedicineOrder
 */
public class MedicineOrder implements IPersistable {
    private final String doctorUUID;
    private final String uuid;
    private final String medicineName;
    private final String medicineDescription;
    private final int quantity;
    private boolean completed;
    
    /**
     * Constructor for a new MedicineOrder
     * @param uuid - unique identifier of this MedicineOrder
     * @param doctorUUID - unique identifier of the Doctor that created this MedicineOrder
     * @param medicineName - name of the medicine
     * @param medicineDescription - description of the medicine
     * @param quantity - quantity of the medicine to order
     * @param completed - status of this medicine order (completed or still pending?)
     */
    public MedicineOrder(String uuid, String doctorUUID, String medicineName, String medicineDescription, int quantity, boolean completed) {
        super();
        this.uuid = uuid;
        this.doctorUUID = doctorUUID;
        this.medicineDescription = medicineDescription;
        this.medicineName = medicineName;
        this.quantity = quantity;
        this.completed = completed;
    }
    
    /**
     * Factory method to create an new MedicineOrder from it's persisted text format
     * @param txtFormat - persisted text format of this MedicineOrder
     * @return a new MedicineOrder
     */
    public static MedicineOrder newMedicineOrder(String txtFormat) {
        String[] tokens = txtFormat.split("~");
        return new MedicineOrder(
                tokens[0],
                tokens[1],
                tokens[2],
                tokens[3],
                Integer.valueOf(tokens[4]),
                Boolean.valueOf(tokens[5])
        );
    }
    
    /**
     * Getter for the unique identifier of the Doctor that created this MedicineOrder
     * @return Doctor unique identifier
     */
    public String getDoctorUUID() {
        return this.doctorUUID;
    }

    /**
     * Getter for the unique identifier of the MedicineOrder
     * @return unique identifier of this MedicineOrder
     */
    public String getUUID() {
        return this.uuid;
    }

    /**
     * Getter for the Medicine name
     * @return the Medicine name
     */
    public String getMedicineName() {
        return this.medicineName;
    }

    /**
     * Getter for the Medicine description
     * @return the Medicine description
     */
    public String getMedicineDescription() {
        return this.medicineDescription;
    }

    /**
     * Getter for the quantity of this Medicine to Order
     * @return the quantity to order
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Getter for the status of this MedicineOrder
     * @return true if the order is completed, otherwise it returns false.
     */
    public boolean isCompleted() {
        return this.completed;
    }
    
    /**
     * Setter for the completion status of this MedicineOrder
     * @param completed new completion status
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toPersistableTxtFormat() {
        return String.format("%s~%s~%s~%s~%d~%b", this.uuid, this.doctorUUID, this.medicineName, this.medicineDescription, this.quantity, this.completed);
    }
    
}
