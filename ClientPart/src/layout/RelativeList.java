package layout;


import converters.PatientDetailsHandler;
import converters.RelativeAddingHandler;
import converters.RelativeListHandler;
import dtos.requests.PatientRegisterDTO;
import dtos.requests.RelativeDetailsDTO;
import dtos.requests.RelativeRegisterDTO;
import dtos.responses.PatientCredentialsDTO;
import dtos.responses.RelativeCredentialsDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class RelativeList extends JFrame {

    private JButton addRelativeButton;
    private JButton authorizeRelativeButton;
    private JLabel relativeListJLabel1;
    private JPanel relativeListPanel;
    private JScrollPane relativeListScrollPane;
    private JTable relativeListTable;

    private RelativeAddingHandler relativeAddingHandler;
    private RelativeListHandler relativeListHandler;
    private int userId;
    private String s;

    public RelativeList(int userId) {
        s= "";
        relativeAddingHandler = new RelativeAddingHandler();
        relativeListHandler = new RelativeListHandler();
        this.setUserId(userId);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        relativeListPanel = new JPanel();
        relativeListScrollPane = new JScrollPane();
        relativeListTable = new JTable();
        addRelativeButton = new JButton();
        relativeListJLabel1 = new JLabel();
        authorizeRelativeButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        relativeListPanel.setBackground(new java.awt.Color(204, 204, 255));

        this.setTableModel();
        relativeListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                relativeListMouseClicked(evt);
            }
        });
        relativeListScrollPane.setViewportView(relativeListTable);
        if (relativeListTable.getColumnModel().getColumnCount() > 0) {
            relativeListTable.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        addRelativeButton.setText("Add Relative");
        addRelativeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addRelativeMouseClicked(evt);
            }
        });

        relativeListJLabel1.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        relativeListJLabel1.setText("Patient Name's Relative List");

        authorizeRelativeButton.setText("Authorize Relative");
        authorizeRelativeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                authorizeRelativeMouseClicked(evt);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(relativeListPanel);
        relativeListPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(relativeListScrollPane, GroupLayout.PREFERRED_SIZE, 678, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(addRelativeButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(authorizeRelativeButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(relativeListJLabel1)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(relativeListJLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(relativeListScrollPane, GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(addRelativeButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(authorizeRelativeButton)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(relativeListPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(relativeListPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    private void addRelativeMouseClicked(java.awt.event.MouseEvent evt) {
        String relativeFirstName = String.valueOf(
                JOptionPane.showInputDialog(this, "Enter first name of your relative:"));

        String relativeLastName = String.valueOf(
                JOptionPane.showInputDialog(this, "Enter last name of your relative:"));

        RelativeDetailsDTO relativeDetailsDTO = new RelativeDetailsDTO();
        relativeDetailsDTO.setPatientId(getUserId());
        relativeDetailsDTO.setRelativeFirstName(relativeFirstName);
        relativeDetailsDTO.setRelativeLastName(relativeLastName);

        System.out.println(relativeDetailsDTO.getRelativeFirstName()+" fn -- "+relativeDetailsDTO.getPatientId()+" pid");
        RelativeCredentialsDTO relativeCredentialsDTO = relativeAddingHandler.addRelativeAndGetCredentials(relativeDetailsDTO);

        String userCredentials = "Username: "+relativeCredentialsDTO.getUsername()+"\n Password: "+relativeCredentialsDTO.getPassword();
        JOptionPane.showMessageDialog(this,userCredentials);
        System.out.println("Passsword: "+relativeCredentialsDTO.getPassword());

        ((DefaultTableModel)relativeListTable.getModel()).addRow(new Object[]{
                relativeCredentialsDTO.getId(),
                relativeFirstName.substring(0,1).toUpperCase()+relativeFirstName.substring(1) ,
                relativeLastName.substring(0,1).toUpperCase()+relativeLastName.substring(1),
        });

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private void authorizeRelativeMouseClicked(java.awt.event.MouseEvent evt) {
        String message = relativeListHandler.setRelativeAuthorized(Integer.parseInt(s));
        JOptionPane.showMessageDialog(this,message);
    }

    private void setTableModel(){
        relativeListHandler.getRelativeDetails(getUserId());
        relativeListHandler.setResponseBodyArray();
        List<RelativeDetailsDTO> relativeDetailsDTOS = relativeListHandler.getListOfRelativeDetails();

        Object[][] relativeRepAsObjArr = new Object[relativeDetailsDTOS.size()][];
        for (int i = 0; i < relativeDetailsDTOS.size(); i++) {
            relativeRepAsObjArr[i] = new Object[]{
                    relativeDetailsDTOS.get(i).getPatientId(),
                    relativeDetailsDTOS.get(i).getRelativeFirstName(),
                    relativeDetailsDTOS.get(i).getRelativeLastName()
            };
        }

        relativeListTable.setModel(new javax.swing.table.DefaultTableModel(
                relativeRepAsObjArr,
                new String[]{
                        "Relative ID", "Relative First Name", "Relative Last Name"
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

    private void relativeListMouseClicked(java.awt.event.MouseEvent evt) {
        int row = relativeListTable.rowAtPoint(evt.getPoint());
        int column = relativeListTable.columnAtPoint(evt.getPoint());
        s = relativeListTable.getModel().getValueAt(row, column) + "";
    }
}
