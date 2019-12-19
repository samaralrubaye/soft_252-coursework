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
public class SecretaryConcreate extends usersConcrete {
    int ID_d=0000;
    char ID_s='S';
    

    public SecretaryConcreate(String UserID, String Address, String Name, String password,int ID_d,char ID_s) {
        super(UserID, Address, Name, password);
        this.ID_d =ID_d;
        this.ID_s =ID_s;
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
Scanner reeder = new Scanner(System.in);
   
    @Override
    public  void Delete(){
        
    LinkedList<PatientConcrete> Patient =new LinkedList<>();
          
         String ID = reeder.nextLine();
       
   
       for(int i=0 ;i<= Patient.size();i++){
       boolean equals = ID.equals(Patient.get(i).getUserID());
        if (equals== true){
           Patient.remove(i);
           System.out.println("the  is removed");
        }
        else{System.out.println("  TRY AGAIN ");}
                   }
         
       }
       }
    

