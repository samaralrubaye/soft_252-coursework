/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import models.DoctorRating;
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
public class DoctorRatingTest {
    
    public DoctorRatingTest() {
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
    public void testNewDoctorRating() {
    }

    @Test
    public void testGetDoctorUUID() {
    }

    @Test
    public void testGetPatientUUID() {
    }

    @Test
    public void testGetRating() {
    }

    @Test
    public void testGetUUID() {
    }

    @Test
    public void testToPersistableTxtFormat() {
    }

    @Test
    public void testGetComment() {
        System.out.println("getComment");
        DoctorRating instance = null;
        String expResult = "";
        String result = instance.getComment();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }
    
}
