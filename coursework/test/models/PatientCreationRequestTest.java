/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import models.Patient;
import models.PatientCreationRequest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author engsa
 */
public class PatientCreationRequestTest {
    
    public PatientCreationRequestTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testNewPatientCreationRequest() {
        System.out.println("newPatientCreationRequest");
        String txtFormat = "";
        PatientCreationRequest expResult = null;
        PatientCreationRequest result = PatientCreationRequest.newPatientCreationRequest(txtFormat);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetPatient() {
        System.out.println("getPatient");
        String uuid = "";
        PatientCreationRequest instance = null;
        Patient expResult = null;
        Patient result = instance.getPatient(uuid);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        PatientCreationRequest instance = null;
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetGivenName() {
        System.out.println("getGivenName");
        PatientCreationRequest instance = null;
        String expResult = "";
        String result = instance.getGivenName();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        PatientCreationRequest instance = null;
        String expResult = "";
        String result = instance.getLastName();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        PatientCreationRequest instance = null;
        String expResult = "";
        String result = instance.getAddress();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetGender() {
        System.out.println("getGender");
        PatientCreationRequest instance = null;
        String expResult = "";
        String result = instance.getGender();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAge() {
        System.out.println("getAge");
        PatientCreationRequest instance = null;
        int expResult = 0;
        int result = instance.getAge();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetUUID() {
        System.out.println("getUUID");
        PatientCreationRequest instance = null;
        String expResult = "";
        String result = instance.getUUID();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testToPersistableTxtFormat() {
        System.out.println("toPersistableTxtFormat");
        PatientCreationRequest instance = null;
        String expResult = "";
        String result = instance.toPersistableTxtFormat();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
