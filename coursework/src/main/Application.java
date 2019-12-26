/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controller.Controller;
import java.util.ArrayList;
import java.util.List;
import models.Administrator;
import models.DatastoreHelper;
import models.Doctor;
import models.IPersistable;
import models.Patient;
import models.Secretary;
import view.MainFrame;

/**
 *
 * Application is the entry point of the app
 */
public class Application {
    public static void main(String[] args) {
        // lets test all the models reading and writing
        DatastoreHelper dh = DatastoreHelper.getSingletonInstance();
        
        List<IPersistable> persistables = new ArrayList<>();
        persistables.add(new Administrator("A_0001", "James", "Bond", "234, James Boulevard", "secret_password"));
        persistables.add(new Administrator("A_0002", "Janet", "Doe", "456, Allen James Avenue", "secret_password"));
        persistables.add(new Administrator("A_0003", "John", "Doe", "The Moon", "secret_password"));
        persistables.add(new Administrator("A_0004", "Jimmy", "Carter", "White House of the USA", "secret_password"));
        dh.savePersistables(DatastoreHelper.administratorTxt, persistables);
        
        persistables.clear();
        persistables.add(new Secretary("S_0001", "Lillian", "McPhilly", "345, Oxford street", "secret_password"));
        persistables.add(new Secretary("S_0002", "Jennifer", "Aldrich", "342, James Mc Dough street, California", "secret_password"));
        persistables.add(new Secretary("S_0003", "Joyce", "Wheeler", "3678, Olenier road", "secret_password"));
        dh.savePersistables(DatastoreHelper.secretaryTxt, persistables);
        
        persistables.clear();
        persistables.add(new Doctor("D_0001",  "Peter", "Crooker", "89, Allen James  Island", "secret_password"));
        persistables.add(new Doctor("D_0002", "Shalewa", "Timothy", "483, Ox James street", "secret_password"));
        persistables.add(new Doctor("D_0003", "Fatima", "Mohammed", "372, Leedway road", "secret_password"));
        persistables.add(new Doctor("D_0004", "Peter", "Trucker", "33, Axis Ten Avenue", "secret_password"));
        dh.savePersistables(DatastoreHelper.doctorTxt, persistables);
        
        persistables.clear();
        persistables.add(new Patient("P_0001", "Juliet", "Atkinson", "34, Zeeland Rd.", "Female", "secret_password", 23));
        persistables.add(new Patient("P_0002", "June", "Raymond", "38, Zeeland Rd.", "Male", "secret_password", 53));
        persistables.add(new Patient("P_0003", "Hakeem", "Alli", "3445, Zeeland Rd.", "Male", "secret_password", 43));
        persistables.add(new Patient("P_0004", "Frank", "Carl", "35, Zeeland Rd.", "Male", "secret_password", 23));
        dh.savePersistables(DatastoreHelper.patientTxt, persistables);
        
        
        Controller controller = new Controller(dh);
        MainFrame mainView = new MainFrame(controller);
        
        mainView.setVisible(true);
    }
}
