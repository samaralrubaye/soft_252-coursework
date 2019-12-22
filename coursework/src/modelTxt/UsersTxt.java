/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelTxt;


import factoryModel.SecretaryConcreate;
import factoryModel.PatientConcrete;
import factoryModel.DoctorsConcrete;
import factoryModel.adminConcrete;
import factoryModel.medicine;
import factoryModel.userFactory;
import factoryModel.usersConcrete;
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author engsa
 */
public class UsersTxt {
    // singleton instance of this class
    private static UsersTxt singleton;
    
    
    /**
     * Make constructor private and use singleton patter in UsersTxt 
     * because we only want one instance of our UserTxt object
     */
    private UsersTxt(){};
    
    public static UsersTxt getInstance() {
        // check if we don't have a singleton, then create one, else ready already existing instance
        if (singleton == null) {
            singleton = new UsersTxt();
        }
        return singleton;
    }

    /**
     *
     * @return
     * @throws java.io.IOException
     */
    public LinkedList<usersConcrete> readUsers() throws IOException {
        LinkedList<usersConcrete> U = new LinkedList();
        String userTxt;
        String[] splitLine;
        usersConcrete user = null; 
        // Scanner readers = new Scanner(System.in);
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            userTxt = reader.readLine();
            while (userTxt != null) {
                splitLine = userTxt.split(",");
                String userId = splitLine[0].trim();
                String addr = splitLine[1].trim();
                String name = splitLine[2].trim();
                String password = splitLine[3].trim();
                
                char userType = userId.charAt(0);
                int idD = Integer.valueOf(userId.substring(2));
                switch(userType) {
                    case 'A': {
                        user = userFactory.getAdminUser(userId, addr, name, password, userType, idD);
                        break;
                    }
                    case 'D': {
                        String medicineName = splitLine[4].trim();
                        String notes = splitLine[5].trim();
                        user = userFactory.getDoctorUser(notes, new medicine(medicineName), userId, addr, name, password, idD, userType);
                        break;
                    }
                    case 'S': {
                        user = userFactory.getScretaryUser(userId, addr, name, password, userType, idD);
                        break;
                    }
                    case 'P': {
                        int age = Integer.valueOf(splitLine[4].trim());
                        String gender = splitLine[5].trim();
                        user = userFactory.getPatientUser(age, gender, userId, addr, name, password, idD, userType);
                        break;
                    }
                }
                
               /*if (splitLine[7].equals("D")) {
                    user = new DoctorsConcrete(Integer.parseInt(splitLine[0]),'D');
                }
                
                else if(splitLine[5].equals("S")) {
                    user = new SecretaryConcreate(Integer.parseInt(splitLine[4]),'S');
                }
                else if(splitLine[5].equals("P")) {
                    user = new PatientConcrete(Integer.parseInt(splitLine[0]),Integer.parseInt(splitLine[4]),'P');
                }
                else if(splitLine[4].equals("A")) {
                    user = new adminConcrete('A',Integer.parseInt(splitLine[1]));
                    
                
                }*/
                if(user != null) {
                    U.add(user);
                    // log out to debug that wa actually read the users from file and create them using the Factory pattern
                    System.out.println("added user: " + user);
                }
                userTxt = reader.readLine();
            }
                     
            }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return U;
    }
    public void  apendfile(String  user){
        try(BufferedWriter Writer =new BufferedWriter(new FileWriter("Myfile.txt",true)))
        {
            Writer.write(user+"\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void overWriteFile(LinkedList<usersConcrete> U){
        String s;
        try(BufferedWriter Writer =new BufferedWriter(new FileWriter("Myfile.temp",false)))
        {
           for(int i =0;i<U.size();i++){
               s=U.get(i).toString();
               
              Writer.write(s+ "\n");
               
           }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try{
        
            File f= new File("Myfile.txt");
            File tf =new File("Myfile.temp");
            f.delete();
            tf.renameTo(f);
            
    }
       catch (Exception e) {
           
             System.out.println(e.getMessage());
       }
        
    }
        
            
        
        
        
    }


    
    
    
    
    
        
      
        
    
    
 
        
