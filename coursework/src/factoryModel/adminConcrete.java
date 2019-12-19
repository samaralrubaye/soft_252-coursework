/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author engsa
 */
public class adminConcrete extends usersConcrete {
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
    
    /**delete patient
     *
     */
  
    

 
    
    
    
    /* ctrate doctor*/

    
    
    
    

    /**
     *
     * @param newadmin
     */
 
   
    
      
      

Scanner reeder = new Scanner(System.in);
   
      
   
   public void create() {
           LinkedList<adminConcrete> admin =new LinkedList<>();
           System.out.println("please enter the user number");
           Name=reeder.nextLine();
           ID_d=admin.get(admin.size()).getID_d()+1;
           UserID= "P"+ID_d;
           System.out.println("UserID");
          

  }

    /**
     *GETTING ADMIN USER ID 
     */
  
    
   
   

       @Override
       public void view(){
           
       }
       
    /**delet admin
     *
     */
    public  void DeleteAdmin(){
        
    LinkedList<adminConcrete> admin =new LinkedList<>();
          
         String ID = reeder.nextLine();
        
        
        for(int i=0 ;i<= admin.size();i++){
            boolean equals = ID.equals(admin.get(i).getUserID());
            if (equals== true){
                admin.remove(i);
                System.out.println("the  is removed");
            }
            else{System.out.println("  TRY AGAIN ");}
        }
}
public  void DeleteDoctor(){
        
    LinkedList<DoctorsConcrete> doctor =new LinkedList<>();
          
         String ID = reeder.nextLine();
       
   
       for(int i=0 ;i<= doctor.size();i++){
       if(ID== doctor.get(i).getUserID()){
           doctor.remove(i);
           System.out.println("the  is removed");
           return;
       }
     
         
       }
}

    /**
     *CREATE A DOCTOR
     */
    public void CreatDoctor() {
    
        ArrayList<SecretaryConcreate> Secretary =new ArrayList<>();
        System.out.println("please enter the user number");
        Name=reeder.nextLine();
        Address= reeder.nextLine();
        int newID = Secretary.get(Secretary.size()).get
        UserID ="S"+newID;
        System.out.println(UserID);
    }

    
    
    
    
    /**
     *create a secretary
     */
    public  void DeleteSecretary(){
        
    ArrayList<SecretaryConcreate> secretary =new ArrayList<>();
          
         String ID = reeder.nextLine();
       
   
       for(int i=0 ;i<= secretary.size();i++){
           
        boolean equals = ID.equals(secretary.get(i).getUserID());
        if (equals== true){
           secretary.remove(i);
           System.out.println("the  is removed");
        }
        else{System.out.println("  TRY AGAIN ");}
                   }
         
       }
     
   public void CreateSecretary() {
           super.Create(); //To change body of generated methods, choose Tools | Templates.
        ArrayList<SecretaryConcreate> Secretary =new ArrayList<>();
        System.out.println("please enter the user number");
        Name=reeder.nextLine();
        Address= reeder.nextLine();
        int newID = Secretary.get(Secretary.size()).
        UserID ="S"+newID;
        System.out.println("userID");
    }


       

   }
}
}
  
 
        
    

    

   
    
    
     
    

