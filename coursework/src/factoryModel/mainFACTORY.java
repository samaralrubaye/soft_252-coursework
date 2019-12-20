/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryModel;

/**
 *
 * @author engsa
 */
public class mainFACTORY {
    
     public usersConcrete createUser(String Type){
           usersConcrete usersConcrete ;
           switch(Type){
               case "DoctorsConcrete" :
                   
                usersConcrete = new DoctorsConcrete(); 
                  breake;
                   
                case "admimConcrete" :
                      usersConcrete =new admimConcrete();
                       breake;
                       
                case "PatientConcrete" :
                   usersConcrete =new PatientConcrete();
                   
                       breake;   
                       defoult:
                       usersConcrete =new SecritaryConcreate();
           }
           return usersConcrete;
        }
  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
   

   
    
    
   
    
    
}
}

