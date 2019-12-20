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
public class userFactory {
    private final mainFACTORY Factory;
     public userFactory(mainFACTORY Factory){
       this.Factory=Factory;  
     }
     
     
     
 public usersConcrete newusers(String Type){
     usersConcrete usersConcrete =Factory.createUser(Type);
      
     return usersConcrete;
 }
    
}
