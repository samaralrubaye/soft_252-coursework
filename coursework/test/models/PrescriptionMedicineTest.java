/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author engsa
 */
public class PrescriptionMedicineTest {
    
    public PrescriptionMedicineTest() {
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
    public void testNewPrescriptionMedicine() {
    }

    @Test
    public void testGetMedicineUUID() {
    }

    @Test
    public void testGetUUID() {
    }

    @Test
    public void testGetPrescriptionUUID() {
    }

    @Test
    public void testGetMedicineQuantity() {
    }

    @Test
    public void testToPersistableTxtFormat() {
    }

    @Test
    public void testGetAppointmentUUID() {
        System.out.println("getAppointmentUUID");
        PrescriptionMedicine instance = null;
        String expResult = "";
        String result = instance.getAppointmentUUID();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
