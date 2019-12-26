/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Class for handling all data persistence related tasks
 * It uses Singleton pattern to ensure there's only one instance of it at runtime
 */
public class DatastoreHelper {
    private static DatastoreHelper datastoreHelperSingleton;
    
    public static final String doctorTxt = "doctor.txt";
    public static final String administratorTxt = "administrator.txt";
    public static final String administratorFeedbackTxt = "administrator_feedback.txt";
    public static final String appointmentTxt = "appointment.txt";
    public static final String doctorRatingTxt = "doctor_rating.txt";
    public static final String medicineTxt = "medicine.txt";
    public static final String medicineOrderTxt = "medicine_order.txt";
    public static final String patientTxt = "patient.txt";
    public static final String patientHistoryTxt = "patient_history.txt";
    public static final String prescriptionTxt = "prescription.txt";
    public static final String prescriptionMedicineTxt = "prescription_medicine.txt";
    public static final String secretaryTxt = "secretary.txt";
    
    /**
     * Map of all Administrators in the application. It maps their unique id to the actual Administrator for easier searching
     */
    private HashMap<String, Administrator> administrators;
    
    /**
     * Map of all Secretary in the application. It maps their unique id to the actual Secretary for easier searching
     */
    private HashMap<String, Secretary> secretaries;
    
    /**
     * Map of all Doctors in the application. It maps their unique id to the actual Doctor for easier searching
     */
    private HashMap<String, Doctor> doctors;
    
    /**
     * Map of all Patients in the application. It maps their unique id to the actual Patient for easier searching
     */
    private HashMap<String, Patient> patients;
    
    
    /**
     * Map of all AdministratorFeedback in the application. It maps their unique id to the actual AdministratorFeedback for easier searching
     */
    private HashMap<String, AdministratorFeedback> administratorFeedback;
    
    
    /**
     * Map of all Appointment in the application. It maps their unique id to the actual Appointment for easier searching
     */
    private HashMap<String, Appointment> appointments;
    
    
    /**
     * Map of all DoctorRating in the application. It maps their unique id to the actual DoctorRating for easier searching
     */
    private HashMap<String, DoctorRating> doctorRatings;
    
    /**
     * Map of all Medicine in the application. It maps their unique id to the actual Medicine for easier searching
     */
    private HashMap<String, Medicine> medicines;
    
    /**
     * Map of all MedicineOrder in the application. It maps their unique id to the actual MedicineOrder for easier searching
     */
    private HashMap<String, MedicineOrder> medicineOrders;
    
    /**
     * Map of all PatientHistory in the application. It maps their unique id to the actual PatientHistory for easier searching
     */
    private HashMap<String, PatientHistory> patientHistories;
    
    
    /**
     * Map of all Prescription in the application. It maps their unique id to the actual Prescription for easier searching
     */
    private HashMap<String, Prescription> prescriptions;
    
    /**
     * Map of all PrescriptionMedicine in the application. It maps their unique id to the actual PrescriptionMedicine for easier searching
     */
    private HashMap<String, PrescriptionMedicine> prescriptionMedicines;
    
    /**
     * Private Constructor to ensure that it cannot be instantiated directly
     */
    private DatastoreHelper() {}
    
    /**
     * Method to get the singleton instance of this DatastoreHelper
     * it lazy loads the instance by only creating it once when this method is called and 
     * the instance is found to be null
     * @return the singleton instance of this DatastoreHelper
     */
    public static DatastoreHelper getSingletonInstance() {
        if (null == datastoreHelperSingleton) {
            datastoreHelperSingleton = new DatastoreHelper();
            datastoreHelperSingleton.loadAllData();
        }
        
        return datastoreHelperSingleton;
    }

     /**
     * Getter method for the Administrator
     * @return Map of all Administrator
     */
    public HashMap<String, Administrator> getAdministrators() {
        return this.administrators;
    }

     /**
     * Getter method for the Secretary
     * @return Map of all Secretary
     */
    public HashMap<String, Secretary> getSecretaries() {
        return this.secretaries;
    }
    
    /**
     * Getter method for the Doctors
     * @return Map of all Doctor
     */
    public HashMap<String, Doctor> getDoctors() {
        return this.doctors;
    }
    
    /**
     * Getter method for the Patients
     * @return Map of all Patient
     */
    public HashMap<String, Patient> getPatients() {
        return this.patients;
    }

    /**
     * Getter method for the AdministratorFeedback
     * @return Map of all AdministratorFeedback
     */
    public HashMap<String, AdministratorFeedback> getAdministratorFeedback() {
        return this.administratorFeedback;
    }
 
    /**
     * Getter method for the Appointments
     * @return Map of all Appointment
     */
    public HashMap<String, Appointment> getAppointments() {
        return this.appointments;
    }

    /**
     * Getter method for the DoctorRating
     * @return Map of all DoctorRating
     */
    public HashMap<String, DoctorRating> getDoctorRatings() {
        return this.doctorRatings;
    }
    
    /**
     * Getter method for the Medicines
     * @return Map of all Medicine
     */
    public HashMap<String, Medicine> getMedicines() {
        return this.medicines;
    }

    /**
     * Getter method for the MedicineOrders
     * @return Map of all MedicineOrders
     */
    public HashMap<String, MedicineOrder> getMedicineOrders() {
        return this.medicineOrders;
    }

    /**
     * Getter method for the PatientHistory
     * @return Map of all PatientHistory
     */
    public HashMap<String, PatientHistory> getPatientHistories() {
        return this.patientHistories;
    }

    /**
     * Getter method for the Prescription
     * @return Map of all Prescription
     */
    public HashMap<String, Prescription> getPrescriptions() {
        return this.prescriptions;
    }

    /**
     * Getter method for the PrescriptionMedicines
     * @return Map of all PrescriptionMedicine
     */
    public HashMap<String, PrescriptionMedicine> getPrescriptionMedicines() {
        return this.prescriptionMedicines;
    }
    
    /**
     * Helper method for saving a list of Persistable objects
     * @param fileName - name of the file where the objects are written to.
     * @param persistables - the IPersistable list to be saved.
     */
    public void savePersistables(String fileName, List<IPersistable> persistables) {
        try {
            FileWriter fWriter = new FileWriter(fileName, false); //overwrites file
            BufferedWriter bWriter = new BufferedWriter(fWriter);
            for(IPersistable p : persistables) {
                String txt = p.toPersistableTxtFormat();
                System.out.println("Writing " + txt + " to " + fileName);
                bWriter.write(txt + "\n");
            }
            bWriter.close();
            
        } catch (IOException ex) {
            Logger.getLogger(DatastoreHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Method to load all Administrator from file
     * @return Map of unique identifier -> Administrator
     */
    public Map<String, Administrator> readAdministrators() throws FileNotFoundException {
        this.administrators = new HashMap<>();
        Scanner sc = new Scanner(new File(administratorTxt));
        while(sc.hasNextLine()) {
            String txtFormat = sc.nextLine();
            if (!txtFormat.trim().isEmpty()) {
                Administrator admin = Administrator.newAdministrator(txtFormat);
                this.administrators.put(admin.getUUID(), admin);
            }
        }
        
        return this.administrators;
    }
    
    /**
     * Method to load all AdministratorFeedback from file
     * @return Map of unique identifier -> AdministratorFeedback
     */
    public Map<String, AdministratorFeedback> readAdministratorFeedback() throws FileNotFoundException {
        this.administratorFeedback = new HashMap<>();
        Scanner sc = new Scanner(new File(administratorFeedbackTxt));
        while(sc.hasNextLine()) {
            String txtFormat = sc.nextLine();
            if (!txtFormat.trim().isEmpty()) {
                AdministratorFeedback af = AdministratorFeedback.newAdministratorFeedback(txtFormat);
                this.administratorFeedback.put(af.getUUID(), af);
            }
        }
        
        return this.administratorFeedback;
    }
    
    /**
     * Method to load all Appointment from file
     * @return Map of unique identifier -> Appointment
     */
    public Map<String, Appointment> readAppointments() throws FileNotFoundException {
        this.appointments = new HashMap<>();
        Scanner sc = new Scanner(new File(appointmentTxt));
        while(sc.hasNextLine()) {
            String txtFormat = sc.nextLine();
            if (!txtFormat.trim().isEmpty()) {
                Appointment ap = Appointment.newAppointment(txtFormat);
                this.appointments.put(ap.getUUID(), ap);
            }
        }
        
        return this.appointments;
    }
    
    /**
     * Method to load all Doctor from file
     * @return Map of unique identifier -> Doctor
     */
    public Map<String, Doctor> readDoctors() throws FileNotFoundException {
        this.doctors = new HashMap<>();
        Scanner sc = new Scanner(new File(doctorTxt));
        while(sc.hasNextLine()) {
            String txtFormat = sc.nextLine();
            if (!txtFormat.trim().isEmpty()) {
                Doctor d = Doctor.newDoctor(txtFormat);
                this.doctors.put(d.getUUID(), d);
            }
        }
        
        return this.doctors;
    }
    
    /**
     * Method to load all DoctorRating from file
     * @return Map of unique identifier -> DoctorRating
     */
    public Map<String, DoctorRating> readDoctorRatings() throws FileNotFoundException {
        this.doctorRatings = new HashMap<>();
        Scanner sc = new Scanner(new File(doctorRatingTxt));
        while(sc.hasNextLine()) {
            String txtFormat = sc.nextLine();
            if (!txtFormat.trim().isEmpty()) {
                DoctorRating dr = DoctorRating.newDoctorRating(txtFormat);
                this.doctorRatings.put(dr.getUUID(), dr);
            }
        }
        
        return this.doctorRatings;
    } 
    
    /**
     * Method to load all Medicine from file
     * @return Map of unique identifier -> Medicine
     */
    public Map<String, Medicine> readMedicines() throws FileNotFoundException {
        this.medicines = new HashMap<>();
        Scanner sc = new Scanner(new File(medicineTxt));
        while(sc.hasNextLine()) {
            String txtFormat = sc.nextLine();
            if (!txtFormat.trim().isEmpty()) {
                Medicine m = Medicine.newMedicine(txtFormat);
                this.medicines.put(m.getUUID(), m);
            }
        }
        
        return this.medicines;
    } 
    
    /**
     * Method to load all MedicineOrder from file
     * @return Map of unique identifier -> MedicineOrder
     */
    public Map<String, MedicineOrder> readMedicineOrders() throws FileNotFoundException {
        this.medicineOrders = new HashMap<>();
        Scanner sc = new Scanner(new File(medicineOrderTxt));
        while(sc.hasNextLine()) {
            String txtFormat = sc.nextLine();
            if (!txtFormat.trim().isEmpty()) {
                MedicineOrder mo = MedicineOrder.newMedicineOrder(txtFormat);
                this.medicineOrders.put(mo.getUUID(), mo);
            }
        }
        
        return this.medicineOrders;
    }
    
    /**
     * Method to load all Patients from file
     * @return Map of unique identifier -> Patient
     */
    public Map<String, Patient> readPatients() throws FileNotFoundException {
        this.patients = new HashMap<>();
        Scanner sc = new Scanner(new File(patientTxt));
        while(sc.hasNextLine()) {
            String txtFormat = sc.nextLine();
            System.out.println("txtFormat: " + txtFormat);
            if (!txtFormat.trim().isEmpty()) {
                Patient p = Patient.newPatient(txtFormat);
                this.patients.put(p.getUUID(), p);
            }
        }
        
        return this.patients;
    }
    
    
    /**
     * Method to load all PatientHistory from file
     * @return Map of unique identifier -> PatientHistory
     */
    public Map<String, PatientHistory> readPatientHistories() throws FileNotFoundException {
        this.patientHistories = new HashMap<>();
        Scanner sc = new Scanner(new File(patientHistoryTxt));
        while(sc.hasNextLine()) {
            String txtFormat = sc.nextLine();
            if (!txtFormat.trim().isEmpty()) {
                PatientHistory ph = PatientHistory.newPatientHistory(txtFormat);
                this.patientHistories.put(ph.getUUID(), ph);
            }
        }
        
        return this.patientHistories;
    }
    
    
    /**
     * Method to load all Prescription from file
     * @return Map of unique identifier -> Prescription
     */
    public Map<String, Prescription> readPrescriptions() throws FileNotFoundException {
        this.prescriptions = new HashMap<>();
        Scanner sc = new Scanner(new File(prescriptionTxt));
        while(sc.hasNextLine()) {
            String txtFormat = sc.nextLine();
            if (!txtFormat.trim().isEmpty()) {
                Prescription p = Prescription.newPrescription(txtFormat);
                this.prescriptions.put(p.getUUID(), p);
            }
        }
        
        return this.prescriptions;
    }
    
    
    /**
     * Method to load all PrescriptionMedicine from file
     * @return Map of unique identifier -> PrescriptionMedicine
     */
    public Map<String, PrescriptionMedicine> readPrescriptionMedicines() throws FileNotFoundException {
        this.prescriptionMedicines = new HashMap<>();
        Scanner sc = new Scanner(new File(prescriptionMedicineTxt));
        while(sc.hasNextLine()) {
            String txtFormat = sc.nextLine();
            if (!txtFormat.trim().isEmpty()) {
                PrescriptionMedicine pm = PrescriptionMedicine.newPrescriptionMedicine(txtFormat);
                this.prescriptionMedicines.put(pm.getUUID(), pm);
            }
        }
        
        return this.prescriptionMedicines;
    }
    
    
    /**
     * Method to load all Secretary from file
     * @return Map of unique identifier -> Secretary
     */
    public Map<String, Secretary> readSecretaries() throws FileNotFoundException {
        this.secretaries = new HashMap<>();
        Scanner sc = new Scanner(new File(secretaryTxt));
        while(sc.hasNextLine()) {
            String txtFormat = sc.nextLine();
            if (!txtFormat.trim().isEmpty()) {
                Secretary s = Secretary.newSecretary(txtFormat);
                this.secretaries.put(s.getUUID(), s);
            }
        }
        
        return this.secretaries;
    }
    
    /**
     * Method to load all the data from the text files into memory
     */
    public void loadAllData() {
        try {
            this.readPatients();
            this.readDoctors();
            this.readSecretaries();
            this.readAdministrators();
            this.readAppointments();
            this.readMedicines();
            this.readPrescriptions();
            this.readMedicineOrders();
            this.readPatientHistories();
            this.readDoctorRatings();
            this.readAdministratorFeedback();
        } catch (FileNotFoundException fnfe) {
            System.out.println("fnfe: " + fnfe);
        }
    }

    /**
     * Method gets an Administrator by userID and password
     * @param userID - unique id of the administrator we want
     * @param password - password of the administrator we want
     * @return an Administrator object or null if an Administrator with the user id and password doesn't exist
     */
    public Administrator getAdmin(String userID, String password) {
        Administrator admin = this.administrators.get(userID);
        if (admin != null & admin.getPassword().equals(password)) {
            return admin;
        }
        
        return null;
    }

    /**
     * Method gets a Doctor by userID and password
     * @param userID - unique id of the administrator we want
     * @param password - password of the administrator we want
     * @return a Doctor object or null if a Doctor with the user id and password doesn't exist
     */
    public Doctor getDoctor(String userID, String password) {
        Doctor doctor = this.doctors.get(userID);
        if (doctor != null & doctor.getPassword().equals(password)) {
            return doctor;
        }
        
        return null;
    }

    /**
     * Method gets a Secretary by userID and password
     * @param userID - unique id of the administrator we want
     * @param password - password of the administrator we want
     * @return an Secretary object or null if a Secretary with the user id and password doesn't exist
     */
    public Secretary getSecretary(String userID, String password) {
        Secretary sec = this.secretaries.get(userID);
        if (sec != null & sec.getPassword().equals(password)) {
            return sec;
        }
        
        return null;
    }

    /**
     * Method gets a Patient by userID and password
     * @param userID - unique id of the administrator we want
     * @param password - password of the administrator we want
     * @return a Patient object or null if a Patient with the user id and password doesn't exist
     */
    public Patient getPatient(String userID, String password) {
        Patient patient = this.patients.get(userID);
        if (patient != null & patient.getPassword().equals(password)) {
            return patient;
        }
        
        return null;
    }
    
}
