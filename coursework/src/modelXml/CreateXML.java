/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelXml;


import org.w3c.dom.Attr;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 *
 * @author engsa
 */
public class CreateXML {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args)throws Exception  {
        
  DocumentBuilderFactory documentbuilderfactory = DocumentBuilderFactory.newInstance();
  DocumentBuilder DocumentBuilder =documentbuilderfactory.newDocumentBuilder();
  Document document=DocumentBuilder.newDocument();
  Element user=document.createElement("usersConcrete");
  document.appendChild(user);
   Element Doctor=document.createElement("DoctorsConcrete");
  document.appendChild(Doctor);
  Attr attr=document.createAttribute("userID");
  
  
  
  
  
  
      
          }
      }
