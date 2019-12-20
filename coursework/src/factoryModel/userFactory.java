/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factoryModel;

public  class userFactory {
     public usersConcrete getuser(String Type){
     	
      if(Type.equalsIgnoreCase("DoctorsConcrete")){
         return new DoctorsConcrete(notes, medicin,UserID,Address,Name,password,ID_d,ID_s);
         
      } else if(Type.equalsIgnoreCase("SecretaryConcreate")){
         return new SecretaryConcreate(UserID,Address,Name,password,ID_d,ID_s);
         
      } else if(Type.equalsIgnoreCase("PatientConcrete")){
         return new PatientConcrete( Age,gender, UserID,Address,  Name,password,ID_d, ID_s);
      
      } else if(Type.equalsIgnoreCase("AdminConcrete")){
         return new PatientConcrete( UserID,Address, Name,password,ID_d, ID_s);
      }
     return  null;
}
}

      
     
     
     
 
 
      
    
 
    

