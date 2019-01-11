package layout;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import converters.DoctorMenuHandler;
import dtos.requests.PatientRegisterDTO;
import dtos.responses.PatientCredentialsDTO;
import models.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoctorMenu extends JFrame {

    private JButton addPatientButton;
    private JButton authorizePatientButton;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JTable patientListTable_doctorMenu;
    private JButton seeNursesButton;
    private JButton showInboxButton;

    ///
    private DoctorMenuHandler doctorMenuHandler;
    private int userId;
    private String s;


    public DoctorMenu(int userId) {
        doctorMenuHandler = new DoctorMenuHandler();
        this.userId = userId;
        initComponents();
    }


    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        patientListTable_doctorMenu = new JTable();
        addPatientButton = new JButton();
        seeNursesButton = new JButton();
        showInboxButton = new JButton();
        authorizePatientButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 700));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));
        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel1.setText("Patient List");


        setTableModel();
        patientListTable_doctorMenu.setAutoCreateRowSorter(true);

        patientListTable_doctorMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patientListTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(patientListTable_doctorMenu);
        if (patientListTable_doctorMenu.getColumnModel().getColumnCount() > 0) {
            patientListTable_doctorMenu.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        addPatientButton.setText("Add Patient");
        addPatientButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addPatientButtonMouseClicked(evt);
            }
        });

        seeNursesButton.setText("See Nurses");
        seeNursesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeNursesButtonClicked(evt);
            }
        });

        showInboxButton.setText("Show Inbox");
        showInboxButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showInboxMouseClicked(evt);
            }
        });
        showInboxButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showInboxButtonActionPerformed(evt);
            }
        });

        authorizePatientButton.setText("Authorize Patient");
        authorizePatientButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                authorizePatientButtonMouseClicked(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 571, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(addPatientButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(seeNursesButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(showInboxButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(authorizePatientButton, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)))
                                        .addComponent(jLabel1))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 534, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(addPatientButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(seeNursesButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(showInboxButton)
                                                .addGap(18, 18, 18)
                                                .addComponent(authorizePatientButton)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }

    public void setTableModel() {

        doctorMenuHandler.setDoctorId(getUserId());
        doctorMenuHandler.setResponseBodyArray();
        List<Patient> patientList = doctorMenuHandler.getListOfPatients();
        Object[][] patientsRepAsObjArr = new Object[patientList.size()][];
        for (int i = 0; i < patientList.size(); i++) {
            patientsRepAsObjArr[i] = new Object[]{
                    patientList.get(i).getId(),
                    patientList.get(i).getFirstName(),
                    patientList.get(i).getLastName(),
                    patientList.get(i).getBirthDate()
            };
        }

        patientListTable_doctorMenu.setModel(new javax.swing.table.DefaultTableModel(
                patientsRepAsObjArr,
                new String[]{
                        "ID", "First Name", "Last Name", "Birth Date"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    private void showInboxButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void patientListTableMouseClicked(java.awt.event.MouseEvent evt) {
        int row = patientListTable_doctorMenu.rowAtPoint(evt.getPoint());
        int column = patientListTable_doctorMenu.columnAtPoint(evt.getPoint());
        s = patientListTable_doctorMenu.getModel().getValueAt(row, column) + "";

        System.out.println(s);
        if (evt.getClickCount() == 2) {
            System.out.println(doctorMenuHandler.getDoctorId()+" DOCTOR ID");
            PatientDetailsMenu patientDetailsMenu = new PatientDetailsMenu(new Integer(s), doctorMenuHandler.getDoctorId());
            patientDetailsMenu.setVisible(true);
        }
    }

    private void addPatientButtonMouseClicked(java.awt.event.MouseEvent evt) {

        String firstName = "";
        String lastName = "";
        String birthDate = "";

        String regexStr = "^[A-Za-z ]++$";
        Pattern pattern = Pattern.compile(regexStr);

        String regexDate = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
        Pattern pattern1 = Pattern.compile(regexDate);

        //TODO HOW TO HANDLE CANCEL BUTTON..

        firstName = String.valueOf(
                JOptionPane.showInputDialog(this, "Enter first name of the patient:"));
        Matcher matcher = pattern.matcher(firstName);
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid input.");
            firstName = null;
        } else {
            lastName = String.valueOf(JOptionPane.showInputDialog(this, "Enter last name of the patient:"));
            Matcher matcher1 = pattern.matcher(lastName);
            if (matcher1.matches()) {
                birthDate = String.valueOf(JOptionPane.showInputDialog(this, "Enter the birth date of the patient in format (Day/Month/Year):"));
                Matcher matcher2 = pattern1.matcher(birthDate);
                if (matcher2.matches()) {
                    //System.out.println(birthDate);
                } else if (!matcher2.matches()) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid date.");
                    firstName = null;
                    lastName = null;
                    birthDate = null;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a string input.");
                firstName = null;
                lastName = null;
                birthDate = null;
            }
        }

        PatientRegisterDTO patientRegisterDTO = new PatientRegisterDTO();
        if (!firstName.equals(null) && !lastName.equals(null) && !birthDate.equals(null)) {
            patientRegisterDTO.setDoctorId(getUserId());
            patientRegisterDTO.setFirstName(firstName);
            patientRegisterDTO.setLastName(lastName);
            patientRegisterDTO.setBirthDate(birthDate);
        }

        PatientCredentialsDTO patientCredentialsDTO = doctorMenuHandler.addPatientAndGetCredentials(patientRegisterDTO);

        String userCredentials = "Username: "+patientCredentialsDTO.getUsername()+"\n Password: "+patientCredentialsDTO.getPassword();
        JOptionPane.showMessageDialog(this,userCredentials);
        System.out.println("Password: "+patientCredentialsDTO.getPassword());

        ((DefaultTableModel)patientListTable_doctorMenu.getModel()).addRow(new Object[]{
                patientCredentialsDTO.getId(),firstName.substring(0,1).toUpperCase()+firstName.substring(1) ,lastName.substring(0,1).toUpperCase()+lastName.substring(1),birthDate
        });
    }

    private void showInboxMouseClicked(java.awt.event.MouseEvent evt) {
        new DoctorInboxMenu().setVisible(true);
    }

    private void seeNursesButtonClicked(java.awt.event.MouseEvent evt) {
        new NursesList().setVisible(true);
    }

    private void authorizePatientButtonMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        int patientId = Integer.parseInt(s);
        String message = doctorMenuHandler.setPatientAuthorized(patientId);
        JOptionPane.showMessageDialog(this,message);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
