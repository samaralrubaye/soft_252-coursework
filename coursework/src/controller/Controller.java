/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import models.Administrator;
import models.*;
import view.MainFrame;

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
        int totalRating = 0;
        for (Map.Entry<String, DoctorRating> rating : this.modelHelper.getDoctorRatings().entrySet()) {
            if (rating.getKey().equals(doctorUUID))  {
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
            if (record.getKey().equals(uuid)) {
                list.add(record.getValue());
            }
        }
        return list;
    }

    public List<Appointment> getPatientAppointmentList(String uuid) {
        List<Appointment> list = new ArrayList<>();
        for (Map.Entry<String, Appointment> record : this.modelHelper.getAppointments().entrySet()) {
            if (record.getKey().equals(uuid)) {
                list.add(record.getValue());
            }
        }
        return list;
    }

    public Doctor getDoctor(String doctorUUID) {
        return this.modelHelper.getDoctors().get(doctorUUID);
    }
}
