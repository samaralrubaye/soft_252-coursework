/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;
import models.Administrator;
import models.*;

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
}
