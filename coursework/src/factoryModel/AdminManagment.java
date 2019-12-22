/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryModel;

import java.util.LinkedList;
import java.util.Scanner;
import modelTxt.UsersTxt;


/**
 *
 * @author engsa
 */
public class AdminManagment {
    private UsersTxt usersTxt;
    public AdminManagment(UsersTxt usersTxt) {
        super();
        this.usersTxt = usersTxt;
    }
    
    public void view(){
        
    }
       
    /**delet admin
     *
     */
       
       Scanner reeder = new Scanner(System.in);
            public void create() {
           LinkedList<adminConcrete> admin =new LinkedList<>();
           System.out.println("please enter the user number");
           Name=reeder.nextLine();
           ID_d=admin.get(admin.size()).getID_d()+1;
           UserID= "P"+ID_d;
           Address=reeder.nextLine();
           System.out.println("UserID");
           
           
          

  }
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
     *CREATE 
     */
    public void CreatDoctor() {
    
        LinkedList<SecretaryConcreate> Secretary =new LinkedList<>();
        System.out.println("please enter the user number");
        Name=reeder.nextLine();
        Address= reeder.nextLine();
        int newID = Secretary.get(Secretary.size()).getID_d()+1;
        UserID ="S"+newID;
        System.out.println(UserID);
    }

    
    
    
    
    /**
     *create a secretary
     */
    public  void DeleteSecretary(){
        
    LinkedList<SecretaryConcreate> secretary =new LinkedList<>();
          
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
          
        LinkedList<SecretaryConcreate> Secretary =new LinkedList<>();
        System.out.println("please enter the user number");
        Name=reeder.nextLine();
        Address= reeder.nextLine();
        ID_d = Secretary.get(Secretary.size()).ID_d+1;
        UserID ="S"+ID_d;
        System.out.println("userID");
    }

    @Override
    public void Create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
