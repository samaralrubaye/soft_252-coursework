/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import models.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author engsa
 */
public class UserTest {
    
    public UserTest() {
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

    public class UserImpl extends User {

        public UserImpl() {
            super("", "", "", "");
        }

        public String toPersistableTxtFormat() {
            return "";
        }
    }

    public class UserImpl extends User {

        public UserImpl() {
            super("", "", "", "");
        }

        public String toPersistableTxtFormat() {
            return "";
        }
    }
    
}
