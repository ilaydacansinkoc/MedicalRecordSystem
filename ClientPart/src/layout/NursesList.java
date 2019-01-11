package layout;


import converters.NurseListHandler;
import models.Nurse;
import models.Patient;

import javax.swing.*;
import java.util.List;

public class NursesList extends JFrame {

    private JButton authorizeNurseButton;
    private JLabel jLabel2;
    private JPanel jPanel2;
    private JScrollPane jScrollPane2;
    private JTable nurseListTable;
    private String s;

    private NurseListHandler nurseListHandler;

    public NursesList() {
        s = "";
        nurseListHandler = new NurseListHandler();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel2 = new JPanel();
        jLabel2 = new JLabel();
        jScrollPane2 = new JScrollPane();
        nurseListTable = new JTable();
        authorizeNurseButton = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel2.setText("Doctor Name's Nurse List");

        setTableModel();
        nurseListTable.setAutoCreateRowSorter(true);

        jScrollPane2.setViewportView(nurseListTable);
        if (nurseListTable.getColumnModel().getColumnCount() > 0) {
            nurseListTable.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        nurseListTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nurseListTableMouseClicked(evt);
            }
        });

        authorizeNurseButton.setText("Authorize Nurse");
        authorizeNurseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                authorizeNurseButtonActionPerformed(evt);
            }
        });


        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 580, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(authorizeNurseButton, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(authorizeNurseButton)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }

    public void setTableModel() {

        nurseListHandler.setResponseBodyArray();
        List<Nurse> nurseList = nurseListHandler.getListOfNurses();
        Object[][] nursesRepAsObjArr = new Object[nurseList.size()][];
        for (int i = 0; i < nurseList.size(); i++) {
            nursesRepAsObjArr[i] = new Object[]{
                    nurseList.get(i).getId(),
                    nurseList.get(i).getFirstName(),
                    nurseList.get(i).getLastName(),
                    nurseList.get(i).getBirthDate()
            };
        }

        nurseListTable.setModel(new javax.swing.table.DefaultTableModel(
                nursesRepAsObjArr,
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

    private void authorizeNurseButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int nurseId = Integer.parseInt(s);
        String message = nurseListHandler.setNurseAuthorized(nurseId);
        JOptionPane.showMessageDialog(this,message);
    }

    private void nurseListTableMouseClicked(java.awt.event.MouseEvent evt) {
        int row = nurseListTable.rowAtPoint(evt.getPoint());
        int column = nurseListTable.columnAtPoint(evt.getPoint());
        s = nurseListTable.getModel().getValueAt(row, column) + "";
        System.out.println(s);
    }


}
