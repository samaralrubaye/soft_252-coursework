/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.AbstractTableModel;
import models.*;

/**
 *
 * class containing the views
 */
public class MainFrame extends javax.swing.JFrame {
    private Controller controller;
    private Patient patient;
    private Administrator administrator;
    private JPopupMenu patientsDoctorsPopupMenu;
    private JPopupMenu adminDoctorsPopupMenu;
    private JPopupMenu adminSecretariesPopupMenu;
    private Secretary secretary;
    private Doctor doctor;
    private Doctor selectedDoctorForAppointment;
    private Patient selectedPatientForAppointment;
    private Appointment selectedAppointment;
    

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }
    
    public MainFrame(Controller controller) {
        this.controller = controller;
        initComponents();
        initPopupMenus();
    }
    
    private void initPopupMenus()  {
        this.patientsDoctorsPopupMenu = new JPopupMenu();
        JMenuItem jmiReqAppointment = new JMenuItem("Request Appointment");
        jmiReqAppointment.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Doctor d = ((DoctorsTableModel)MainFrame.this.jtblAllDoctors.getModel()).getDoctor(MainFrame.this.jtblAllDoctors.getSelectedRow());
                MainFrame.this.jtfAppointSchedulerPatientName.setText(patient.getGivenName() + " " + patient.getLastName());
                MainFrame.this.jtfAppointmentSchedulerDoctorName.setText(d.getGivenName() + " " + d.getLastName());
                MainFrame.this.selectedDoctorForAppointment = d;
                MainFrame.this.selectedPatientForAppointment = patient;
                MainFrame.this.jdRequestAppointment.pack();
                MainFrame.this.jdRequestAppointment.setLocationRelativeTo(MainFrame.this);
                MainFrame.this.jdRequestAppointment.setVisible(true);
            }
        });
        this.patientsDoctorsPopupMenu.add(jmiReqAppointment);
        this.jtblAllDoctors.setComponentPopupMenu(this.patientsDoctorsPopupMenu);
        this.jtblAllDoctors.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                // selects the row at which point the mouse is clicked
                Point point = event.getPoint();
                int currentRow = jtblAllDoctors.rowAtPoint(point);
                jtblAllDoctors.setRowSelectionInterval(currentRow, currentRow);
            }
        });
        
        JPopupMenu doctorPatientsPopupMenu = new JPopupMenu();
        JMenuItem jmiDoctorReqAppointment = new JMenuItem("Request Appointment");
        JMenuItem jmiDoctorViewPatientHistory = new JMenuItem("View History");
        
        jmiDoctorReqAppointment.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Patient p = ((PatientTableModel)MainFrame.this.jtblDoctorPatients.getModel()).getPatient(MainFrame.this.jtblDoctorPatients.getSelectedRow());
                MainFrame.this.jtfAppointSchedulerPatientName.setText(p.getGivenName() + " " + p.getLastName());
                MainFrame.this.jtfAppointmentSchedulerDoctorName.setText(doctor.getGivenName() + " " + doctor.getLastName());
                MainFrame.this.selectedDoctorForAppointment = doctor;
                MainFrame.this.selectedPatientForAppointment =  p;
                MainFrame.this.jdRequestAppointment.pack();
                MainFrame.this.jdRequestAppointment.setLocationRelativeTo(MainFrame.this);
                MainFrame.this.jdRequestAppointment.setVisible(true);
            }
        });
        
        jmiDoctorViewPatientHistory.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.jdDoctorViewPatientHistory.pack();
                MainFrame.this.jdDoctorViewPatientHistory.setLocationRelativeTo(MainFrame.this);
                Patient p = ((PatientTableModel)MainFrame.this.jtblDoctorPatients.getModel()).getPatient(MainFrame.this.jtblDoctorPatients.getSelectedRow());
                MainFrame.this.jtPatientHistoryDialog.setModel(new PatientHistoryTableModel(p.getUUID()));
                MainFrame.this.jdDoctorViewPatientHistory.setVisible(true);
            }
        });
        doctorPatientsPopupMenu.add(jmiDoctorReqAppointment);
        doctorPatientsPopupMenu.add(jmiDoctorViewPatientHistory);
        this.jtblDoctorPatients.setComponentPopupMenu(doctorPatientsPopupMenu);
        
        JPopupMenu jpDoctorAppointments = new JPopupMenu();
        JMenuItem jmiDoctorBeginAppointment = new JMenuItem("Begin Appointment");
        JMenuItem jmiDoctorCreatePrescription = new JMenuItem("Create Prescription");
        jmiDoctorBeginAppointment.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Appointment a = ((DoctorAppointmentTableModel)MainFrame.this.jtblDoctorAppointments.getModel()).getAppointment(MainFrame.this.jtblDoctorAppointments.getSelectedRow());
                MainFrame.this.selectedAppointment = a;
                Patient p = MainFrame.this.controller.getPatient(a.getPatientUUID());
                MainFrame.this.jtfAppointmentDate.setText(Appointment.sdf.format(a.getDate()));
                MainFrame.this.jtfAppointmentPatientAddress.setText(p.getAddress());
                MainFrame.this.jtfAppointmentPatientAge.setText(String.valueOf(p.getAge()));
                MainFrame.this.jtfAppointmentPatientName.setText(String.format("%s %s", p.getGivenName(), p.getLastName()));
                MainFrame.this.jdDoctorAppointment.pack();
                MainFrame.this.jdDoctorAppointment.setLocationRelativeTo(MainFrame.this);
                MainFrame.this.jdDoctorAppointment.setVisible(true);
            }
        });
        
        jmiDoctorCreatePrescription.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.jdDoctorPrescribeMedicine.pack();
                MainFrame.this.jdDoctorPrescribeMedicine.setLocationRelativeTo(MainFrame.this);
                MainFrame.this.jdDoctorPrescribeMedicine.setVisible(true);
            }
        });
        jpDoctorAppointments.add(jmiDoctorBeginAppointment);
        jpDoctorAppointments.add(jmiDoctorCreatePrescription);
        this.jtblDoctorAppointments.setComponentPopupMenu(jpDoctorAppointments);
        
        
        this.adminDoctorsPopupMenu = new JPopupMenu();
        JMenuItem jmiRemoveDoctor = new JMenuItem("Remove");
        jmiRemoveDoctor.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Doctor d = ((AdminDoctorTableModel)MainFrame.this.jtblAdminAllDoctors.getModel()).getDoctor(MainFrame.this.jtblAdminAllDoctors.getSelectedRow());
                MainFrame.this.controller.deleteDoctor(d.getUUID());
                MainFrame.this.jtblAdminAllDoctors.setModel(new AdminDoctorTableModel());
            }
        });
        
        JMenuItem jmiViewDoctorRatings = new JMenuItem("View Ratings");
        jmiViewDoctorRatings.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // get selected doctor
                Doctor d = ((AdminDoctorTableModel)MainFrame.this.jtblAdminAllDoctors.getModel()).getDoctor(MainFrame.this.jtblAdminAllDoctors.getSelectedRow());
                MainFrame.this.jlblAdminViewDoctorRatingsDoctorName.setText("Ratings of Dr. " + d.getGivenName() + " " + d.getLastName());
                
                // set dialog table model
                MainFrame.this.jtblAdminViewDoctorRatings.setModel(new AdminViewDoctorRatingTableModel(d));
                
                // clear dialog fields
                MainFrame.this.jtfAdminViewDoctorRatingPatientName.setText("");
                MainFrame.this.jtfAdminViewDoctorRating.setText("");
                MainFrame.this.jtfAdminViewDoctorRatingComment.setText("");
                MainFrame.this.jtaAdminViewDoctorRatingComment.setText("");
                
                // open dialog packed
                MainFrame.this.jdAdminViewDoctorRatings.pack();
                MainFrame.this.jdAdminViewDoctorRatings.setLocationRelativeTo(MainFrame.this);
                MainFrame.this.jdAdminViewDoctorRatings.setVisible(true);
            }
        });
        
        // add ViewAdminRatingsTable mouse listener
        this.jtblAdminViewDoctorRatings.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                int row = MainFrame.this.jtblAdminViewDoctorRatings.getSelectedRow();
                DoctorRating dr = ((AdminViewDoctorRatingTableModel)MainFrame.this.jtblAdminViewDoctorRatings.getModel()).getDoctorRating(row);
                Patient p = controller.getPatient(dr.getPatientUUID());
                
                MainFrame.this.jtfAdminViewDoctorRatingPatientName.setText(String.format("%s %s", p.getGivenName(), p.getLastName()));
                MainFrame.this.jtfAdminViewDoctorRating.setText(String.format("%.2f", dr.getRating()));
                MainFrame.this.jtfAdminViewDoctorRatingComment.setText(dr.getComment());
                MainFrame.this.jtaAdminViewDoctorRatingComment.setText("");
            }
        });
        
        this.adminDoctorsPopupMenu.add(jmiRemoveDoctor);
        this.adminDoctorsPopupMenu.add(jmiViewDoctorRatings);
        this.jtblAdminAllDoctors.setComponentPopupMenu(this.adminDoctorsPopupMenu);
        this.jtblAdminAllDoctors.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                // selects the row at which point the mouse is clicked
                Point point = event.getPoint();
                int currentRow = jtblAdminAllDoctors.rowAtPoint(point);
                jtblAdminAllDoctors.setRowSelectionInterval(currentRow, currentRow);
            }
        });
        
        this.adminSecretariesPopupMenu = new JPopupMenu();
        JMenuItem jmiRemoveSecretary = new JMenuItem("Remove Secretary");
        jmiRemoveSecretary.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Secretary s = ((AdminSecretaryTableModel)MainFrame.this.jtblAdminAllSecretaries.getModel()).getSecretary(MainFrame.this.jtblAdminAllSecretaries.getSelectedRow());
                MainFrame.this.controller.deleteSecretary(s.getUUID());
                MainFrame.this.jtblAdminAllSecretaries.setModel(new AdminSecretaryTableModel());
            }
        });
        this.adminSecretariesPopupMenu.add(jmiRemoveSecretary);
        this.jtblAdminAllSecretaries.setComponentPopupMenu(this.adminSecretariesPopupMenu);
        this.jtblAdminAllSecretaries.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                // selects the row at which point the mouse is clicked
                Point point = event.getPoint();
                int currentRow = jtblAdminAllSecretaries.rowAtPoint(point);
                jtblAdminAllSecretaries.setRowSelectionInterval(currentRow, currentRow);
            }
        }); 
        
        JPopupMenu jpmAccountCreation = new JPopupMenu();
        JMenuItem jmiApproveAccountCreation = new JMenuItem("Approve Account");
        jpmAccountCreation.add(jmiApproveAccountCreation);
        jmiApproveAccountCreation.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                PatientCreationRequest pcr = ((PatientCreationRequestTableModel)MainFrame.this.jtblPatientAccCreationRequests.getModel()).getRequest(MainFrame.this.jtblPatientAccCreationRequests.getSelectedRow());
                MainFrame.this.controller.approvePatientAccountCreation(pcr);
                MainFrame.this.jtblPatientAccCreationRequests.setModel(new PatientCreationRequestTableModel());
                MainFrame.this.jtblSecretaryPatients.setModel(new PatientTableModel());
                JOptionPane.showMessageDialog(MainFrame.this, "Account Approved", "Success",  JOptionPane.INFORMATION_MESSAGE);
            }
        });
        this.jtblPatientAccCreationRequests.setComponentPopupMenu(jpmAccountCreation);
        
        JPopupMenu jpmAccountRemoval = new JPopupMenu();
        JMenuItem jmiApproveAccountRemoval = new JMenuItem("Approve Account Removal");
        jmiApproveAccountRemoval.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                AccountTerminationRequest atr = ((PatientRemovalRequestTableModel)MainFrame.this.jtblAccountRemovalRequests.getModel()).getRequest(MainFrame.this.jtblAccountRemovalRequests.getSelectedRow());
                MainFrame.this.controller.approvePatientAccountRemoval(atr);
                MainFrame.this.jtblAccountRemovalRequests.setModel(new PatientRemovalRequestTableModel());
                MainFrame.this.jtblSecretaryPatients.setModel(new PatientTableModel());
                JOptionPane.showMessageDialog(MainFrame.this, "Account Removed", "Success",  JOptionPane.INFORMATION_MESSAGE);
            }
        });
        jpmAccountRemoval.add(jmiApproveAccountRemoval);
        this.jtblAccountRemovalRequests.setComponentPopupMenu(jpmAccountRemoval);
        
        JPopupMenu jpmPatientRemoval = new JPopupMenu();
        JMenuItem jmiRemovePatient = new JMenuItem("Remove Patient");
        jmiRemovePatient.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Patient p = ((PatientTableModel)MainFrame.this.jtblSecretaryPatients.getModel()).getPatient(MainFrame.this.jtblSecretaryPatients.getSelectedRow());
                controller.removePatient(p.getUUID());
                MainFrame.this.jtblSecretaryPatients.setModel(new PatientTableModel());
                JOptionPane.showMessageDialog(MainFrame.this, "Patient removed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        jpmPatientRemoval.add(jmiRemovePatient);
        this.jtblSecretaryPatients.setComponentPopupMenu(jpmPatientRemoval);
        
        JPopupMenu jpmAppointmentRequest = new JPopupMenu();
        JMenuItem jmiApproveAppointmentRequest = new JMenuItem("Approve");
        jmiApproveAppointmentRequest.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                AppointmentRequest ar = ((AppointmentRequestTableModel)MainFrame.this.jtblAppointmentsRequests.getModel()).getRequest(MainFrame.this.jtblAppointmentsRequests.getSelectedRow());
                ar.setApproved(true);
                MainFrame.this.jtblAppointmentsRequests.setModel(new AppointmentRequestTableModel());
                controller.registerAppointmentFromRequest(ar);
                JOptionPane.showMessageDialog(MainFrame.this, "Appointment Request Approved", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        jpmAppointmentRequest.add(jmiApproveAppointmentRequest);
        this.jtblAppointmentsRequests.setComponentPopupMenu(jpmAppointmentRequest);
        
        JPopupMenu jpmMedicineOrder = new JPopupMenu();
        JMenuItem jmiApproveMedicineOrder = new JMenuItem("Approve Medicine Order");
        jmiApproveMedicineOrder.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MedicineOrder mo = ((MedicineOrderTableModel)MainFrame.this.jtblMedicineStockRequests.getModel()).getOrder(MainFrame.this.jtblMedicineStockRequests.getSelectedRow());
                MainFrame.this.controller.approveMedicineOrder(mo);
                MainFrame.this.jtblMedicineStockRequests.setModel(new MedicineOrderTableModel());
                JOptionPane.showMessageDialog(MainFrame.this, "Medicine Order Request Successfully Approved",  "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        jpmMedicineOrder.add(jmiApproveMedicineOrder);
        this.jtblMedicineStockRequests.setComponentPopupMenu(jpmMedicineOrder);
        
        JPopupMenu jpmMedicineCollection = new JPopupMenu();
        JMenuItem jmiApproveMedicineCollection = new JMenuItem("Approve Medicine Collection");
        jmiApproveMedicineCollection.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                PrescriptionMedicine pm = ((PrescriptionCollectionTableModel)MainFrame.this.jtblMedicineCollection.getModel()).getPrescriptionMedicine(MainFrame.this.jtblMedicineCollection.getSelectedRow());
                MainFrame.this.controller.approveMedicinePrescriptionCollection(pm.getUUID());
                MainFrame.this.jtblMedicineCollection.setModel(new PrescriptionCollectionTableModel());
                JOptionPane.showMessageDialog(MainFrame.this, "Medicine Collection Approved", "Success",  JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        jpmMedicineCollection.add(jmiApproveMedicineCollection);
        this.jtblMedicineCollection.setComponentPopupMenu(jpmMedicineCollection);
        
        JPopupMenu jpmPatientAppointments = new JPopupMenu();
        JMenuItem jmiPatientAppointmentRating = new JMenuItem("Rate Appointment With Doctor");
        jmiPatientAppointmentRating.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.jdPatientRateAppointment.pack();
                MainFrame.this.jdPatientRateAppointment.setLocationRelativeTo(MainFrame.this);
                MainFrame.this.jdPatientRateAppointment.setVisible(true);
            }
        });
        jpmPatientAppointments.add(jmiPatientAppointmentRating);
        this.jtblMyAppointments.setComponentPopupMenu(jpmPatientAppointments);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpSubmitPatientCreationRequest = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jtfGivenName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jtfLastName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtfAddress = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtfAge = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jbtnSubmitPatientAccountRequest = new javax.swing.JButton();
        jtfPassword = new javax.swing.JPasswordField();
        jtfGender = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jpLRC = new javax.swing.JPanel();
        jBtnViewLogin = new javax.swing.JButton();
        jBtnViewRquestPatientAcc = new javax.swing.JButton();
        jBtnViewCreateAdmin = new javax.swing.JButton();
        pLoginContainer = new javax.swing.JPanel();
        jpLoginRootView = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jtfUserID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jpfPassword = new javax.swing.JPasswordField();
        jBtnLogin = new javax.swing.JButton();
        jpCreateAdmin = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jtfCreateAdminGivenName = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jtfCreateAdminLastName = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jtfCreateAdminAddress = new javax.swing.JTextField();
        jbtnCreateAdminSubmit = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jpfCreateAdminPassword = new javax.swing.JPasswordField();
        jpPatientRoot = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblAllDoctors = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtblMyHistory = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtblMyAppointments = new javax.swing.JTable();
        jlblPatientName = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jdRequestAppointment = new javax.swing.JDialog();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jtfAppointmentSchedulerDoctorName = new javax.swing.JTextField();
        jtfAppointSchedulerPatientName = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jbtnSubmitAppointment = new javax.swing.JButton();
        jbtnCancelRequestAppointmentDialog = new javax.swing.JButton();
        jftAppointSchedulerDate = new javax.swing.JFormattedTextField();
        jpAdminRoot = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtblAdminAllDoctors = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtblAdminAllSecretaries = new javax.swing.JTable();
        jlblAdminName = new javax.swing.JLabel();
        jButtonAdminLogout = new javax.swing.JButton();
        jbtnAdminAddDoctor = new javax.swing.JButton();
        jbtnAdminAddSecretary = new javax.swing.JButton();
        jdCreateDoctor = new javax.swing.JDialog();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jtfCreateDoctorGivenName = new javax.swing.JTextField();
        jbtnCreateDoctorSubmit = new javax.swing.JButton();
        jbtnCreateDoctorCancel = new javax.swing.JButton();
        jtfCreateDoctorLastName = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jtfCreateDoctorAddress = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jpfCreateDoctorPassword = new javax.swing.JPasswordField();
        jdCreateSecretary = new javax.swing.JDialog();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jtfCreateSecretaryGivenName = new javax.swing.JTextField();
        jbtnCreateSecretarySubmit = new javax.swing.JButton();
        jbtnCreateSecretaryCancel = new javax.swing.JButton();
        jtfCreateSecretaryLastName = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jtfCreateSecretaryAddress = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jpfCreateSecretaryPassword = new javax.swing.JPasswordField();
        jdAdminViewDoctorRatings = new javax.swing.JDialog();
        jlblAdminViewDoctorRatingsDoctorName = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtblAdminViewDoctorRatings = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jtfAdminViewDoctorRatingPatientName = new javax.swing.JTextField();
        jtfAdminViewDoctorRatingComment = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jtfAdminViewDoctorRating = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtaAdminViewDoctorRatingComment = new javax.swing.JTextArea();
        jbtnAdminViewDoctorRatingSubmit = new javax.swing.JButton();
        jbtnAdminViewDoctorRatingCancel = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jpSecretaryRoot = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jtblSecretaryPatients = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jtblPatientAccCreationRequests = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jtblAccountRemovalRequests = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jtblAppointmentsRequests = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jtblMedicineCollection = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jtblMedicineStockRequests = new javax.swing.JTable();
        jlblSecretaryName = new javax.swing.JLabel();
        jBtnSecretaryLogOut = new javax.swing.JButton();
        jpDoctorRoot = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jtblDoctorAppointments = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jtblDoctorPatients = new javax.swing.JTable();
        jlblDoctorName = new javax.swing.JLabel();
        jButtonDoctorLogout = new javax.swing.JButton();
        jbtnDoctorCreateMedicine = new javax.swing.JButton();
        jdCreateMedicine = new javax.swing.JDialog();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jtfMedicineName = new javax.swing.JTextField();
        jbtnCreateMedicine = new javax.swing.JButton();
        jbtnCancelCreateMedicineDialog = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jtfMedicineQty = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jtaMedicineDescription = new javax.swing.JTextArea();
        jdDoctorAppointment = new javax.swing.JDialog();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jtfAppointmentPatientName = new javax.swing.JTextField();
        jbtnAppointmentDialogSubmit = new javax.swing.JButton();
        jbtnDialogAppointmentCancel = new javax.swing.JButton();
        jtfAppointmentPatientAge = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jtfAppointmentPatientAddress = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jScrollPane17 = new javax.swing.JScrollPane();
        jtaAppointmentDoctorNotes = new javax.swing.JTextArea();
        jtfAppointmentDate = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jdDoctorPrescribeMedicine = new javax.swing.JDialog();
        jLabel42 = new javax.swing.JLabel();
        jbtnSubmitPrescription = new javax.swing.JButton();
        jbtCancelPrescrptionDialog = new javax.swing.JButton();
        jScrollPane19 = new javax.swing.JScrollPane();
        jtblMedicinesForPrescription = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jtfPrescriptionQty = new javax.swing.JTextField();
        jdPatientRateAppointment = new javax.swing.JDialog();
        jLabel43 = new javax.swing.JLabel();
        jbtnPatientSubmitRating = new javax.swing.JButton();
        jbtPantientCancelSubmitRating = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        jtfPatientAppointmentRating = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        jtaPatientAppointmentComments = new javax.swing.JTextArea();
        jdDoctorViewPatientHistory = new javax.swing.JDialog();
        jLabel47 = new javax.swing.JLabel();
        jbtnCancelDocViewPatientHistory = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        jtfPatientHistoryDialogPatientName = new javax.swing.JTextField();
        jScrollPane20 = new javax.swing.JScrollPane();
        jtPatientHistoryDialog = new javax.swing.JTable();

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("GIVEN NAME");

        jtfGivenName.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("LAST NAME");

        jtfLastName.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("ADDRESS");

        jtfAddress.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("AGE");

        jtfAge.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("PASSWORD");

        jbtnSubmitPatientAccountRequest.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jbtnSubmitPatientAccountRequest.setText("SUBMIT REQUEST");
        jbtnSubmitPatientAccountRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSubmitPatientAccountRequestActionPerformed(evt);
            }
        });

        jtfGender.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText("GENDER");

        javax.swing.GroupLayout jpSubmitPatientCreationRequestLayout = new javax.swing.GroupLayout(jpSubmitPatientCreationRequest);
        jpSubmitPatientCreationRequest.setLayout(jpSubmitPatientCreationRequestLayout);
        jpSubmitPatientCreationRequestLayout.setHorizontalGroup(
            jpSubmitPatientCreationRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSubmitPatientCreationRequestLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jpSubmitPatientCreationRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpSubmitPatientCreationRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpSubmitPatientCreationRequestLayout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jtfAge, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jpSubmitPatientCreationRequestLayout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jtfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jpSubmitPatientCreationRequestLayout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jtfLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jpSubmitPatientCreationRequestLayout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jtfGivenName, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jpSubmitPatientCreationRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpSubmitPatientCreationRequestLayout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtfGender, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpSubmitPatientCreationRequestLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtfPassword))))
                    .addComponent(jbtnSubmitPatientAccountRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jpSubmitPatientCreationRequestLayout.setVerticalGroup(
            jpSubmitPatientCreationRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSubmitPatientCreationRequestLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jpSubmitPatientCreationRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfGivenName, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpSubmitPatientCreationRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpSubmitPatientCreationRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpSubmitPatientCreationRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfAge, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpSubmitPatientCreationRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpSubmitPatientCreationRequestLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfGender, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnSubmitPatientAccountRequest, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jBtnViewLogin.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jBtnViewLogin.setText("LOGIN");
        jBtnViewLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnViewLoginActionPerformed(evt);
            }
        });

        jBtnViewRquestPatientAcc.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jBtnViewRquestPatientAcc.setText("REQUEST PATIENT ACCOUNT");
        jBtnViewRquestPatientAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnViewRquestPatientAccActionPerformed(evt);
            }
        });

        jBtnViewCreateAdmin.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jBtnViewCreateAdmin.setText("CREATE ADMINISTRATOR");
        jBtnViewCreateAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnViewCreateAdminActionPerformed(evt);
            }
        });

        pLoginContainer.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        pLoginContainer.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jpLRCLayout = new javax.swing.GroupLayout(jpLRC);
        jpLRC.setLayout(jpLRCLayout);
        jpLRCLayout.setHorizontalGroup(
            jpLRCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpLRCLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jpLRCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pLoginContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jpLRCLayout.createSequentialGroup()
                        .addComponent(jBtnViewLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jBtnViewRquestPatientAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                        .addComponent(jBtnViewCreateAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(51, 51, 51))
        );
        jpLRCLayout.setVerticalGroup(
            jpLRCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLRCLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jpLRCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnViewLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnViewRquestPatientAcc, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnViewCreateAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pLoginContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(571, Short.MAX_VALUE))
        );

        this.setContentPane(this.jpLRC);
        this.pLoginContainer.add(this.jpLoginRootView, BorderLayout.CENTER);
        this.jBtnViewLogin.setForeground(Color.BLUE);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("USER ID");

        jtfUserID.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("PASSWORD");

        jpfPassword.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jBtnLogin.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jBtnLogin.setText("LOG IN");
        jBtnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpLoginRootViewLayout = new javax.swing.GroupLayout(jpLoginRootView);
        jpLoginRootView.setLayout(jpLoginRootViewLayout);
        jpLoginRootViewLayout.setHorizontalGroup(
            jpLoginRootViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLoginRootViewLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jpLoginRootViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jBtnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 671, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpLoginRootViewLayout.createSequentialGroup()
                        .addGroup(jpLoginRootViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jpLoginRootViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtfUserID)
                            .addComponent(jpfPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jpLoginRootViewLayout.setVerticalGroup(
            jpLoginRootViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpLoginRootViewLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jpLoginRootViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfUserID, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpLoginRootViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                    .addComponent(jpfPassword))
                .addGap(79, 79, 79)
                .addComponent(jBtnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(138, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("GIVEN NAME");

        jtfCreateAdminGivenName.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("LAST NAME");

        jtfCreateAdminLastName.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("ADDRESS");

        jtfCreateAdminAddress.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        jbtnCreateAdminSubmit.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jbtnCreateAdminSubmit.setText("CREATE ADMINISTRATOR ACCOUNT");
        jbtnCreateAdminSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCreateAdminSubmitActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("PASSWORD");

        jpfCreateAdminPassword.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N

        javax.swing.GroupLayout jpCreateAdminLayout = new javax.swing.GroupLayout(jpCreateAdmin);
        jpCreateAdmin.setLayout(jpCreateAdminLayout);
        jpCreateAdminLayout.setHorizontalGroup(
            jpCreateAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCreateAdminLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(jpCreateAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnCreateAdminSubmit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCreateAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jpCreateAdminLayout.createSequentialGroup()
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jpfCreateAdminPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jpCreateAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jpCreateAdminLayout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jtfCreateAdminGivenName, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpCreateAdminLayout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(jtfCreateAdminLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpCreateAdminLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jtfCreateAdminAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jpCreateAdminLayout.setVerticalGroup(
            jpCreateAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCreateAdminLayout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addGroup(jpCreateAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfCreateAdminGivenName, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jpCreateAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfCreateAdminLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jpCreateAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfCreateAdminAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jpCreateAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(jpfCreateAdminPassword))
                .addGap(25, 25, 25)
                .addComponent(jbtnCreateAdminSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        jtblAllDoctors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtblAllDoctors.setToolTipText("");
        jtblAllDoctors.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jtblAllDoctors);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("All Doctors", jPanel2);

        jtblMyHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtblMyHistory.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jtblMyHistory);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("My History", jPanel3);

        jtblMyAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtblMyAppointments.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jtblMyAppointments);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("My Appointments", jPanel4);

        jlblPatientName.setFont(new java.awt.Font("Lucida Grande", 3, 13)); // NOI18N
        jlblPatientName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton1.setForeground(new java.awt.Color(153, 0, 51));
        jButton1.setText("Request Account Termination");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Log Out");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpPatientRootLayout = new javax.swing.GroupLayout(jpPatientRoot);
        jpPatientRoot.setLayout(jpPatientRootLayout);
        jpPatientRootLayout.setHorizontalGroup(
            jpPatientRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPatientRootLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(18, 18, 18))
            .addGroup(jpPatientRootLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jlblPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 823, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpPatientRootLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        jpPatientRootLayout.setVerticalGroup(
            jpPatientRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpPatientRootLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jlblPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jTabbedPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpPatientRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        jdRequestAppointment.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jdRequestAppointment.setAlwaysOnTop(true);
        jdRequestAppointment.setModal(true);

        jLabel12.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("APPOINTMENT SCHEDULER");

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("DOCTOR");

        jtfAppointmentSchedulerDoctorName.setEditable(false);

        jtfAppointSchedulerPatientName.setEditable(false);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("PATIENT");

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("DATE");

        jbtnSubmitAppointment.setText("SUBMIT");
        jbtnSubmitAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSubmitAppointmentActionPerformed(evt);
            }
        });

        jbtnCancelRequestAppointmentDialog.setForeground(new java.awt.Color(204, 0, 0));
        jbtnCancelRequestAppointmentDialog.setText("CANCEL");
        jbtnCancelRequestAppointmentDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelRequestAppointmentDialogActionPerformed(evt);
            }
        });

        jftAppointSchedulerDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));

        javax.swing.GroupLayout jdRequestAppointmentLayout = new javax.swing.GroupLayout(jdRequestAppointment.getContentPane());
        jdRequestAppointment.getContentPane().setLayout(jdRequestAppointmentLayout);
        jdRequestAppointmentLayout.setHorizontalGroup(
            jdRequestAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdRequestAppointmentLayout.createSequentialGroup()
                .addGroup(jdRequestAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jdRequestAppointmentLayout.createSequentialGroup()
                        .addComponent(jbtnSubmitAppointment, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnCancelRequestAppointmentDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jdRequestAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jdRequestAppointmentLayout.createSequentialGroup()
                            .addGap(82, 82, 82)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jdRequestAppointmentLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtfAppointmentSchedulerDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jdRequestAppointmentLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jtfAppointSchedulerPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jdRequestAppointmentLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jftAppointSchedulerDate))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jdRequestAppointmentLayout.setVerticalGroup(
            jdRequestAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdRequestAppointmentLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel12)
                .addGap(32, 32, 32)
                .addGroup(jdRequestAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jtfAppointmentSchedulerDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jdRequestAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jtfAppointSchedulerPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jdRequestAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jftAppointSchedulerDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jdRequestAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnSubmitAppointment)
                    .addComponent(jbtnCancelRequestAppointmentDialog))
                .addGap(24, 24, 24))
        );

        jtblAdminAllDoctors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtblAdminAllDoctors.setToolTipText("");
        jtblAdminAllDoctors.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(jtblAdminAllDoctors);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("All Doctors", jPanel5);

        jtblAdminAllSecretaries.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtblAdminAllSecretaries.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(jtblAdminAllSecretaries);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("All Secretaries", jPanel6);

        jlblAdminName.setFont(new java.awt.Font("Lucida Grande", 3, 13)); // NOI18N
        jlblAdminName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButtonAdminLogout.setText("Log Out");
        jButtonAdminLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdminLogoutActionPerformed(evt);
            }
        });

        jbtnAdminAddDoctor.setText("Add New Doctor");
        jbtnAdminAddDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAdminAddDoctorActionPerformed(evt);
            }
        });

        jbtnAdminAddSecretary.setText("Add New Secretary");
        jbtnAdminAddSecretary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAdminAddSecretaryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpAdminRootLayout = new javax.swing.GroupLayout(jpAdminRoot);
        jpAdminRoot.setLayout(jpAdminRootLayout);
        jpAdminRootLayout.setHorizontalGroup(
            jpAdminRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAdminRootLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jlblAdminName, javax.swing.GroupLayout.PREFERRED_SIZE, 823, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jpAdminRootLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpAdminRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpAdminRootLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jbtnAdminAddDoctor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnAdminAddSecretary)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAdminLogout)
                        .addGap(14, 14, 14))
                    .addComponent(jTabbedPane2)))
        );
        jpAdminRootLayout.setVerticalGroup(
            jpAdminRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpAdminRootLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jlblAdminName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jTabbedPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpAdminRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonAdminLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jbtnAdminAddSecretary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnAdminAddDoctor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );

        jdCreateDoctor.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jdCreateDoctor.setAlwaysOnTop(true);
        jdCreateDoctor.setModal(true);

        jLabel17.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("CREATE DOCTOR");

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("GIVEN NAME");

        jbtnCreateDoctorSubmit.setText("CREATE");
        jbtnCreateDoctorSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCreateDoctorSubmitActionPerformed(evt);
            }
        });

        jbtnCreateDoctorCancel.setForeground(new java.awt.Color(204, 0, 0));
        jbtnCreateDoctorCancel.setText("CANCEL");
        jbtnCreateDoctorCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCreateDoctorCancelActionPerformed(evt);
            }
        });

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("LAST NAME");

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("ADDRESS");

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("PASSWORD");

        javax.swing.GroupLayout jdCreateDoctorLayout = new javax.swing.GroupLayout(jdCreateDoctor.getContentPane());
        jdCreateDoctor.getContentPane().setLayout(jdCreateDoctorLayout);
        jdCreateDoctorLayout.setHorizontalGroup(
            jdCreateDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdCreateDoctorLayout.createSequentialGroup()
                .addGroup(jdCreateDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jdCreateDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jdCreateDoctorLayout.createSequentialGroup()
                            .addComponent(jbtnCreateDoctorSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbtnCreateDoctorCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jdCreateDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jdCreateDoctorLayout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jdCreateDoctorLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfCreateDoctorGivenName, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jdCreateDoctorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jdCreateDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jdCreateDoctorLayout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfCreateDoctorLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jdCreateDoctorLayout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfCreateDoctorAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jdCreateDoctorLayout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jpfCreateDoctorPassword)))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jdCreateDoctorLayout.setVerticalGroup(
            jdCreateDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdCreateDoctorLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addGroup(jdCreateDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jtfCreateDoctorGivenName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jdCreateDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jtfCreateDoctorLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jdCreateDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jtfCreateDoctorAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jdCreateDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jpfCreateDoctorPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jdCreateDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnCreateDoctorSubmit)
                    .addComponent(jbtnCreateDoctorCancel))
                .addGap(24, 24, 24))
        );

        jdCreateSecretary.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jdCreateSecretary.setAlwaysOnTop(true);
        jdCreateSecretary.setModal(true);

        jLabel19.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("CREATE SECRETARY");

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("GIVEN NAME");

        jbtnCreateSecretarySubmit.setText("CREATE");
        jbtnCreateSecretarySubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCreateSecretarySubmitActionPerformed(evt);
            }
        });

        jbtnCreateSecretaryCancel.setForeground(new java.awt.Color(204, 0, 0));
        jbtnCreateSecretaryCancel.setText("CANCEL");
        jbtnCreateSecretaryCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCreateSecretaryCancelActionPerformed(evt);
            }
        });

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("LAST NAME");

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("ADDRESS");

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("PASSWORD");

        javax.swing.GroupLayout jdCreateSecretaryLayout = new javax.swing.GroupLayout(jdCreateSecretary.getContentPane());
        jdCreateSecretary.getContentPane().setLayout(jdCreateSecretaryLayout);
        jdCreateSecretaryLayout.setHorizontalGroup(
            jdCreateSecretaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdCreateSecretaryLayout.createSequentialGroup()
                .addGroup(jdCreateSecretaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jdCreateSecretaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jdCreateSecretaryLayout.createSequentialGroup()
                            .addComponent(jbtnCreateSecretarySubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbtnCreateSecretaryCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jdCreateSecretaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jdCreateSecretaryLayout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jdCreateSecretaryLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfCreateSecretaryGivenName, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jdCreateSecretaryLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jdCreateSecretaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jdCreateSecretaryLayout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfCreateSecretaryLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jdCreateSecretaryLayout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfCreateSecretaryAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jdCreateSecretaryLayout.createSequentialGroup()
                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jpfCreateSecretaryPassword)))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jdCreateSecretaryLayout.setVerticalGroup(
            jdCreateSecretaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdCreateSecretaryLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addGroup(jdCreateSecretaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jtfCreateSecretaryGivenName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jdCreateSecretaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jtfCreateSecretaryLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jdCreateSecretaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jtfCreateSecretaryAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jdCreateSecretaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jpfCreateSecretaryPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jdCreateSecretaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnCreateSecretarySubmit)
                    .addComponent(jbtnCreateSecretaryCancel))
                .addGap(24, 24, 24))
        );

        jdAdminViewDoctorRatings.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jdAdminViewDoctorRatings.setAlwaysOnTop(true);
        jdAdminViewDoctorRatings.setModal(true);

        jlblAdminViewDoctorRatingsDoctorName.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jlblAdminViewDoctorRatingsDoctorName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jtblAdminViewDoctorRatings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtblAdminViewDoctorRatings.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane6.setViewportView(jtblAdminViewDoctorRatings);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("PATIENT NAME");

        jtfAdminViewDoctorRatingPatientName.setEditable(false);

        jtfAdminViewDoctorRatingComment.setEditable(false);

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("COMMENTS");

        jtfAdminViewDoctorRating.setEditable(false);

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("RATING");

        jLabel30.setText("ADMINISTRATOR COMMENTS");

        jtaAdminViewDoctorRatingComment.setColumns(20);
        jtaAdminViewDoctorRatingComment.setRows(5);
        jScrollPane7.setViewportView(jtaAdminViewDoctorRatingComment);

        jbtnAdminViewDoctorRatingSubmit.setText("SUBMIT");
        jbtnAdminViewDoctorRatingSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAdminViewDoctorRatingSubmitActionPerformed(evt);
            }
        });

        jbtnAdminViewDoctorRatingCancel.setForeground(new java.awt.Color(204, 0, 0));
        jbtnAdminViewDoctorRatingCancel.setText("CANCEL");
        jbtnAdminViewDoctorRatingCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAdminViewDoctorRatingCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jbtnAdminViewDoctorRatingCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnAdminViewDoctorRatingSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfAdminViewDoctorRatingComment, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfAdminViewDoctorRatingPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jtfAdminViewDoctorRating, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                            .addComponent(jScrollPane7))))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jtfAdminViewDoctorRatingPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jtfAdminViewDoctorRatingComment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jtfAdminViewDoctorRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnAdminViewDoctorRatingSubmit)
                    .addComponent(jbtnAdminViewDoctorRatingCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jdAdminViewDoctorRatingsLayout = new javax.swing.GroupLayout(jdAdminViewDoctorRatings.getContentPane());
        jdAdminViewDoctorRatings.getContentPane().setLayout(jdAdminViewDoctorRatingsLayout);
        jdAdminViewDoctorRatingsLayout.setHorizontalGroup(
            jdAdminViewDoctorRatingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdAdminViewDoctorRatingsLayout.createSequentialGroup()
                .addGroup(jdAdminViewDoctorRatingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jdAdminViewDoctorRatingsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane6))
                    .addComponent(jlblAdminViewDoctorRatingsDoctorName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jdAdminViewDoctorRatingsLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jdAdminViewDoctorRatingsLayout.setVerticalGroup(
            jdAdminViewDoctorRatingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdAdminViewDoctorRatingsLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jlblAdminViewDoctorRatingsDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jtblSecretaryPatients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtblSecretaryPatients.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane13.setViewportView(jtblSecretaryPatients);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Manage  Patients", jPanel12);

        jtblPatientAccCreationRequests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtblPatientAccCreationRequests.setToolTipText("");
        jtblPatientAccCreationRequests.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane8.setViewportView(jtblPatientAccCreationRequests);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Account Creation Requests", jPanel7);

        jtblAccountRemovalRequests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtblAccountRemovalRequests.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane9.setViewportView(jtblAccountRemovalRequests);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Account Removal Requests", jPanel8);

        jtblAppointmentsRequests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtblAppointmentsRequests.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane10.setViewportView(jtblAppointmentsRequests);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Appointment Requests", jPanel9);

        jtblMedicineCollection.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtblMedicineCollection.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane11.setViewportView(jtblMedicineCollection);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Medicine Collection", jPanel10);

        jtblMedicineStockRequests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtblMedicineStockRequests.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane12.setViewportView(jtblMedicineStockRequests);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Medicine Stock Requests", jPanel11);

        jlblSecretaryName.setFont(new java.awt.Font("Lucida Grande", 3, 13)); // NOI18N
        jlblSecretaryName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jBtnSecretaryLogOut.setText("Log Out");
        jBtnSecretaryLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSecretaryLogOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpSecretaryRootLayout = new javax.swing.GroupLayout(jpSecretaryRoot);
        jpSecretaryRoot.setLayout(jpSecretaryRootLayout);
        jpSecretaryRootLayout.setHorizontalGroup(
            jpSecretaryRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSecretaryRootLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jlblSecretaryName, javax.swing.GroupLayout.PREFERRED_SIZE, 823, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpSecretaryRootLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpSecretaryRootLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBtnSecretaryLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jpSecretaryRootLayout.setVerticalGroup(
            jpSecretaryRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpSecretaryRootLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jlblSecretaryName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jTabbedPane3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBtnSecretaryLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jtblDoctorAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtblDoctorAppointments.setToolTipText("");
        jtblDoctorAppointments.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane14.setViewportView(jtblDoctorAppointments);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Appointments", jPanel13);

        jtblDoctorPatients.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtblDoctorPatients.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane15.setViewportView(jtblDoctorPatients);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Patients", jPanel14);

        jlblDoctorName.setFont(new java.awt.Font("Lucida Grande", 3, 13)); // NOI18N
        jlblDoctorName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButtonDoctorLogout.setText("Log Out");
        jButtonDoctorLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDoctorLogoutActionPerformed(evt);
            }
        });

        jbtnDoctorCreateMedicine.setText("Create Medicine");
        jbtnDoctorCreateMedicine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDoctorCreateMedicineActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpDoctorRootLayout = new javax.swing.GroupLayout(jpDoctorRoot);
        jpDoctorRoot.setLayout(jpDoctorRootLayout);
        jpDoctorRootLayout.setHorizontalGroup(
            jpDoctorRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDoctorRootLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jlblDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, 823, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jpDoctorRootLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDoctorRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpDoctorRootLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jbtnDoctorCreateMedicine)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonDoctorLogout)
                        .addGap(14, 14, 14))
                    .addComponent(jTabbedPane4)))
        );
        jpDoctorRootLayout.setVerticalGroup(
            jpDoctorRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDoctorRootLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jlblDoctorName, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jTabbedPane4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpDoctorRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonDoctorLogout, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jbtnDoctorCreateMedicine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );

        jdCreateMedicine.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jdCreateMedicine.setAlwaysOnTop(true);
        jdCreateMedicine.setModal(true);

        jLabel32.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("CREATE MEDICINE");

        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel33.setText("NAME");
        jLabel33.setToolTipText("");

        jbtnCreateMedicine.setText("CREATE");
        jbtnCreateMedicine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCreateMedicineActionPerformed(evt);
            }
        });

        jbtnCancelCreateMedicineDialog.setForeground(new java.awt.Color(204, 0, 0));
        jbtnCancelCreateMedicineDialog.setText("CANCEL");
        jbtnCancelCreateMedicineDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelCreateMedicineDialogActionPerformed(evt);
            }
        });

        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel34.setText("QUANTITY");

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel35.setText("DESCRIPTION");

        jtaMedicineDescription.setColumns(20);
        jtaMedicineDescription.setRows(5);
        jScrollPane16.setViewportView(jtaMedicineDescription);

        javax.swing.GroupLayout jdCreateMedicineLayout = new javax.swing.GroupLayout(jdCreateMedicine.getContentPane());
        jdCreateMedicine.getContentPane().setLayout(jdCreateMedicineLayout);
        jdCreateMedicineLayout.setHorizontalGroup(
            jdCreateMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdCreateMedicineLayout.createSequentialGroup()
                .addGroup(jdCreateMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jdCreateMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jdCreateMedicineLayout.createSequentialGroup()
                            .addComponent(jbtnCreateMedicine, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jbtnCancelCreateMedicineDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jdCreateMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jdCreateMedicineLayout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jdCreateMedicineLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfMedicineName, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jdCreateMedicineLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jdCreateMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jdCreateMedicineLayout.createSequentialGroup()
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfMedicineQty, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jdCreateMedicineLayout.createSequentialGroup()
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jdCreateMedicineLayout.setVerticalGroup(
            jdCreateMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdCreateMedicineLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel32)
                .addGap(18, 18, 18)
                .addGroup(jdCreateMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jtfMedicineName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jdCreateMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(jtfMedicineQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jdCreateMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addGroup(jdCreateMedicineLayout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jdCreateMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnCreateMedicine)
                    .addComponent(jbtnCancelCreateMedicineDialog))
                .addGap(24, 24, 24))
        );

        jdDoctorAppointment.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jdDoctorAppointment.setAlwaysOnTop(true);
        jdDoctorAppointment.setModal(true);

        jLabel36.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("PATIENT APPOINTMENT");

        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel37.setText("PATIENT NAME");

        jtfAppointmentPatientName.setEditable(false);

        jbtnAppointmentDialogSubmit.setText("CREATE");
        jbtnAppointmentDialogSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAppointmentDialogSubmitActionPerformed(evt);
            }
        });

        jbtnDialogAppointmentCancel.setForeground(new java.awt.Color(204, 0, 0));
        jbtnDialogAppointmentCancel.setText("CANCEL");
        jbtnDialogAppointmentCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDialogAppointmentCancelActionPerformed(evt);
            }
        });

        jtfAppointmentPatientAge.setEditable(false);

        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel38.setText("PATIENT AGE");

        jtfAppointmentPatientAddress.setEditable(false);

        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel39.setText("PATIENT ADDRESS");

        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel40.setText("DOCTORS NOTES");

        jtaAppointmentDoctorNotes.setColumns(20);
        jtaAppointmentDoctorNotes.setRows(5);
        jScrollPane17.setViewportView(jtaAppointmentDoctorNotes);

        jtfAppointmentDate.setEditable(false);

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setText("DATE");

        javax.swing.GroupLayout jdDoctorAppointmentLayout = new javax.swing.GroupLayout(jdDoctorAppointment.getContentPane());
        jdDoctorAppointment.getContentPane().setLayout(jdDoctorAppointmentLayout);
        jdDoctorAppointmentLayout.setHorizontalGroup(
            jdDoctorAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdDoctorAppointmentLayout.createSequentialGroup()
                .addGroup(jdDoctorAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jdDoctorAppointmentLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jdDoctorAppointmentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfAppointmentPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jdDoctorAppointmentLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jdDoctorAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jdDoctorAppointmentLayout.createSequentialGroup()
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfAppointmentPatientAge, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jdDoctorAppointmentLayout.createSequentialGroup()
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfAppointmentPatientAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jdDoctorAppointmentLayout.createSequentialGroup()
                                .addGroup(jdDoctorAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jdDoctorAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jdDoctorAppointmentLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jScrollPane17))
                                    .addComponent(jtfAppointmentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jdDoctorAppointmentLayout.createSequentialGroup()
                                .addComponent(jbtnAppointmentDialogSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtnDialogAppointmentCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jdDoctorAppointmentLayout.setVerticalGroup(
            jdDoctorAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdDoctorAppointmentLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel36)
                .addGap(18, 18, 18)
                .addGroup(jdDoctorAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jtfAppointmentPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jdDoctorAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(jtfAppointmentPatientAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jdDoctorAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jtfAppointmentPatientAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jdDoctorAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jtfAppointmentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jdDoctorAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jdDoctorAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnAppointmentDialogSubmit)
                    .addComponent(jbtnDialogAppointmentCancel))
                .addGap(12, 12, 12))
        );

        jdDoctorPrescribeMedicine.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jdDoctorPrescribeMedicine.setAlwaysOnTop(true);
        jdDoctorPrescribeMedicine.setModal(true);

        jLabel42.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel42.setText("PRESCRIBE MEDICINE");

        jbtnSubmitPrescription.setText("SUBMIT  PRESCRIPTION");
        jbtnSubmitPrescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSubmitPrescriptionActionPerformed(evt);
            }
        });

        jbtCancelPrescrptionDialog.setForeground(new java.awt.Color(204, 0, 0));
        jbtCancelPrescrptionDialog.setText("CANCEL");
        jbtCancelPrescrptionDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtCancelPrescrptionDialogActionPerformed(evt);
            }
        });

        jtblMedicinesForPrescription.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtblMedicinesForPrescription.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane19.setViewportView(jtblMedicinesForPrescription);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("QUANTITY");

        javax.swing.GroupLayout jdDoctorPrescribeMedicineLayout = new javax.swing.GroupLayout(jdDoctorPrescribeMedicine.getContentPane());
        jdDoctorPrescribeMedicine.getContentPane().setLayout(jdDoctorPrescribeMedicineLayout);
        jdDoctorPrescribeMedicineLayout.setHorizontalGroup(
            jdDoctorPrescribeMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdDoctorPrescribeMedicineLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jdDoctorPrescribeMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jdDoctorPrescribeMedicineLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfPrescriptionQty))
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jdDoctorPrescribeMedicineLayout.createSequentialGroup()
                        .addComponent(jbtnSubmitPrescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jbtCancelPrescrptionDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jdDoctorPrescribeMedicineLayout.setVerticalGroup(
            jdDoctorPrescribeMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdDoctorPrescribeMedicineLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel42)
                .addGap(17, 17, 17)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jdDoctorPrescribeMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtfPrescriptionQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jdDoctorPrescribeMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnSubmitPrescription)
                    .addComponent(jbtCancelPrescrptionDialog))
                .addGap(23, 23, 23))
        );

        jdPatientRateAppointment.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jdPatientRateAppointment.setAlwaysOnTop(true);
        jdPatientRateAppointment.setModal(true);

        jLabel43.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("RATE APPOINTMENT WITH DOCTOR");

        jbtnPatientSubmitRating.setText("SUBMIT RATING");
        jbtnPatientSubmitRating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPatientSubmitRatingActionPerformed(evt);
            }
        });

        jbtPantientCancelSubmitRating.setForeground(new java.awt.Color(204, 0, 0));
        jbtPantientCancelSubmitRating.setText("CANCEL");
        jbtPantientCancelSubmitRating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtPantientCancelSubmitRatingActionPerformed(evt);
            }
        });

        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel44.setText("RATING");

        jLabel45.setText("OF 5 STARS");

        jLabel46.setText("COMMENTS");

        jtaPatientAppointmentComments.setColumns(20);
        jtaPatientAppointmentComments.setRows(5);
        jScrollPane18.setViewportView(jtaPatientAppointmentComments);

        javax.swing.GroupLayout jdPatientRateAppointmentLayout = new javax.swing.GroupLayout(jdPatientRateAppointment.getContentPane());
        jdPatientRateAppointment.getContentPane().setLayout(jdPatientRateAppointmentLayout);
        jdPatientRateAppointmentLayout.setHorizontalGroup(
            jdPatientRateAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdPatientRateAppointmentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jdPatientRateAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jdPatientRateAppointmentLayout.createSequentialGroup()
                        .addComponent(jbtnPatientSubmitRating, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtPantientCancelSubmitRating, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jdPatientRateAppointmentLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jdPatientRateAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jdPatientRateAppointmentLayout.createSequentialGroup()
                        .addComponent(jScrollPane18)
                        .addContainerGap())
                    .addGroup(jdPatientRateAppointmentLayout.createSequentialGroup()
                        .addComponent(jLabel44)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtfPatientAppointmentRating, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel45))
                    .addComponent(jLabel46)))
        );
        jdPatientRateAppointmentLayout.setVerticalGroup(
            jdPatientRateAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdPatientRateAppointmentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel43)
                .addGap(18, 18, 18)
                .addGroup(jdPatientRateAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtfPatientAppointmentRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jdPatientRateAppointmentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnPatientSubmitRating)
                    .addComponent(jbtPantientCancelSubmitRating))
                .addGap(23, 23, 23))
        );

        jdDoctorViewPatientHistory.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jdDoctorViewPatientHistory.setAlwaysOnTop(true);
        jdDoctorViewPatientHistory.setModal(true);

        jLabel47.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setText("PATIENT HISTORY");

        jbtnCancelDocViewPatientHistory.setForeground(new java.awt.Color(204, 0, 0));
        jbtnCancelDocViewPatientHistory.setText("CANCEL");
        jbtnCancelDocViewPatientHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelDocViewPatientHistoryActionPerformed(evt);
            }
        });

        jLabel51.setText("PATIENT NAME");

        jtfPatientHistoryDialogPatientName.setEditable(false);

        jtPatientHistoryDialog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane20.setViewportView(jtPatientHistoryDialog);

        javax.swing.GroupLayout jdDoctorViewPatientHistoryLayout = new javax.swing.GroupLayout(jdDoctorViewPatientHistory.getContentPane());
        jdDoctorViewPatientHistory.getContentPane().setLayout(jdDoctorViewPatientHistoryLayout);
        jdDoctorViewPatientHistoryLayout.setHorizontalGroup(
            jdDoctorViewPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdDoctorViewPatientHistoryLayout.createSequentialGroup()
                .addGroup(jdDoctorViewPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jdDoctorViewPatientHistoryLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbtnCancelDocViewPatientHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jdDoctorViewPatientHistoryLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jdDoctorViewPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                            .addGroup(jdDoctorViewPatientHistoryLayout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtfPatientHistoryDialogPatientName)))))
                .addContainerGap())
        );
        jdDoctorViewPatientHistoryLayout.setVerticalGroup(
            jdDoctorViewPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jdDoctorViewPatientHistoryLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jdDoctorViewPatientHistoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(jtfPatientHistoryDialogPatientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jbtnCancelDocViewPatientHistory)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 23, 1000, 850));
        setMaximumSize(new java.awt.Dimension(1000, 850));
        setMinimumSize(new java.awt.Dimension(1000, 850));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnViewLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnViewLoginActionPerformed
        this.jBtnViewLogin.setForeground(Color.BLUE);
        this.jBtnViewRquestPatientAcc.setForeground(Color.BLACK);
        this.jBtnViewCreateAdmin.setForeground(Color.BLACK);
        
        this.pLoginContainer.removeAll();
        this.pLoginContainer.add(this.jpLoginRootView, BorderLayout.CENTER);
        this.pLoginContainer.repaint();
        this.revalidate();
    }//GEN-LAST:event_jBtnViewLoginActionPerformed

    private void switchToLogin() {
        this.controller.persistData();
        this.jBtnViewLogin.setForeground(Color.BLUE);
        this.jBtnViewRquestPatientAcc.setForeground(Color.BLACK);
        this.jBtnViewCreateAdmin.setForeground(Color.BLACK);
        
        this.pLoginContainer.removeAll();
        this.pLoginContainer.add(this.jpLoginRootView, BorderLayout.CENTER);
        this.pLoginContainer.repaint();
        this.setContentPane(this.jpLRC);
        this.revalidate();
    }
    
    private void jBtnViewRquestPatientAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnViewRquestPatientAccActionPerformed
        this.jBtnViewRquestPatientAcc.setForeground(Color.BLUE);
        this.jBtnViewLogin.setForeground(Color.BLACK);
        this.jBtnViewCreateAdmin.setForeground(Color.BLACK);
        
        this.pLoginContainer.removeAll();
        this.pLoginContainer.add(this.jpSubmitPatientCreationRequest, BorderLayout.CENTER);
        this.pLoginContainer.repaint();
        this.revalidate();
    }//GEN-LAST:event_jBtnViewRquestPatientAccActionPerformed

    private void jBtnViewCreateAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnViewCreateAdminActionPerformed
        // TODO add your handling code here:
        this.jBtnViewRquestPatientAcc.setForeground(Color.BLACK);
        this.jBtnViewLogin.setForeground(Color.BLACK);
        this.jBtnViewCreateAdmin.setForeground(Color.BLUE);
        
        this.pLoginContainer.removeAll();
        this.pLoginContainer.add(this.jpCreateAdmin, BorderLayout.CENTER);
        this.pLoginContainer.repaint();
        this.revalidate();
    }//GEN-LAST:event_jBtnViewCreateAdminActionPerformed

    private void jBtnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLoginActionPerformed
        // TODO add your handling code here:
        String userID = this.jtfUserID.getText();
        String password = new String(this.jpfPassword.getPassword());
        
        String[] userIDParts = userID.split("_");
        if (userIDParts.length != 2) {
            System.out.println("Invalid user ID detected; userID: " + userID);
            JOptionPane.showMessageDialog(this, "Invalid user id", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.controller.persistData();
        this.controller.loadAllData();
        
        char userType = userIDParts[0].toCharArray()[0];
        switch(userType) {
            case 'A': {
                Administrator admin = this.controller.loginAdmin(userID, password);
                if (admin != null) {
                    this.switchToAdminMode(admin);
                    return;
                }
                break;
            }
            case 'D': {
                Doctor doctor = this.controller.loginDoctor(userID, password);
                if (doctor != null) {
                    this.switchToDoctorMode(doctor);
                    return;
                }
                break;
            }
            case 'P': {
                Patient patient = this.controller.loginPatient(userID, password);
                if (patient != null) {
                    this.switchToPatientMode(patient);
                    return;
                }
                break;
            }
            case 'S': {
                Secretary secretary = this.controller.loginSecretary(userID, password);
                if (secretary != null) {
                    this.switchToSecretaryMode(secretary);
                    return;
                }
                break;
            }
            default: {
                System.out.println(String.format("Failed login; userID: %s,  password: %s", userID,  password));
            }
        }
        JOptionPane.showMessageDialog(this, "Login failed. Check password or user id", "Failed Login", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_jBtnLoginActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.switchToLogin();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jbtnCancelRequestAppointmentDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelRequestAppointmentDialogActionPerformed
        this.jdRequestAppointment.dispose();
        this.jftAppointSchedulerDate.setText("");
    }//GEN-LAST:event_jbtnCancelRequestAppointmentDialogActionPerformed

    private void jbtnSubmitAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSubmitAppointmentActionPerformed
        // TODO add your handling code here:
        System.out.println("Submit Appointment!");
        Doctor d = this.selectedDoctorForAppointment;
        Patient p = this.selectedPatientForAppointment;
        try {
            AppointmentRequest ar = new AppointmentRequest(d.getUUID(), p.getUUID(), Appointment.sdf.parse(this.jftAppointSchedulerDate.getText().trim()), false);
            this.controller.submitAppointmentRequest(ar);
            this.jftAppointSchedulerDate.setText("");
            this.jdRequestAppointment.dispose();
            JOptionPane.showMessageDialog(this, "Appointment Successfully scheduled", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (ParseException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnSubmitAppointmentActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        System.out.println("Window closing");
        this.controller.persistData();
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.controller.requestAccountTermination(this.patient.getUUID());
        JOptionPane.showMessageDialog(this, "Account Termination Request Submitted", "Success", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jbtnCreateAdminSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCreateAdminSubmitActionPerformed
        String givenName = this.jtfCreateAdminGivenName.getText();
        String lastName = this.jtfCreateAdminLastName.getText();
        String address = this.jtfCreateAdminAddress.getText();
        String password = new String(this.jpfCreateAdminPassword.getPassword());
        Administrator admin = this.controller.createAdmin(givenName, lastName, address, password);
        this.switchToAdminMode(admin);
    }//GEN-LAST:event_jbtnCreateAdminSubmitActionPerformed

    private void jButtonAdminLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdminLogoutActionPerformed
        this.switchToLogin();
    }//GEN-LAST:event_jButtonAdminLogoutActionPerformed

    private void jbtnCreateDoctorSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCreateDoctorSubmitActionPerformed
        String givenName = this.jtfCreateDoctorGivenName.getText();
        String lastName = this.jtfCreateDoctorLastName.getText();
        String address = this.jtfCreateDoctorAddress.getText();
        String password = new String(this.jpfCreateDoctorPassword.getPassword());
        this.controller.createDoctor(givenName, lastName, address, password);
        this.jtblAdminAllDoctors.setModel(new AdminDoctorTableModel());
        this.jdCreateDoctor.dispose();
    }//GEN-LAST:event_jbtnCreateDoctorSubmitActionPerformed

    private void jbtnCreateDoctorCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCreateDoctorCancelActionPerformed
       this.jdCreateDoctor.dispose();
    }//GEN-LAST:event_jbtnCreateDoctorCancelActionPerformed

    private void jbtnCreateSecretarySubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCreateSecretarySubmitActionPerformed
        String givenName = this.jtfCreateSecretaryGivenName.getText();
        String lastName = this.jtfCreateSecretaryLastName.getText();
        String address = this.jtfCreateSecretaryAddress.getText();
        String password = new String(this.jpfCreateSecretaryPassword.getPassword());
        this.controller.createSecretary(givenName, lastName, address, password);
        this.jtblAdminAllSecretaries.setModel(new AdminSecretaryTableModel());
        this.jdCreateSecretary.dispose();
    }//GEN-LAST:event_jbtnCreateSecretarySubmitActionPerformed

    private void jbtnCreateSecretaryCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCreateSecretaryCancelActionPerformed
        this.jdCreateSecretary.dispose();
    }//GEN-LAST:event_jbtnCreateSecretaryCancelActionPerformed

    private void jbtnAdminAddDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAdminAddDoctorActionPerformed
        this.jdCreateDoctor.pack();
        this.jdCreateDoctor.setLocationRelativeTo(MainFrame.this);
        this.jdCreateDoctor.setVisible(true);
    }//GEN-LAST:event_jbtnAdminAddDoctorActionPerformed

    private void jbtnAdminAddSecretaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAdminAddSecretaryActionPerformed
        this.jdCreateSecretary.pack();
        this.jdCreateSecretary.setLocationRelativeTo(MainFrame.this);
        this.jdCreateSecretary.setVisible(true);
    }//GEN-LAST:event_jbtnAdminAddSecretaryActionPerformed

    private void jbtnAdminViewDoctorRatingSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAdminViewDoctorRatingSubmitActionPerformed
        int row = MainFrame.this.jtblAdminViewDoctorRatings.getSelectedRow();
        if (row < 0) return;
        
        DoctorRating dr = ((AdminViewDoctorRatingTableModel)MainFrame.this.jtblAdminViewDoctorRatings.getModel()).getDoctorRating(row);
        this.controller.createAdministratorFeedback(dr.getDoctorUUID(), this.jtaAdminViewDoctorRatingComment.getText());
        this.jtaAdminViewDoctorRatingComment.setText("");
        this.jdAdminViewDoctorRatings.dispose();
        JOptionPane.showMessageDialog(this, "Feedback Submitted to Doctor", "Doctor Comment", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jbtnAdminViewDoctorRatingSubmitActionPerformed

    private void jbtnAdminViewDoctorRatingCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAdminViewDoctorRatingCancelActionPerformed
        this.jdAdminViewDoctorRatings.dispose();
    }//GEN-LAST:event_jbtnAdminViewDoctorRatingCancelActionPerformed

    private void jBtnSecretaryLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSecretaryLogOutActionPerformed
        // TODO add your handling code here:
        this.switchToLogin();
    }//GEN-LAST:event_jBtnSecretaryLogOutActionPerformed

    private void jbtnSubmitPatientAccountRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSubmitPatientAccountRequestActionPerformed
        try {
            String givenName = this.jtfGivenName.getText();
            String lastName = this.jtfLastName.getText();
            String address = this.jtfAddress.getText();
            String gender = this.jtfGender.getText();
            String password = new String(this.jtfPassword.getPassword());
            int age = Integer.valueOf(this.jtfAge.getText());
            this.controller.createPatientCreationRequest(givenName, lastName, address, password, gender, age);
            JOptionPane.showMessageDialog(this, "Your Patient account creation request was successfully submitted. Thank you!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Something went wrong. Fill all fields correctly and try again", "Error Occured", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_jbtnSubmitPatientAccountRequestActionPerformed

    private void jButtonDoctorLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDoctorLogoutActionPerformed
        this.switchToLogin();
    }//GEN-LAST:event_jButtonDoctorLogoutActionPerformed

    private void jbtnDoctorCreateMedicineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDoctorCreateMedicineActionPerformed
        this.jdCreateMedicine.pack();
        this.jdCreateMedicine.setLocationRelativeTo(this);
        this.jdCreateMedicine.setVisible(true);
    }//GEN-LAST:event_jbtnDoctorCreateMedicineActionPerformed

    private void jbtnCreateMedicineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCreateMedicineActionPerformed
        this.controller.submitMedicineOrder(
                this.doctor.getUUID(),
                this.jtfMedicineName.getText(),
                Integer.valueOf(this.jtfMedicineQty.getText()),
                this.jtaMedicineDescription.getText()
        );
        this.jdCreateMedicine.dispose();
        JOptionPane.showMessageDialog(this, "Medicine Order successfully submitted.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jbtnCreateMedicineActionPerformed

    private void jbtnCancelCreateMedicineDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelCreateMedicineDialogActionPerformed
        this.jtfMedicineName.setText("");
        this.jtfMedicineQty.setText("");
        this.jtaMedicineDescription.setText("");
        this.jdCreateMedicine.dispose();
    }//GEN-LAST:event_jbtnCancelCreateMedicineDialogActionPerformed

    private void jbtnAppointmentDialogSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAppointmentDialogSubmitActionPerformed
        Appointment a = this.selectedAppointment;
        a.setNotes(this.jtaAppointmentDoctorNotes.getText());
        this.controller.createPatientHistory(a);
        this.jdDoctorAppointment.dispose();
        JOptionPane.showMessageDialog(this, "Appointment Notes saved", "Success", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jbtnAppointmentDialogSubmitActionPerformed

    private void jbtnDialogAppointmentCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDialogAppointmentCancelActionPerformed
        this.jtfAppointmentDate.setText("");
        this.jtfAppointmentPatientAddress.setText("");
        this.jtfAppointmentPatientAge.setText("");
        this.jtfAppointmentPatientName.setText("");
        this.jdDoctorAppointment.dispose();
    }//GEN-LAST:event_jbtnDialogAppointmentCancelActionPerformed

    private void jbtnSubmitPrescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSubmitPrescriptionActionPerformed
        int selection = this.jtblMedicinesForPrescription.getSelectedRow();
        if (selection < 0) {
            JOptionPane.showMessageDialog(this, "Please select a Medicine first", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Medicine m = ((PrescriptionMedicineTableModel)this.jtblMedicinesForPrescription.getModel()).getMedicine(selection);
        int qty = Integer.valueOf(this.jtfPrescriptionQty.getText());
        Appointment a = ((DoctorAppointmentTableModel)MainFrame.this.jtblDoctorAppointments.getModel()).getAppointment(MainFrame.this.jtblDoctorAppointments.getSelectedRow());
        
        this.controller.submitDoctorPrescriptionMedicine(m.getUUID(), a.getUUID(), qty);
        this.jtfPrescriptionQty.setText("");
        this.jdDoctorPrescribeMedicine.dispose();
        JOptionPane.showMessageDialog(this, "Prescription submitted", "Success", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jbtnSubmitPrescriptionActionPerformed

    private void jbtCancelPrescrptionDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtCancelPrescrptionDialogActionPerformed
        this.jdDoctorPrescribeMedicine.dispose();
    }//GEN-LAST:event_jbtCancelPrescrptionDialogActionPerformed

    private void jbtnPatientSubmitRatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPatientSubmitRatingActionPerformed
        double rating = Double.valueOf(this.jtfPatientAppointmentRating.getText());
        String comments = this.jtaPatientAppointmentComments.getText();
        Appointment a = ((PatientAppointmentTableModel)this.jtblMyAppointments.getModel()).getDoctor(this.jtblMyAppointments.getSelectedRow());
        this.controller.submitPatientAppointmentRating(a.getPatientUUID(),  a.getDoctorUUID(), comments, rating);                            
        this.jdPatientRateAppointment.dispose();
        this.jtaPatientAppointmentComments.setText("");
        this.jtfPatientAppointmentRating.setText("");
        this.jtblAllDoctors.setModel(new DoctorsTableModel());
        JOptionPane.showMessageDialog(this, "Your Rating was successfully  submitted", "Success", JOptionPane.INFORMATION_MESSAGE);
        
    }//GEN-LAST:event_jbtnPatientSubmitRatingActionPerformed

    private void jbtPantientCancelSubmitRatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtPantientCancelSubmitRatingActionPerformed
        this.jdPatientRateAppointment.dispose();
    }//GEN-LAST:event_jbtPantientCancelSubmitRatingActionPerformed

    private void jbtnCancelDocViewPatientHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelDocViewPatientHistoryActionPerformed
        this.jdDoctorViewPatientHistory.dispose();
    }//GEN-LAST:event_jbtnCancelDocViewPatientHistoryActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnLogin;
    private javax.swing.JButton jBtnSecretaryLogOut;
    private javax.swing.JButton jBtnViewCreateAdmin;
    private javax.swing.JButton jBtnViewLogin;
    private javax.swing.JButton jBtnViewRquestPatientAcc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAdminLogout;
    private javax.swing.JButton jButtonDoctorLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JButton jbtCancelPrescrptionDialog;
    private javax.swing.JButton jbtPantientCancelSubmitRating;
    private javax.swing.JButton jbtnAdminAddDoctor;
    private javax.swing.JButton jbtnAdminAddSecretary;
    private javax.swing.JButton jbtnAdminViewDoctorRatingCancel;
    private javax.swing.JButton jbtnAdminViewDoctorRatingSubmit;
    private javax.swing.JButton jbtnAppointmentDialogSubmit;
    private javax.swing.JButton jbtnCancelCreateMedicineDialog;
    private javax.swing.JButton jbtnCancelDocViewPatientHistory;
    private javax.swing.JButton jbtnCancelRequestAppointmentDialog;
    private javax.swing.JButton jbtnCreateAdminSubmit;
    private javax.swing.JButton jbtnCreateDoctorCancel;
    private javax.swing.JButton jbtnCreateDoctorSubmit;
    private javax.swing.JButton jbtnCreateMedicine;
    private javax.swing.JButton jbtnCreateSecretaryCancel;
    private javax.swing.JButton jbtnCreateSecretarySubmit;
    private javax.swing.JButton jbtnDialogAppointmentCancel;
    private javax.swing.JButton jbtnDoctorCreateMedicine;
    private javax.swing.JButton jbtnPatientSubmitRating;
    private javax.swing.JButton jbtnSubmitAppointment;
    private javax.swing.JButton jbtnSubmitPatientAccountRequest;
    private javax.swing.JButton jbtnSubmitPrescription;
    private javax.swing.JDialog jdAdminViewDoctorRatings;
    private javax.swing.JDialog jdCreateDoctor;
    private javax.swing.JDialog jdCreateMedicine;
    private javax.swing.JDialog jdCreateSecretary;
    private javax.swing.JDialog jdDoctorAppointment;
    private javax.swing.JDialog jdDoctorPrescribeMedicine;
    private javax.swing.JDialog jdDoctorViewPatientHistory;
    private javax.swing.JDialog jdPatientRateAppointment;
    private javax.swing.JDialog jdRequestAppointment;
    private javax.swing.JFormattedTextField jftAppointSchedulerDate;
    private javax.swing.JLabel jlblAdminName;
    private javax.swing.JLabel jlblAdminViewDoctorRatingsDoctorName;
    private javax.swing.JLabel jlblDoctorName;
    private javax.swing.JLabel jlblPatientName;
    private javax.swing.JLabel jlblSecretaryName;
    private javax.swing.JPanel jpAdminRoot;
    private javax.swing.JPanel jpCreateAdmin;
    private javax.swing.JPanel jpDoctorRoot;
    private javax.swing.JPanel jpLRC;
    private javax.swing.JPanel jpLoginRootView;
    private javax.swing.JPanel jpPatientRoot;
    private javax.swing.JPanel jpSecretaryRoot;
    private javax.swing.JPanel jpSubmitPatientCreationRequest;
    private javax.swing.JPasswordField jpfCreateAdminPassword;
    private javax.swing.JPasswordField jpfCreateDoctorPassword;
    private javax.swing.JPasswordField jpfCreateSecretaryPassword;
    private javax.swing.JPasswordField jpfPassword;
    private javax.swing.JTable jtPatientHistoryDialog;
    private javax.swing.JTextArea jtaAdminViewDoctorRatingComment;
    private javax.swing.JTextArea jtaAppointmentDoctorNotes;
    private javax.swing.JTextArea jtaMedicineDescription;
    private javax.swing.JTextArea jtaPatientAppointmentComments;
    private javax.swing.JTable jtblAccountRemovalRequests;
    private javax.swing.JTable jtblAdminAllDoctors;
    private javax.swing.JTable jtblAdminAllSecretaries;
    private javax.swing.JTable jtblAdminViewDoctorRatings;
    private javax.swing.JTable jtblAllDoctors;
    private javax.swing.JTable jtblAppointmentsRequests;
    private javax.swing.JTable jtblDoctorAppointments;
    private javax.swing.JTable jtblDoctorPatients;
    private javax.swing.JTable jtblMedicineCollection;
    private javax.swing.JTable jtblMedicineStockRequests;
    private javax.swing.JTable jtblMedicinesForPrescription;
    private javax.swing.JTable jtblMyAppointments;
    private javax.swing.JTable jtblMyHistory;
    private javax.swing.JTable jtblPatientAccCreationRequests;
    private javax.swing.JTable jtblSecretaryPatients;
    private javax.swing.JTextField jtfAddress;
    private javax.swing.JTextField jtfAdminViewDoctorRating;
    private javax.swing.JTextField jtfAdminViewDoctorRatingComment;
    private javax.swing.JTextField jtfAdminViewDoctorRatingPatientName;
    private javax.swing.JTextField jtfAge;
    private javax.swing.JTextField jtfAppointSchedulerPatientName;
    private javax.swing.JTextField jtfAppointmentDate;
    private javax.swing.JTextField jtfAppointmentPatientAddress;
    private javax.swing.JTextField jtfAppointmentPatientAge;
    private javax.swing.JTextField jtfAppointmentPatientName;
    private javax.swing.JTextField jtfAppointmentSchedulerDoctorName;
    private javax.swing.JTextField jtfCreateAdminAddress;
    private javax.swing.JTextField jtfCreateAdminGivenName;
    private javax.swing.JTextField jtfCreateAdminLastName;
    private javax.swing.JTextField jtfCreateDoctorAddress;
    private javax.swing.JTextField jtfCreateDoctorGivenName;
    private javax.swing.JTextField jtfCreateDoctorLastName;
    private javax.swing.JTextField jtfCreateSecretaryAddress;
    private javax.swing.JTextField jtfCreateSecretaryGivenName;
    private javax.swing.JTextField jtfCreateSecretaryLastName;
    private javax.swing.JTextField jtfGender;
    private javax.swing.JTextField jtfGivenName;
    private javax.swing.JTextField jtfLastName;
    private javax.swing.JTextField jtfMedicineName;
    private javax.swing.JTextField jtfMedicineQty;
    private javax.swing.JPasswordField jtfPassword;
    private javax.swing.JTextField jtfPatientAppointmentRating;
    private javax.swing.JTextField jtfPatientHistoryDialogPatientName;
    private javax.swing.JTextField jtfPrescriptionQty;
    private javax.swing.JTextField jtfUserID;
    private javax.swing.JPanel pLoginContainer;
    // End of variables declaration//GEN-END:variables
    private void switchToAdminMode(Administrator admin) {
        System.out.println("Switching to Administrator mode");
        this.administrator = admin;
        this.jlblAdminName.setText("Welcome Administrator " + admin.getGivenName() + " " + admin.getLastName());
        this.jtblAdminAllDoctors.setModel(new AdminDoctorTableModel());
        this.jtblAdminAllSecretaries.setModel(new AdminSecretaryTableModel());
        
        this.setContentPane(this.jpAdminRoot);
        this.revalidate();
    }

    private void switchToDoctorMode(Doctor doctor) {
        System.out.println("Switcing to Doctor mode");
        this.doctor = doctor;
        this.jlblDoctorName.setText(String.format("Welcome Dr. %s %s", doctor.getGivenName(), doctor.getLastName()));
        this.jtblDoctorAppointments.setModel(new DoctorAppointmentTableModel());
        this.jtblDoctorPatients.setModel(new PatientTableModel());
        this.jtblMedicinesForPrescription.setModel(new PrescriptionMedicineTableModel());
        this.setContentPane(this.jpDoctorRoot);
        this.revalidate();
    }

    private void switchToPatientMode(Patient patient) {
        System.out.println("Swithching to patient mode: " + patient.toPersistableTxtFormat());
        this.patient = patient;
        this.jlblPatientName.setText("Welcome, " + patient.getGivenName() + " " + patient.getLastName());
        this.jtblAllDoctors.setModel(new DoctorsTableModel());
        this.jtblMyAppointments.setModel(new PatientAppointmentTableModel());
        this.jtblMyHistory.setModel(new PatientHistoryTableModel(patient.getUUID()));
        // this.removeAll();
        this.setContentPane(this.jpPatientRoot);
        this.revalidate();
    }

    private void switchToSecretaryMode(Secretary secretary) {
        System.out.println("Switching to Secretary mode");
        this.secretary = secretary;
        this.jlblSecretaryName.setText(String.format("Welcome Secretary %s %s", secretary.getGivenName(), secretary.getLastName()));
        
        // set models
        this.jtblPatientAccCreationRequests.setModel(new PatientCreationRequestTableModel());
        this.jtblAccountRemovalRequests.setModel(new PatientRemovalRequestTableModel());
        this.jtblSecretaryPatients.setModel(new PatientTableModel());
        this.jtblAppointmentsRequests.setModel(new AppointmentRequestTableModel());
        this.jtblMedicineStockRequests.setModel(new MedicineOrderTableModel());
        this.jtblMedicineCollection.setModel(new PrescriptionCollectionTableModel());
        
        // update view
        this.setContentPane(this.jpSecretaryRoot);
        this.revalidate();
    }
    
    
    class DoctorsTableModel extends AbstractTableModel {
        private final List<Doctor> doctors;
        DoctorsTableModel() {
            this.doctors = controller.getDoctorsList();
        }
        
        @Override
        public int getRowCount() {
            return this.doctors.size();
        }

        @Override
        public int getColumnCount() {
            return 4;
        }
        
        public Doctor getDoctor(int index) {
            return this.doctors.get(index);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0: return "ID";
                case 1: return "Given Name";
                case 2: return "Last Name";
                case 3: return "Rating";
            }
            return null;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Doctor doctor = this.doctors.get(rowIndex);
            double ratingD = controller.getDoctorRating(doctor.getUUID());
            String ratingS = ratingD == 0 ? "--" : String.valueOf(ratingD) + " stars";
            switch (columnIndex) {
                case 0: return doctor.getUUID();
                case 1: return doctor.getGivenName();
                case 2: return doctor.getLastName();
                case 3: return ratingS;
            }
            return null;
        }
    }
    
    class AdminDoctorTableModel extends AbstractTableModel {
        private final List<Doctor> doctors;
        AdminDoctorTableModel() {
            this.doctors = controller.getDoctorsList();
        }
        
        @Override
        public int getRowCount() {
            return this.doctors.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
        }
        
        public Doctor getDoctor(int index) {
            return this.doctors.get(index);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0: return "ID";
                case 1: return "Given Name";
                case 2: return "Last Name";
            }
            return null;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Doctor doctor = this.doctors.get(rowIndex);
            switch (columnIndex) {
                case 0: return doctor.getUUID();
                case 1: return doctor.getGivenName();
                case 2: return doctor.getLastName();
            }
            return null;
        }
    }
     
     class AdminSecretaryTableModel extends AbstractTableModel {
        private final List<Secretary> secretaries;
        AdminSecretaryTableModel() {
            this.secretaries = controller.getSecretariesList();
        }
        
        @Override
        public int getRowCount() {
            return this.secretaries.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
        }
        
        public Secretary getSecretary(int index) {
            return this.secretaries.get(index);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0: return "ID";
                case 1: return "Given Name";
                case 2: return "Last Name";
            }
            return null;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Secretary secretary = this.secretaries.get(rowIndex);
            switch (columnIndex) {
                case 0: return secretary.getUUID();
                case 1: return secretary.getGivenName();
                case 2: return secretary.getLastName();
            }
            return null;
        }
    }
    
    class PatientHistoryTableModel extends AbstractTableModel {
        private final List<PatientHistory> histories;
        private String patientUUID;
        PatientHistoryTableModel(String pUUID) {
            this.patientUUID = pUUID;
            System.out.println("controller == null? " + (null == controller));
            this.histories = controller.getPatientHistoryList(this.patientUUID);
        }
        
        @Override
        public int getRowCount() {
            return this.histories.size();
        }

        @Override
        public int getColumnCount() {
            return 4;
        }
        
        public PatientHistory getDoctor(int index) {
            return this.histories.get(index);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0: return "ID";
                case 1: return "Event Date";
                case 2: return "Doctor Notes";
                case 3: return "Doctor Name";
            }
            return null;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            PatientHistory history = this.histories.get(rowIndex);
            Appointment a = controller.getAppointment(history.getAppointmentUUID());
            Doctor d = controller.getDoctor(a.getDoctorUUID());
            Patient p = controller.getPatient(a.getPatientUUID());
            switch (columnIndex) {
                case 0: return history.getUUID();
                case 1: return Appointment.sdf.format(a.getDate());
                case 2: return a.getNotes();
                case 3: return String.format("%s %s", d.getGivenName(), d.getLastName());
            }
            return null;
        }
    }
    
    class PatientAppointmentTableModel extends AbstractTableModel {
        private final List<Appointment> appointments;
        PatientAppointmentTableModel() {
            this.appointments = controller.getPatientAppointmentList(patient.getUUID());
        }
        
        @Override
        public int getRowCount() {
            return this.appointments.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
        }
        
        public Appointment getDoctor(int index) {
            return this.appointments.get(index);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0: return "ID";
                case 1: return "Date";
                case 2: return "Doctor";
            }
            return null;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Appointment a = this.appointments.get(rowIndex);
            Doctor d = controller.getDoctor(a.getDoctorUUID());
            switch (columnIndex) {
                case 0: return a.getUUID();
                case 1: return Appointment.sdf.format(a.getDate());
                case 2: return d.getGivenName() + " " + d.getLastName();
            }
            return null;
        }
    }
    
    class AdminViewDoctorRatingTableModel extends AbstractTableModel {
        private final List<DoctorRating> ratings;
        private final Doctor doctor;
        AdminViewDoctorRatingTableModel(Doctor doctor) {
            this.ratings = controller.getDoctorRatings(doctor.getUUID());
            this.doctor = doctor;
        }
        
        @Override
        public int getRowCount() {
            return this.ratings.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
        }
        
        public DoctorRating getDoctorRating(int index) {
            return this.ratings.get(index);
        }
        
        public Doctor getDoctor() {
            return this.doctor;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0: return "ID";
                case 1: return "Patient";
                case 2: return "Rating";
            }
            return null;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            DoctorRating dr = this.ratings.get(rowIndex);
            Patient p = controller.getPatient(dr.getPatientUUID());
            switch (columnIndex) {
                case 0: return dr.getUUID();
                case 1: return p.getGivenName() + " " + p.getLastName();
                case 2: return String.format("%.2f / 5", dr.getRating());
            }
            return null;
        }
    }
    
    class PatientCreationRequestTableModel extends AbstractTableModel {
        private final List<PatientCreationRequest> requests;
        PatientCreationRequestTableModel() {
            this.requests = controller.getPatientCreationRequestList();
        }
        
        @Override
        public int getRowCount() {
            return this.requests.size();
        }

        @Override
        public int getColumnCount() {
            return 6;
        }
        
        public PatientCreationRequest getRequest(int index) {
            return this.requests.get(index);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0: return "ID";
                case 1: return "Given Name";
                case 2: return "Last Name";
                case 3: return "Address";
                case 4: return "Age";
                case 5: return "Gender";
            }
            return null;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            PatientCreationRequest request = this.requests.get(rowIndex);
            switch (columnIndex) {
                case 0: return request.getUUID();
                case 1: return request.getGivenName();
                case 2: return request.getLastName();
                case 3: return request.getAddress();
                case 4: return request.getAge();
                case 5: return request.getGender();
            }
            return null;
        }
    }
    
    class PatientRemovalRequestTableModel extends AbstractTableModel {
        private final List<AccountTerminationRequest> requests;
        PatientRemovalRequestTableModel() {
            this.requests = controller.getAccountTerminationRequests();
        }
        
        @Override
        public int getRowCount() {
            return this.requests.size();
        }

        @Override
        public int getColumnCount() {
            return 6;
        }
        
        public AccountTerminationRequest getRequest(int index) {
            if (index < 0) {
                return null;
            }
            return this.requests.get(index);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0: return "ID";
                case 1: return "Given Name";
                case 2: return "Last Name";
                case 3: return "Address";
                case 4: return "Age";
                case 5: return "Gender";
            }
            return null;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            AccountTerminationRequest request = this.requests.get(rowIndex);
            Patient p = controller.getPatient(request.getPatientUUID());
            switch (columnIndex) {
                case 0: return p.getUUID();
                case 1: return p.getGivenName();
                case 2: return p.getLastName();
                case 3: return p.getAddress();
                case 4: return p.getAge();
                case 5: return p.getGender();
            }
            return null;
        }
    }
    
    class PatientTableModel extends AbstractTableModel {
        private final List<Patient> patients;
        PatientTableModel() {
            this.patients = controller.getPatientList();
        }
        
        @Override
        public int getRowCount() {
            return this.patients.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }
        
        public Patient getPatient(int index) {
            return this.patients.get(index);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0: return "ID";
                case 1: return "Given Name";
                case 2: return "Last Name";
                case 3: return "Address";
                case 4: return "Age";
            }
            return null;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Patient p = this.patients.get(rowIndex);
            switch (columnIndex) {
                case 0: return p.getUUID();
                case 1: return p.getGivenName();
                case 2: return p.getLastName();
                case 3: return p.getAddress();
                case 4: return p.getAge();
            }
            return null;
        }
    }
    
    class AppointmentRequestTableModel extends AbstractTableModel {
        private final List<AppointmentRequest> requests;
        AppointmentRequestTableModel() {
            this.requests = controller.getOpenAppointmentRequests();
        }
        
        @Override
        public int getRowCount() {
            return this.requests.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
        }
        
        public AppointmentRequest getRequest(int index) {
            return this.requests.get(index);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0: return "Doctor Name";
                case 1: return "Patient Name";
                case 2: return "Appointment Date";
            }
            return null;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            AppointmentRequest ar = this.requests.get(rowIndex);
            Doctor dr = controller.getDoctor(ar.getDoctorUUID());
            Patient p = controller.getPatient(ar.getPatientUUID());
            String doctorName = String.format("Dr. %s %s", dr.getGivenName(), dr.getLastName());
            String patientName = String.format("%s %s", p.getGivenName(), p.getLastName());
            switch (columnIndex) {
                case 0: return doctorName;
                case 1: return patientName;
                case 2: return Appointment.sdf.format(ar.getDate());
            }
            return null;
        }
    }
    
    class DoctorAppointmentTableModel extends AbstractTableModel {
        private final List<Appointment> appointments;
        DoctorAppointmentTableModel() {
            this.appointments = controller.getDoctorAppointmentList(doctor.getUUID());
        }
        
        @Override
        public int getRowCount() {
            return this.appointments.size();
        }

        @Override
        public int getColumnCount() {
            return 4;
        }
        
        public Appointment getAppointment(int index) {
            return this.appointments.get(index);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0: return "ID";
                case 1: return "Date";
                case 2: return "Patient Name";
                case 3: return "Patient Age";
            }
            return null;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Appointment a = this.appointments.get(rowIndex);
            Patient p = controller.getPatient(a.getPatientUUID());
            switch (columnIndex) {
                case 0: return a.getUUID();
                case 1: return Appointment.sdf.format(a.getDate());
                case 2: return p.getGivenName() + " " + p.getLastName();
                case 3: return String.valueOf(p.getAge());
            }
            return null;
        }
    }
    
    class MedicineOrderTableModel extends AbstractTableModel {
        private final List<MedicineOrder> requests;
        MedicineOrderTableModel() {
            this.requests = controller.getMedicineOrders();
        }
        
        @Override
        public int getRowCount() {
            return this.requests.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }
        
        public MedicineOrder getOrder(int index) {
            return this.requests.get(index);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0: return "ID";
                case 1: return "Doctor Name";
                case 2: return "Medicine Name";
                case 3: return "Medicine Description";
                case 4: return "Medicine Quantity";
            }
            return null;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            MedicineOrder mo = this.requests.get(rowIndex);
            Doctor d = controller.getDoctor(mo.getDoctorUUID());
            switch (columnIndex) {
                case 0: return mo.getUUID();
                case 1: return String.format("Dr. %s %s", d.getGivenName(), d.getLastName());
                case 2: return mo.getMedicineName();
                case 3: return mo.getMedicineDescription();
                case 4: return String.valueOf(mo.getQuantity());
            }
            return null;
        }
    }
    
    class PrescriptionMedicineTableModel extends AbstractTableModel {
        private final List<Medicine> medicines;
        PrescriptionMedicineTableModel() {
            this.medicines = controller.getMedicines();
        }
        
        @Override
        public int getRowCount() {
            return this.medicines.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
        }
        
        public Medicine getMedicine(int index) {
            return this.medicines.get(index);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0: return "ID";
                case 1: return "Medicine Name";
                case 2: return "Medicine Description";
            }
            return null;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Medicine m = this.medicines.get(rowIndex);
            switch (columnIndex) {
                case 0: return m.getUUID();
                case 1: return m.getName();
                case 2: return m.getDescription();
            }
            return null;
        }
    }
    
    class PrescriptionCollectionTableModel extends AbstractTableModel {
        private final List<PrescriptionMedicine> prescriptions;
        PrescriptionCollectionTableModel() {
            this.prescriptions = controller.getPrescriptions();
        }
        
        @Override
        public int getRowCount() {
            return this.prescriptions.size();
        }

        @Override
        public int getColumnCount() {
            return 6;
        }
        
        public PrescriptionMedicine getPrescriptionMedicine(int index) {
            return this.prescriptions.get(index);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0: return "ID";
                case 1: return "Medicine Name";
                case 2: return "Medicine Description";
                case 3: return "Quantity";
                case 4: return "Receiving Patient";
                case 5: return "Prescribing Doctor";
            }
            return null;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            PrescriptionMedicine pm = this.prescriptions.get(rowIndex);
            Appointment a = controller.getAppointment(pm.getAppointmentUUID());
            Medicine m = controller.getMedicine(pm.getMedicineUUID());
            Patient p  = controller.getPatient(a.getPatientUUID());
            Doctor d = controller.getDoctor(a.getDoctorUUID());
            switch (columnIndex) {
                case 0: return pm.getUUID();
                case 1: return m.getName();
                case 2: return m.getDescription();
                case 3: return String.valueOf(pm.getMedicineQuantity());
                case 4: return String.format("%s %s", p.getGivenName(), p.getLastName());
                case 5: return String.format("Dr. %s %s", d.getGivenName(), d.getLastName());
            }
            return null;
        }
    }
}
