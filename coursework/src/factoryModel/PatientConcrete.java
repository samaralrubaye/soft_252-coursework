/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryModel;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author engsa
 */
public class PatientConcrete extends usersConcrete {
    int Age;
    String gender;
    int ID_d=0000;
    char ID_s='P';

    public PatientConcrete(int Age, String gender, String UserID, String Address, String Name, String password,int ID_d, char ID_s) {
        super(UserID, Address, Name, password);
        
        this.ID_d=ID_d;
        this.ID_s =ID_s;
        this.Age = Age;
        this.gender = gender;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getID_d() {
        return ID_d;
    }

    public void setID_d(int ID_d) {
        this.ID_d = ID_d;
    }

    public char getID_s() {
        return ID_s;
    }

    public void setID_s(char ID_s) {
        this.ID_s = ID_s;
    }
   
    
   
    

}