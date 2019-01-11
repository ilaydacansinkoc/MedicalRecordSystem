package layout;


import converters.PatientDetailsHandler;
import dtos.responses.PatientDetailsDTO;
import models.Patient;

import javax.swing.*;
import java.util.List;

public class PatientMenu extends JFrame {

    private JLabel jLabel1;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    private JButton seeRelativesButton;

    private int userId;
    private PatientDetailsHandler patientDetailsHandler;

    public PatientMenu(int userId) {
        this.setUserId(userId);
        patientDetailsHandler = new PatientDetailsHandler();
        initComponents();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();
        jLabel1 = new JLabel();
        seeRelativesButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        setTableModel();
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel1.setText("Patient Name Medical Records");

        seeRelativesButton.setText("See Relatives");
        seeRelativesButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seeRelativesButtonMouseClicked(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 592, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(seeRelativesButton, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 526, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(seeRelativesButton))
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
    }

    private void seeRelativesButtonMouseClicked(java.awt.event.MouseEvent evt) {
        new RelativeList(getUserId()).setVisible(true);
    }

    public void setTableModel(){
        patientDetailsHandler.getPatientDetails(getUserId());
        patientDetailsHandler.setResponseBodyArray();
        List<PatientDetailsDTO> patientDetailsDTOS = patientDetailsHandler.getListOfPatientDetails();
        Object[][] patientsRepAsObjArr = new Object[patientDetailsDTOS.size()][];
        for (int i = 0; i < patientDetailsDTOS.size(); i++) {
            patientsRepAsObjArr[i] = new Object[]{
                    patientDetailsDTOS.get(i).getDoctorId(),
                    patientDetailsDTOS.get(i).getDiagnostics(),
                    patientDetailsDTOS.get(i).getPrescription()
            };
        }

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                patientsRepAsObjArr,
                new String[]{
                        "Doctor ID","Diagnostics", "Prescription"
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

}
