/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 * Class for creating a PrescriptionMedine
 */
public class PrescriptionMedicine implements IPersistable {
    private final String medicineUUID;
    private final String uuid;
    private final String prescriptionUUID;
    private final int medicineQuantity;
    
    /**
     * Constructor for creating a new PrescriptionMedicine
     * @param uuid - unique identifier of this PrescriptionMedicine
     * @param medicineUUID - unique identifier of the Medicine for this PrescriptionMedicine
     * @param prescriptionUUID - unique identifier of the Prescription for this PrescriptionMedicine
     * @param medicineQuantity - the quantity of the Medicine for this PrescriptionMedicine
     */
    public PrescriptionMedicine(String uuid, String medicineUUID, String prescriptionUUID, int medicineQuantity) {
        super();
        this.uuid = uuid;
        this.medicineUUID = medicineUUID;
        this.prescriptionUUID = prescriptionUUID;
        this.medicineQuantity = medicineQuantity;
    }
    
    /**
     * Factory method for creating a PrescriptionMedicine from it's persisted text format
     * @param txtFormat - the persisted text format
     * @return new PerscriptionMedicine
     */
    public static PrescriptionMedicine newPrescriptionMedicine(String txtFormat) {
        String[] tokens = txtFormat.split(",");
        return new PrescriptionMedicine(
                tokens[0],
                tokens[1],
                tokens[2],
                Integer.valueOf(tokens[3])
        );
    }

    /**
     * Getter method for the associated Medicine unique identifier
     * @return the associated Medicine unique identifier
     */
    public String getMedicineUUID() {
        return this.medicineUUID;
    }

    /**
     * Getter method for this PrescriptionMedicine unique identifier
     * @return the unique identifier for this PrescriptionMedicine
     */
    public String getUUID() {
        return this.uuid;
    }

    /**
     * Getter method for the associated Prescription unique identifier
     * @return the associated Prescription unique identifier
     */
    public String getPrescriptionUUID() {
        return prescriptionUUID;
    }

    /**
     * Getter method for the quantity of medicine to be given to the Patient
     * @return the quantity of medicine to be given to the Patient
     */
    public int getMedicineQuantity() {
        return this.medicineQuantity;
    }

    @Override
    public String toPersistableTxtFormat() {
        return String.format("%s,%s,%s%d", this.uuid, this.medicineUUID, this.prescriptionUUID, this.medicineQuantity);
    }
}
