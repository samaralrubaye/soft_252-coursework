/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryModel;

import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author engsa
 */

public class DoctorsConcrete extends usersConcrete {
     Scanner reeder = new Scanner(System.in);
    String notes;
   medicine medicin;
    int ID_d=0000;
    char ID_s='D';

    public DoctorsConcrete(String notes, medicine medicin, String UserID, String Address, String Name, String password,int ID_d, char ID_s) {
        super(UserID, Address, Name, password);
        this.ID_d=ID_d;
        this.ID_s=ID_s;
        this.notes = notes;
        this.medicin = medicin;
    }

    

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public medicine getMedicin() {
        return medicin;
    }

    public void setMedicin(medicine medicin) {
        this.medicin = medicin;
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
    

    
   

   
    /**
     *
     */
    public void writeanit(){
     LinkedList<medicine> newmed=new LinkedList<>();
      medicin=reeder.nextLine();
    
          
    }
    
   
     }
