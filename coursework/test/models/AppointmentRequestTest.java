/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import models.AppointmentRequest;
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
public class AppointmentRequestTest {
    
    public AppointmentRequestTest() {
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
    public void testNewAppointmentRequest() {
        System.out.println("newAppointmentRequest");
        String txtFormat = "";
        AppointmentRequest expResult = null;
        AppointmentRequest result = AppointmentRequest.newAppointmentRequest(txtFormat);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testToPersistableTxtFormat() {
        System.out.println("toPersistableTxtFormat");
        AppointmentRequest instance = null;
        String expResult = "";
        String result = instance.toPersistableTxtFormat();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetDoctorUUID() {
        System.out.println("getDoctorUUID");
        AppointmentRequest instance = null;
        String expResult = "";
        String result = instance.getDoctorUUID();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetApproved() {
        System.out.println("setApproved");
        boolean approved = false;
        AppointmentRequest instance = null;
        instance.setApproved(approved);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetPatientUUID() {
        System.out.println("getPatientUUID");
        AppointmentRequest instance = null;
        String expResult = "";
        String result = instance.getPatientUUID();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetDate() {
        System.out.println("getDate");
        AppointmentRequest instance = null;
        Date expResult = null;
        Date result = instance.getDate();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testIsApproved() {
        System.out.println("isApproved");
        AppointmentRequest instance = null;
        boolean expResult = false;
        boolean result = instance.isApproved();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
