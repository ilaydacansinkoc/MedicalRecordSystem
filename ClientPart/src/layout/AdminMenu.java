package layout;

import converters.AdminMenuHandler;
import dtos.requests.DoctorRegisterDTO;
import dtos.requests.NurseRegisterDTO;
import dtos.requests.PatientRegisterDTO;
import dtos.responses.DoctorCredentialsDTO;
import dtos.responses.NurseCredentialsDTO;
import dtos.responses.PatientCredentialsDTO;
import models.Doctor;
import models.Nurse;
import models.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminMenu extends JFrame {

    private JButton addDoctorButton;
    private JButton addNurseButton;
    private JButton authorizeDoctorButton;
    private JButton authorizeNurseButton;
    private JTable doctorTable;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTable nurseTable;

    //
    private AdminMenuHandler adminMenuHandler;
    private int doctorId;
    private int nurseId;
    private String doctorSelected;
    private String nurseSelected;


    public AdminMenu() {
        doctorId = 0;
        nurseId = 0;
        doctorSelected = "";
        nurseSelected = "";
        adminMenuHandler  = new AdminMenuHandler();
        initComponents();
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        doctorTable = new JTable();
        jScrollPane2 = new JScrollPane();
        nurseTable = new JTable();
        addDoctorButton = new JButton();
        authorizeDoctorButton = new JButton();
        addNurseButton = new JButton();
        authorizeNurseButton = new JButton();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 36)); // NOI18N
        jLabel1.setText("Admin Panel");

        this.setDoctorTable();
        doctorTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                doctorListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(doctorTable);

        this.setNurseTable();
        nurseTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nursesListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(nurseTable);

        addDoctorButton.setText("Add Doctor");
        addDoctorButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addDoctorButtonClicked(evt);
            }
        });

        authorizeDoctorButton.setText("Authorize Doctor");
        authorizeDoctorButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                authorizedDoctorButtonClicked(evt);
            }
        });

        addNurseButton.setText("Add Nurse");
        addNurseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addNurseButtonClicked(evt);
            }
        });

        authorizeNurseButton.setText("Authorize Nurse");
        authorizeNurseButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                authorizeNurseButtonClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel2.setText("Doctor List");

        jLabel3.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel3.setText("Nurse List");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(291, 291, 291)
                                                                .addComponent(jLabel1))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(jLabel2)))
                                                .addGap(0, 291, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(addDoctorButton, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(authorizeDoctorButton, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(addNurseButton, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(authorizeNurseButton, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jScrollPane2))))
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(23, 23, 23)
                                .addComponent(jLabel2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(addDoctorButton)
                                        .addComponent(authorizeDoctorButton))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(authorizeNurseButton)
                                        .addComponent(addNurseButton))
                                .addContainerGap())
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
    }// </editor-fold>

    private void addDoctorButtonClicked(java.awt.event.MouseEvent evt) {
        String firstName = "";
        String lastName = "";
        String birthDate = "";

        String regexStr = "^[A-Za-z ]++$";
        Pattern pattern = Pattern.compile(regexStr);

        String regexDate = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
        Pattern pattern1 = Pattern.compile(regexDate);

        firstName = String.valueOf(
                JOptionPane.showInputDialog(this, "Enter first name of the doctor:"));
        Matcher matcher = pattern.matcher(firstName);
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid input.");
        } else {
            lastName = String.valueOf(JOptionPane.showInputDialog(this, "Enter last name of the doctor:"));
            Matcher matcher1 = pattern.matcher(lastName);
            if (matcher1.matches()) {
                birthDate = String.valueOf(JOptionPane.showInputDialog(this, "Enter the birth date of the doctor in format (Day/Month/Year):"));
                Matcher matcher2 = pattern1.matcher(birthDate);
                if (matcher2.matches()) {
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

        DoctorRegisterDTO doctorRegisterDTO = new DoctorRegisterDTO();
        if (!firstName.equals(null) && !lastName.equals(null)) {
            doctorRegisterDTO.setFirstName(firstName);
            doctorRegisterDTO.setLastName(lastName);
            doctorRegisterDTO.setBirthDate(birthDate);
        }

        DoctorCredentialsDTO doctorCredentialsDTO = adminMenuHandler.addDoctorAndGetCredentials(doctorRegisterDTO);

        String userCredentials = "Username: "+doctorCredentialsDTO.getUsername()+"\n Password: "+doctorCredentialsDTO.getPassword();
        JOptionPane.showMessageDialog(this,userCredentials);
        System.out.println("Password: "+doctorCredentialsDTO.getPassword());

        ((DefaultTableModel)doctorTable.getModel()).addRow(new Object[]{
                doctorCredentialsDTO.getId(),firstName.substring(0,1).toUpperCase()+firstName.substring(1) ,lastName.substring(0,1).toUpperCase()+lastName.substring(1)
        });

    }

    private void authorizedDoctorButtonClicked(java.awt.event.MouseEvent evt) {
        String message = adminMenuHandler.setDoctorAuthorized(Integer.parseInt(doctorSelected));
        JOptionPane.showMessageDialog(this,message);
    }

    private void addNurseButtonClicked(java.awt.event.MouseEvent evt) {
        String firstName = "";
        String lastName = "";
        String birthDate = "";

        String regexStr = "^[A-Za-z ]++$";
        Pattern pattern = Pattern.compile(regexStr);

        String regexDate = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
        Pattern pattern1 = Pattern.compile(regexDate);

        firstName = String.valueOf(
                JOptionPane.showInputDialog(this, "Enter first name of the nurse:"));
        Matcher matcher = pattern.matcher(firstName);
        if (!matcher.matches()) {
            JOptionPane.showMessageDialog(this, "Please enter a valid input.");
        } else {
            lastName = String.valueOf(JOptionPane.showInputDialog(this, "Enter last name of the nurse:"));
            Matcher matcher1 = pattern.matcher(lastName);
            if (matcher1.matches()) {
                birthDate = String.valueOf(JOptionPane.showInputDialog(this, "Enter the birth date of the nurse in format (Day/Month/Year):"));
                Matcher matcher2 = pattern1.matcher(birthDate);
                if (matcher2.matches()) {
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

        NurseRegisterDTO nurseRegisterDTO = new NurseRegisterDTO();
        if (!firstName.equals(null) && !lastName.equals(null)) {
            nurseRegisterDTO.setFirstName(firstName);
            nurseRegisterDTO.setLastName(lastName);
            nurseRegisterDTO.setBirthDate(birthDate);
        }

        NurseCredentialsDTO nurseCredentialsDTO = adminMenuHandler.addNurseAndGetCredentials(nurseRegisterDTO);

        String userCredentials = "Username: "+nurseCredentialsDTO.getUsername()+"\n Password: "+nurseCredentialsDTO.getPassword();
        JOptionPane.showMessageDialog(this,userCredentials);
        System.out.println("Password: "+nurseCredentialsDTO.getPassword());


        ((DefaultTableModel)nurseTable.getModel()).addRow(new Object[]{
                nurseCredentialsDTO.getId(),firstName.substring(0,1).toUpperCase()+firstName.substring(1) ,lastName.substring(0,1).toUpperCase()+lastName.substring(1)
        });
    }

    private void authorizeNurseButtonClicked(java.awt.event.MouseEvent evt) {
        String message = adminMenuHandler.setNurseAuthorized(Integer.parseInt(nurseSelected));
        JOptionPane.showMessageDialog(this,message);
    }

    private void setNurseTable(){
        adminMenuHandler.setResponseBodyArrayForNurses();
        List<Nurse> nurses = adminMenuHandler.getListOfNurses();
        Object[][] nursesRepAsObjArr = new Object[nurses.size()][];

        for (int i = 0; i < nurses.size(); i++) {
            nursesRepAsObjArr[i] = new Object[]{
                    nurses.get(i).getId(),
                    nurses.get(i).getFirstName(),
                    nurses.get(i).getLastName()
            };
        }

        nurseTable.setModel(new javax.swing.table.DefaultTableModel(
                nursesRepAsObjArr,
                new String[]{
                        "Nurse ID", "Nurse First Name", "Nurse Last Name"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    private void setDoctorTable(){
        adminMenuHandler.setResponseBodyArrayForDoctors();
        List<Doctor> doctors = adminMenuHandler.getListOfDoctors();
        Object[][] doctorsRepAsObjArr = new Object[doctors.size()][];
        for (int i = 0; i < doctors.size(); i++) {
            doctorsRepAsObjArr[i] = new Object[]{
                    doctors.get(i).getId(),
                    doctors.get(i).getFirstName(),
                    doctors.get(i).getLastName()
            };
        }

        doctorTable.setModel(new javax.swing.table.DefaultTableModel(
                doctorsRepAsObjArr,
                new String[]{
                        "Doctor ID", "Doctor First Name", "Doctor Last Name"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    private void doctorListMouseClicked(java.awt.event.MouseEvent evt) {
        int row = doctorTable.rowAtPoint(evt.getPoint());
        int column = doctorTable.columnAtPoint(evt.getPoint());
        doctorSelected = doctorTable.getModel().getValueAt(row, column) + "";
    }

    private void nursesListMouseClicked(java.awt.event.MouseEvent evt) {
        int row = nurseTable.rowAtPoint(evt.getPoint());
        int column = nurseTable.columnAtPoint(evt.getPoint());
        nurseSelected = nurseTable.getModel().getValueAt(row, column) + "";
    }

}
