/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import models.AccountTerminationRequest;
import models.Secretary;
import models.Doctor;
import models.MedicineOrder;
import models.PatientHistory;
import models.DatastoreHelper;
import models.PrescriptionMedicine;
import models.DoctorRating;
import models.PatientCreationRequest;
import models.AppointmentRequest;
import models.Patient;
import models.Appointment;
import models.Medicine;
import models.Prescription;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.Administrator;

/**
 *
 * Controller Class
 */
public class Controller {
    private DatastoreHelper modelHelper;
    
    public Controller(DatastoreHelper dh) {
        super();
        this.modelHelper = dh;
    }
    
    public Administrator loginAdmin(String userID, String password) {
        return this.modelHelper.getAdmin(userID, password);
    }
    
    public Patient loginPatient(String userID, String password) {
        return this.modelHelper.getPatient(userID, password);
    }
    
    public Secretary loginSecretary(String userID, String password) {
        return this.modelHelper.getSecretary(userID, password);
    }
    
    public Doctor loginDoctor(String userID, String password) {
        return this.modelHelper.getDoctor(userID, password);
    }
    
    public List<Doctor> getDoctorsList() {
        return new ArrayList<>(this.modelHelper.getDoctors().values());
    }
    
    public double getDoctorRating(String doctorUUID) {
        int totalRecords = 0;
        double totalRating = 0;
        for (Map.Entry<String, DoctorRating> rating : this.modelHelper.getDoctorRatings().entrySet()) {
            if (rating.getValue().getDoctorUUID().equals(doctorUUID))  {
                totalRecords++;
                totalRating += rating.getValue().getRating();
            }
        }
        
        if (totalRecords == 0 || totalRating == 0) {
            return 0;
        }
        
        return (totalRating / totalRecords);
    }
    
    public void persistData() {
        this.modelHelper.persistAllData();
    }

    public void submitAppointmentRequest(AppointmentRequest ar) {
        this.modelHelper.saveAppointmentRequest(ar);
    }

    public void requestAccountTermination(String uuid) {
        this.modelHelper.saveAccountTerminationRequest(new AccountTerminationRequest(uuid, false));
    }

    public Prescription getPrescription(String prescriptionUUID) {
        return this.modelHelper.getPrescriptions().get(prescriptionUUID);
    }

    public List<PatientHistory> getPatientHistoryList(String uuid) {
        List<PatientHistory> list = new ArrayList<>();
        for (Map.Entry<String, PatientHistory> record : this.modelHelper.getPatientHistories().entrySet()) {
            if (record.getValue().getPatientUUID().equals(uuid)) {
                list.add(record.getValue());
            }
        }
        return list;
    }

    public List<Appointment> getPatientAppointmentList(String uuid) {
        List<Appointment> list = new ArrayList<>();
        for (Map.Entry<String, Appointment> record : this.modelHelper.getAppointments().entrySet()) {
            if (record.getValue().getPatientUUID().equals(uuid)) {
                list.add(record.getValue());
            }
        }
        return list;
    }

    public Doctor getDoctor(String doctorUUID) {
        return this.modelHelper.getDoctors().get(doctorUUID);
    }

    public Administrator createAdmin(String givenName, String lastName, String address, String password) {
        return this.modelHelper.createAdministrator(givenName, lastName, address, password);
    }

    public List<Secretary> getSecretariesList() {
        return new ArrayList<>(this.modelHelper.getSecretaries().values());
    }

    public void deleteDoctor(String uuid) {
        this.modelHelper.deleteDoctor(uuid);
    }

    public void deleteSecretary(String uuid) {
        this.modelHelper.deleteSecretary(uuid);
    }

    public void createDoctor(String givenName, String lastName, String address, String password) {
        this.modelHelper.createDoctor(givenName, lastName, address, password);
    }

    public void createSecretary(String givenName, String lastName, String address, String password) {
        this.modelHelper.createSecretary(givenName, lastName, address, password);
    }

    public Patient getPatient(String patientUUID) {
        return this.modelHelper.getPatients().get(patientUUID);
    }

    public List<DoctorRating> getDoctorRatings(String docUUID) {
        List<DoctorRating> list = new ArrayList<>();
        for (Map.Entry<String, DoctorRating> record : this.modelHelper.getDoctorRatings().entrySet()) {
            if (record.getValue().getDoctorUUID().equals(docUUID)) {
                list.add(record.getValue());
            }
        }
        return list;
    }

    public void createAdministratorFeedback(String doctorUUID, String feedback) {
        this.modelHelper.createAdministratorFeedback(doctorUUID, feedback);
    }

    public void createPatientCreationRequest(String givenName, String lastName, String address,  String password, String gender, int age) {
        this.modelHelper.createPatientCreationRequest(givenName, lastName, address, password, gender, age);           
    }

    public void loadAllData() {
        this.modelHelper.loadAllData();
    }

    public List<PatientCreationRequest> getPatientCreationRequestList() {
        List<PatientCreationRequest> list = new ArrayList<>();
        for (Map.Entry<String, PatientCreationRequest> record : this.modelHelper.getPatientCreationRequests().entrySet()) {
            list.add(record.getValue());
        }
        return list;
    }

    public void approvePatientAccountCreation(PatientCreationRequest pcr) {
        this.modelHelper.createPatient(pcr);
    }

    public List<AccountTerminationRequest> getAccountTerminationRequests() {
        return this.modelHelper.getAccountTerminationRequests();
    }

    public void approvePatientAccountRemoval(AccountTerminationRequest atr) {
        this.modelHelper.removePatientAccount(atr);
    }

    public List<Patient> getPatientList() {
        return new ArrayList<>(this.modelHelper.getPatients().values());
    }

    public void removePatient(String uuid) {
        this.modelHelper.removePatientAccount(uuid);
    }

    public List<AppointmentRequest> getOpenAppointmentRequests() {
        List<AppointmentRequest> list = new ArrayList<>();
        for (AppointmentRequest record : this.modelHelper.getAppointmentRequests()) {
            if(!record.isApproved()) {
                list.add(record);
            }
        }
        return list;
    }

    public void registerAppointmentFromRequest(AppointmentRequest ar) {
        this.modelHelper.registerAppointmentFromRequest(ar);
    }

    public List<Appointment> getDoctorAppointmentList(String uuid) {
        List<Appointment> list = new ArrayList<>();
        for (Map.Entry<String, Appointment> record : this.modelHelper.getAppointments().entrySet()) {
            if (record.getValue().getDoctorUUID().equals(uuid)) {
                list.add(record.getValue());
            }
        }
        return list;
    }

    public void submitMedicineOrder(String docUUID, String name, int qty, String description) {
        this.modelHelper.createMedicineOrder(docUUID, name, qty, description);
    }

    public List<MedicineOrder> getMedicineOrders() {
        return new ArrayList<>(this.modelHelper.getMedicineOrders().values());
    }

    public void approveMedicineOrder(MedicineOrder mo) {
        this.modelHelper.processMedicineOrder(mo);
    }

    public void createPatientHistory(Appointment a) {
        this.modelHelper.createPatientHistory(a);
    }

    public Appointment getAppointment(String appointmentUUID) {
        return this.modelHelper.getAppointments().get(appointmentUUID);
    }
    
    public List<Medicine> getMedicines() {
        return new ArrayList<>(this.modelHelper.getMedicines().values());
    }

    public void submitDoctorPrescriptionMedicine(String medicineUUID, String appointmentUUID, int qty) {
        this.modelHelper.submitDoctorPrescriptionMedicine(medicineUUID, appointmentUUID, qty);
    }

    public Medicine getMedicine(String medicineUUID) {
        return this.modelHelper.getMedicines().get(medicineUUID);
    }

    public List<PrescriptionMedicine> getPrescriptions() {
        return new ArrayList<>(this.modelHelper.getPrescriptionMedicines().values());
    }

    public void approveMedicinePrescriptionCollection(String uuid) {
        this.modelHelper.approvePrescriptionMedicineCollection(uuid);
    }

    public void submitPatientAppointmentRating(String patientUUID, String doctorUUID, String comments, double rating) {
        this.modelHelper.submitPatientAppointmentRating(patientUUID, doctorUUID, comments, rating);
    }
}
