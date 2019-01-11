package layout;

import converters.PatientDetailsAddingHandler;
import converters.PatientDetailsHandler;
import dtos.responses.PatientDetailsDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PatientDetailsMenu extends JFrame {

    private JButton addMedicalRecordButton;
    private JButton transmitMedicalRecordButton;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JScrollPane jScrollPane3;
    private JTable patientDetailsTable;
    private JTextArea jTextArea1;
    private JTextArea jTextArea2;

    private PatientDetailsHandler patientDetailsHandler;
    private PatientDetailsAddingHandler patientDetailsAddingHandler;
    private int userId;
    private int doctorId;
    /**
     * Creates new form PatientDetailsMenu
     */

    public PatientDetailsMenu(int userId, int doctorId) {
        patientDetailsHandler = new PatientDetailsHandler();
        patientDetailsAddingHandler = new PatientDetailsAddingHandler();
        this.setUserId(userId);
        this.setDoctorId(doctorId);
        initComponents();
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new JPanel();
        jScrollPane1 = new JScrollPane();
        patientDetailsTable = new JTable();
        jLabel1 = new JLabel();
        addMedicalRecordButton = new JButton();
        transmitMedicalRecordButton = new JButton();
        jScrollPane2 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jScrollPane3 = new JScrollPane();
        jTextArea2 = new JTextArea();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 700));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        int id = getUserId();
        setTableModel(id);
        patientDetailsTable.setPreferredSize(new java.awt.Dimension(300, 528));
        jScrollPane1.setViewportView(patientDetailsTable);

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel1.setText("Patient Name Details");

        patientDetailsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patientListTableMouseClicked(evt);
            }
        });

        addMedicalRecordButton.setText("Add Medical Record");
        addMedicalRecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMedicalRecordButtonActionPerformed(evt);
            }
        });


        transmitMedicalRecordButton.setText("Transmit Medical Record");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setEnabled(false);
        jTextArea1.setFont(new Font("Serif", Font.BOLD, 15));
        jTextArea1.setForeground(Color.RED);

        jScrollPane2.setViewportView(jTextArea1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setEnabled(false);
        jTextArea2.setFont(new Font("Serif", Font.BOLD, 15));
        jTextArea2.setForeground(Color.RED);

        jScrollPane3.setViewportView(jTextArea2);

        jLabel2.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel2.setText("Diagnostics Details:");

        jLabel3.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        jLabel3.setText("Prescription Details:");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 497, GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(jLabel3)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jScrollPane3, GroupLayout.Alignment.LEADING)
                                                                        .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                                                .addComponent(jLabel2)
                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                        .addComponent(addMedicalRecordButton, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jScrollPane2)
                                                                        .addComponent(transmitMedicalRecordButton, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                                                                .addContainerGap())))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(addMedicalRecordButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(transmitMedicalRecordButton)
                                                .addGap(12, 12, 12)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane2)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                                                .addGap(13, 13, 13)))
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

    public void setUserId(Integer userId){
        this.userId = userId;
    }
    public Integer getUserId(){
        return userId;
    }


    public void setTableModel(Integer userId){
        System.out.println(userId);

        patientDetailsHandler.getPatientDetails(userId);
        patientDetailsHandler.setResponseBodyArray();
        List<PatientDetailsDTO> patientDetailsDTOS = patientDetailsHandler.getListOfPatientDetails();
        Object[][] patientDetailsArr = new Object[patientDetailsDTOS.size()][];
        for (int i = 0; i < patientDetailsDTOS.size(); i++) {
            patientDetailsArr[i] = new Object[]{
                    patientDetailsDTOS.get(i).getDiagnostics(),
                    patientDetailsDTOS.get(i).getPrescription()
            };
        }

        patientDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
                patientDetailsArr,
                new String[]{
                        "Diagnostics", "Prescription"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                    false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });


    }
    private void addMedicalRecordButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String diagnostics = "";
        String prescription = "";

        diagnostics = String.valueOf(
                JOptionPane.showInputDialog(this, "Enter the diagnostics of patient:"));

        prescription = String.valueOf(
                JOptionPane.showInputDialog(this, "Enter the prescription for the diagnostics:"));

        PatientDetailsDTO patientDetailsDTO = new PatientDetailsDTO();
        patientDetailsDTO.setUserId(getUserId());
        patientDetailsDTO.setPrescription(prescription);
        patientDetailsDTO.setDiagnostics(diagnostics);
        patientDetailsDTO.setDoctorId(getDoctorId());
        patientDetailsAddingHandler.addDetailsToTable(patientDetailsDTO);
        ((DefaultTableModel)patientDetailsTable.getModel()).addRow(new Object[]{
                patientDetailsDTO.getDiagnostics(),patientDetailsDTO.getPrescription()
        });

    }

    private void resetSelectedFieldButtonActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void patientListTableMouseClicked(java.awt.event.MouseEvent evt) {
        int row = patientDetailsTable.rowAtPoint(evt.getPoint());
        int column = patientDetailsTable.columnAtPoint(evt.getPoint());

        String columnName = patientDetailsTable.getColumnName(column);

        String s = patientDetailsTable.getModel().getValueAt(row, column) + "";

        if(columnName.toLowerCase().equals("diagnostics"))
            jTextArea1.setText(s);
        else if(columnName.toLowerCase().equals("prescription"))
            jTextArea2.setText(s);

        System.out.println(s);

    }
}