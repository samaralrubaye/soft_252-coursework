/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class DatastoreHelperTest {
    
    public DatastoreHelperTest() {
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
    public void testGetSingletonInstance() {
    }

    @Test
    public void testGetAdministrators() {
    }

    @Test
    public void testGetSecretaries() {
    }

    @Test
    public void testGetDoctors() {
    }

    @Test
    public void testGetPatients() {
    }

    @Test
    public void testGetAdministratorFeedback() {
    }

    @Test
    public void testGetAppointments() {
    }

    @Test
    public void testGetDoctorRatings() {
    }

    @Test
    public void testGetMedicines() {
    }

    @Test
    public void testGetMedicineOrders() {
    }

    @Test
    public void testGetPatientHistories() {
    }

    @Test
    public void testGetPrescriptions() {
    }

    @Test
    public void testGetPrescriptionMedicines() {
    }

    @Test
    public void testSavePersistables() {
    }

    @Test
    public void testReadAdministrators() {
    }

    @Test
    public void testReadAdministratorFeedback() {
    }

    @Test
    public void testReadAppointments() {
    }

    @Test
    public void testReadDoctors() {
    }

    @Test
    public void testReadDoctorRatings() {
    }

    @Test
    public void testReadMedicines() {
    }

    @Test
    public void testReadMedicineOrders() {
    }

    @Test
    public void testReadPatients() {
    }

    @Test
    public void testReadPatientHistories() {
    }

    @Test
    public void testReadPrescriptions() {
    }

    @Test
    public void testReadPrescriptionMedicines() {
    }

    @Test
    public void testReadSecretaries() {
    }

    @Test
    public void testGetAppointmentRequests() {
        System.out.println("getAppointmentRequests");
        DatastoreHelper instance = null;
        List<AppointmentRequest> expResult = null;
        List<AppointmentRequest> result = instance.getAppointmentRequests();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAccountTerminationRequests() {
        System.out.println("getAccountTerminationRequests");
        DatastoreHelper instance = null;
        List<AccountTerminationRequest> expResult = null;
        List<AccountTerminationRequest> result = instance.getAccountTerminationRequests();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetPatientCreationRequests() {
        System.out.println("getPatientCreationRequests");
        DatastoreHelper instance = null;
        HashMap<String, PatientCreationRequest> expResult = null;
        HashMap<String, PatientCreationRequest> result = instance.getPatientCreationRequests();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPersistAllData() {
        System.out.println("persistAllData");
        DatastoreHelper instance = null;
        instance.persistAllData();
        fail("The test case is a prototype.");
    }

    @Test
    public void testReadAppointmentRequests() {
        System.out.println("readAppointmentRequests");
        DatastoreHelper instance = null;
        List<AppointmentRequest> expResult = null;
        List<AppointmentRequest> result = instance.readAppointmentRequests();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testReadAccountTerminationRequests() {
        System.out.println("readAccountTerminationRequests");
        DatastoreHelper instance = null;
        List<AccountTerminationRequest> expResult = null;
        List<AccountTerminationRequest> result = instance.readAccountTerminationRequests();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testReadPatientCreationRequests() {
        System.out.println("readPatientCreationRequests");
        DatastoreHelper instance = null;
        Map<String, PatientCreationRequest> expResult = null;
        Map<String, PatientCreationRequest> result = instance.readPatientCreationRequests();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testLoadAllData() {
        System.out.println("loadAllData");
        DatastoreHelper instance = null;
        instance.loadAllData();
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetAdmin() {
        System.out.println("getAdmin");
        String userID = "";
        String password = "";
        DatastoreHelper instance = null;
        Administrator expResult = null;
        Administrator result = instance.getAdmin(userID, password);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetDoctor() {
        System.out.println("getDoctor");
        String userID = "";
        String password = "";
        DatastoreHelper instance = null;
        Doctor expResult = null;
        Doctor result = instance.getDoctor(userID, password);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetSecretary() {
        System.out.println("getSecretary");
        String userID = "";
        String password = "";
        DatastoreHelper instance = null;
        Secretary expResult = null;
        Secretary result = instance.getSecretary(userID, password);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetPatient() {
        System.out.println("getPatient");
        String userID = "";
        String password = "";
        DatastoreHelper instance = null;
        Patient expResult = null;
        Patient result = instance.getPatient(userID, password);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSaveAppointmentRequest() {
        System.out.println("saveAppointmentRequest");
        AppointmentRequest ar = null;
        DatastoreHelper instance = null;
        instance.saveAppointmentRequest(ar);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSaveAccountTerminationRequest() {
        System.out.println("saveAccountTerminationRequest");
        AccountTerminationRequest accountTerminationRequest = null;
        DatastoreHelper instance = null;
        instance.saveAccountTerminationRequest(accountTerminationRequest);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateAdministrator() {
        System.out.println("createAdministrator");
        String givenName = "";
        String lastName = "";
        String address = "";
        String password = "";
        DatastoreHelper instance = null;
        Administrator expResult = null;
        Administrator result = instance.createAdministrator(givenName, lastName, address, password);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteDoctor() {
        System.out.println("deleteDoctor");
        String uuid = "";
        DatastoreHelper instance = null;
        instance.deleteDoctor(uuid);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteSecretary() {
        System.out.println("deleteSecretary");
        String uuid = "";
        DatastoreHelper instance = null;
        instance.deleteSecretary(uuid);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateDoctor() {
        System.out.println("createDoctor");
        String givenName = "";
        String lastName = "";
        String address = "";
        String password = "";
        DatastoreHelper instance = null;
        instance.createDoctor(givenName, lastName, address, password);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateSecretary() {
        System.out.println("createSecretary");
        String givenName = "";
        String lastName = "";
        String address = "";
        String password = "";
        DatastoreHelper instance = null;
        instance.createSecretary(givenName, lastName, address, password);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateAdministratorFeedback() {
        System.out.println("createAdministratorFeedback");
        String doctorUUID = "";
        String feedback = "";
        DatastoreHelper instance = null;
        instance.createAdministratorFeedback(doctorUUID, feedback);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreatePatientCreationRequest() {
        System.out.println("createPatientCreationRequest");
        String givenName = "";
        String lastName = "";
        String address = "";
        String password = "";
        String gender = "";
        int age = 0;
        DatastoreHelper instance = null;
        instance.createPatientCreationRequest(givenName, lastName, address, password, gender, age);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreatePatient() {
        System.out.println("createPatient");
        PatientCreationRequest pcr = null;
        DatastoreHelper instance = null;
        instance.createPatient(pcr);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRemovePatientAccount_AccountTerminationRequest() {
        System.out.println("removePatientAccount");
        AccountTerminationRequest atr = null;
        DatastoreHelper instance = null;
        instance.removePatientAccount(atr);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRemovePatientAccount_String() {
        System.out.println("removePatientAccount");
        String uuid = "";
        DatastoreHelper instance = null;
        instance.removePatientAccount(uuid);
        fail("The test case is a prototype.");
    }

    @Test
    public void testRegisterAppointmentFromRequest() {
        System.out.println("registerAppointmentFromRequest");
        AppointmentRequest ar = null;
        DatastoreHelper instance = null;
        instance.registerAppointmentFromRequest(ar);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreateMedicineOrder() {
        System.out.println("createMedicineOrder");
        String docUUID = "";
        String name = "";
        int qty = 0;
        String description = "";
        DatastoreHelper instance = null;
        instance.createMedicineOrder(docUUID, name, qty, description);
        fail("The test case is a prototype.");
    }

    @Test
    public void testProcessMedicineOrder() {
        System.out.println("processMedicineOrder");
        MedicineOrder mo = null;
        DatastoreHelper instance = null;
        instance.processMedicineOrder(mo);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCreatePatientHistory() {
        System.out.println("createPatientHistory");
        Appointment a = null;
        DatastoreHelper instance = null;
        instance.createPatientHistory(a);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSubmitDoctorPrescriptionMedicine() {
        System.out.println("submitDoctorPrescriptionMedicine");
        String medicineUUID = "";
        String appointmentUUID = "";
        int qty = 0;
        DatastoreHelper instance = null;
        instance.submitDoctorPrescriptionMedicine(medicineUUID, appointmentUUID, qty);
        fail("The test case is a prototype.");
    }

    @Test
    public void testApprovePrescriptionMedicineCollection() {
        System.out.println("approvePrescriptionMedicineCollection");
        String uuid = "";
        DatastoreHelper instance = null;
        instance.approvePrescriptionMedicineCollection(uuid);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSubmitPatientAppointmentRating() {
        System.out.println("submitPatientAppointmentRating");
        String patientUUID = "";
        String doctorUUID = "";
        String comments = "";
        double rating = 0.0;
        DatastoreHelper instance = null;
        instance.submitPatientAppointmentRating(patientUUID, doctorUUID, comments, rating);
        fail("The test case is a prototype.");
    }
    
}
