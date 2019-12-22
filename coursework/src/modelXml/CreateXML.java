/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelXml;


import factoryModel.SecretaryConcreate;
import factoryModel.PatientConcrete;
import factoryModel.DoctorsConcrete;
import factoryModel.adminConcrete;
import factoryModel.usersConcrete;
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author engsa
 */
public class CreateXML {

    /**
     *
     *
     *
     * /**
     *
     * @return
     * @throws java.io.IOException
     */
    public LinkedList<usersConcrete> readFile() throws IOException {
        LinkedList<usersConcrete> U = new LinkedList();
        String lineRead;
        String[] splitLine;
        usersConcrete user; 
        Scanner readers = new Scanner(System.in);
        try (BufferedReader reader = new BufferedReader(new FileReader("MyFile.txt"))) {
            lineRead = reader.readLine();
            while (lineRead != null) {
                splitLine = lineRead.split(",");
                
                if (splitLine[7].equals("D")) {
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
                    
                
                }
            }
                     U.add(user);
                     lineRead = reader.readLine();
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
        
    }
        
            
        
        
        
    }
}


    
    
    
    
    
        
      
        
    
    
 
        
