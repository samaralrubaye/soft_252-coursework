/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

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
public class AppointmentTest {
    
    public AppointmentTest() {
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
    public void testNewAppointment() {
    }

    @Test
    public void testGetDate() {
    }

    @Test
    public void testGetUUID() {
    }

    @Test
    public void testGetDoctorUUID() {
    }

    @Test
    public void testGetPatientUUID() {
    }

    @Test
    public void testGetPatientNotified() {
    }

    @Test
    public void testGetDoctorNotified() {
    }

    @Test
    public void testSetDoctorNotified() {
    }

    @Test
    public void testSetPatientNotified() {
    }

    @Test
    public void testToPersistableTxtFormat() {
    }

    @Test
    public void testGetNotes() {
        System.out.println("getNotes");
        Appointment instance = null;
        String expResult = "";
        String result = instance.getNotes();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetNotes() {
        System.out.println("setNotes");
        String notes = "";
        Appointment instance = null;
        instance.setNotes(notes);
        fail("The test case is a prototype.");
    }
    
}
