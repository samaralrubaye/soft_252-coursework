/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import models.Patient;
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
public class PatientTest {
    
    public PatientTest() {
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
    public void testToPersistableTxtFormat() {
    }

    @Test
    public void testNewPatient() {
        System.out.println("newPatient");
        String txtFormat = "";
        Patient expResult = null;
        Patient result = Patient.newPatient(txtFormat);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAge() {
        System.out.println("getAge");
        Patient instance = null;
        int expResult = 0;
        int result = instance.getAge();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetGender() {
        System.out.println("getGender");
        Patient instance = null;
        String expResult = "";
        String result = instance.getGender();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
