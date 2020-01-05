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
public class AccountTerminationRequestTest {
    
    public AccountTerminationRequestTest() {
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
    public void testNewAccountTerminationRequest() {
        System.out.println("newAccountTerminationRequest");
        String txtFormat = "";
        AccountTerminationRequest expResult = null;
        AccountTerminationRequest result = AccountTerminationRequest.newAccountTerminationRequest(txtFormat);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetPatientUUID() {
        System.out.println("getPatientUUID");
        AccountTerminationRequest instance = null;
        String expResult = "";
        String result = instance.getPatientUUID();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testIsApproved() {
        System.out.println("isApproved");
        AccountTerminationRequest instance = null;
        boolean expResult = false;
        boolean result = instance.isApproved();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testToPersistableTxtFormat() {
        System.out.println("toPersistableTxtFormat");
        AccountTerminationRequest instance = null;
        String expResult = "";
        String result = instance.toPersistableTxtFormat();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        AccountTerminationRequest instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        AccountTerminationRequest instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
