package layout;


import javax.swing.*;

public class RelativeMenu extends JFrame {

    private JLabel relativeMenuJLabel;
    private JPanel relativeMenuPanel;
    private JScrollPane relativeMenuScrollPane;
    private JTable relativeMenuTable;

    public RelativeMenu() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        relativeMenuPanel = new JPanel();
        relativeMenuScrollPane = new JScrollPane();
        relativeMenuTable = new JTable();
        relativeMenuJLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        relativeMenuPanel.setBackground(new java.awt.Color(204, 204, 255));

        relativeMenuTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null}
                },
                new String [] {
                        "Doctor ID", "Patient First Name", "Patient Last Name", "Patient Birth Date", "Patient Diagnostics", "Patient Prescription"
                }
        ));
        relativeMenuScrollPane.setViewportView(relativeMenuTable);
        if (relativeMenuTable.getColumnModel().getColumnCount() > 0) {
            relativeMenuTable.getColumnModel().getColumn(0).setMaxWidth(100);
        }

        relativeMenuJLabel.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        relativeMenuJLabel.setText(" Relative Name's Relative's Medical Record List");

        GroupLayout jPanel1Layout = new GroupLayout(relativeMenuPanel);
        relativeMenuPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(relativeMenuScrollPane, GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(relativeMenuJLabel)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(relativeMenuJLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(relativeMenuScrollPane, GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(relativeMenuPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(relativeMenuPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }
}
