/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryModel;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelTxt.UsersTxt;

/**
 *
 * @author engsa
 */
public class mainFACTORY {
    
    
  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // get singleton instance of your UsersTxt object
            UsersTxt usersTxt = UsersTxt.getInstance();
            
            // read all users from file using the singleton instance of the UsersTxt which internally uses factory pattern to create the Users
            LinkedList<usersConcrete> allusers = usersTxt.readUsers();
        } catch (IOException ex) {
            Logger.getLogger(mainFACTORY.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       // userFactory userFactory = new userFactory();
       

      //get an object of Circle and call its draw method.
      /*usersConcrete Doctor = userFactory.getuser("DoctorConcrete");

      //call draw method of Doctor
      Doctor.Create();
      Doctor.Delete();
      Doctor.view();

      //get an object of Rectangle and call its draw method.
      usersConcrete Secretary = userFactory.getuser("SecretaryConcreate");

      //call methods of Secretary
      Secretary.Create();
      Secretary.Delete();
      Secretary.view();

      //get an object of Square and call its draw method.
      usersConcrete patient = userFactory.getuser("PatientConcrete");

      //call draw method of square
      patient.Create();
      patient.Delete();
      patient.view();
      usersConcrete Admin = userFactory.getuser("adminConcrete");
      Admin.Create();
      Admin.Delete();
      Admin.view();*/
   } 
        
   

   
    
    
   
    
    
}


