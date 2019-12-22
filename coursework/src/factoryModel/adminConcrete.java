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
public class  adminConcrete extends usersConcrete {
    char ID_s='A';
    int ID_d=0000;

    /**
     *
     * @param UserID
     * @param Address
     * @param Name
     * @param password
     * @param ID_s
     * @param ID_d
     */
    public adminConcrete(String UserID, String Address, String Name, String password ,char ID_s , int ID_d ) {
        super(UserID, Address, Name, password);
        this.ID_s =ID_s;
        this.ID_d =ID_d;
    }

    

    public char getID_s() {
        return ID_s;
    }

    public void setID_s(char ID_s) {
        this.ID_s = ID_s;
    }

    public int getID_d() {
        return ID_d;
    }

    public void setID_d(int ID_d) {
        this.ID_d = ID_d;
    }
   
    

   /* @Override
   public  abstract void Create();
   public abstract void delete();

    /**
     *
     */
   /* @Override
   public abstract void view();*/
 
   
    
      
      


   
      
   

    /**
     *GETTING ADMIN USER ID 
     */
  
    
   
   

       
       

   }

  
 
        
    

    

   
    
    
     
    

