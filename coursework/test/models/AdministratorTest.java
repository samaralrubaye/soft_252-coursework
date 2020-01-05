/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import models.Administrator;
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
public class AdministratorTest {
    
    public AdministratorTest() {
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
    public void testNewAdministrator() {
        System.out.println("newAdministrator");
        String txtFormat = "";
        Administrator expResult = null;
        Administrator result = Administrator.newAdministrator(txtFormat);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
