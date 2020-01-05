/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import models.Medicine;
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
public class MedicineTest {
    
    public MedicineTest() {
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
    public void testNewMedicine() {
        System.out.println("newMedicine");
        String txtFormat = "";
        Medicine expResult = null;
        Medicine result = Medicine.newMedicine(txtFormat);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetUUID() {
        System.out.println("getUUID");
        Medicine instance = null;
        String expResult = "";
        String result = instance.getUUID();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetDoctorUUID() {
        System.out.println("getDoctorUUID");
        Medicine instance = null;
        String expResult = "";
        String result = instance.getDoctorUUID();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        Medicine instance = null;
        int expResult = 0;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetName() {
        System.out.println("getName");
        Medicine instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Medicine instance = null;
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUpdateQuantity() {
        System.out.println("updateQuantity");
        int difference = 0;
        Medicine instance = null;
        instance.updateQuantity(difference);
        fail("The test case is a prototype.");
    }

    @Test
    public void testToPersistableTxtFormat() {
        System.out.println("toPersistableTxtFormat");
        Medicine instance = null;
        String expResult = "";
        String result = instance.toPersistableTxtFormat();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
