package layout;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import converters.UserLayoutHandler;
import dtos.requests.UserCredentialsDTO;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class LoginPage extends JFrame {

    private JButton loginButton;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPanel jPanel1;
    private JPasswordField jPasswordField1;
    private java.awt.TextField textField1;

    private UserLayoutHandler userLayoutHandler;

    private String loginButtonJson;

    public LoginPage() {

        initUI();
        setObjectMapper();
        userLayoutHandler = new UserLayoutHandler();
        loginButtonJson = "";
    }

    public void setObjectMapper(){
        Unirest.setObjectMapper(new ObjectMapper() {
            com.fasterxml.jackson.databind.ObjectMapper mapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public String writeValue(Object value) {
                try {
                    return mapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return mapper.readValue(value, valueType);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }
    public void run(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }

    private void initUI() {

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        textField1 = new java.awt.TextField();
        jPasswordField1 = new JPasswordField();
        loginButton = new JButton();


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(800, 600));

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 24)); // NOI18N
        jLabel1.setText("MEDICAL RECORD SYSTEM LOGIN PAGE");

        jLabel2.setText("Username:");

        jLabel3.setText("Password:");

        loginButton.setText("Login");
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                try {
                    loginButtonMouseClicked(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap(228, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
                                                .addGap(162, 162, 162))
                                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(loginButton)
                                                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addComponent(jLabel3)
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jPasswordField1, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
                                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                        .addComponent(jLabel2)
                                                                        .addGap(32, 32, 32)
                                                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))))
                                                .addGap(233, 233, 233))))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(166, 166, 166)
                                .addComponent(jLabel1)
                                .addGap(60, 60, 60)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(jLabel2))
                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(jPasswordField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(loginButton)
                                .addContainerGap(310, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }

    private JSONObject getUserDetails(){
        JSONObject userDetails = new JSONObject();
        userDetails.put("username",textField1.getText());
        userDetails.put("password",String.valueOf(jPasswordField1.getPassword()));
        return userDetails;
    }

    public void loginButtonMouseClicked(MouseEvent evt) throws IOException {

        UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO();
        userCredentialsDTO.setUsername(textField1.getText());
        userCredentialsDTO.setPassword(String.valueOf(jPasswordField1.getPassword()));

        int userId = userLayoutHandler.getUserId(userCredentialsDTO);
        int roleId = userLayoutHandler.selectUserLayoutByRole(userCredentialsDTO);

        String authorizedInfo = userLayoutHandler.getAuthorizedStatus(userId);
        boolean isAuthorized = false;

        if(authorizedInfo.equals("true"))
            isAuthorized = true;
        else if(authorizedInfo.equals("false"))
            isAuthorized = false;


        if(roleId == 1 && isAuthorized){
            new AdminMenu().setVisible(true);
            super.dispose();
        }
        else if(roleId == 2 && isAuthorized){
            new DoctorMenu(userId).setVisible(true);
            super.dispose();
        }
        else if(roleId == 3 && isAuthorized){
            new NurseMenu().setVisible(true);
            super.dispose();
        }
        else if(roleId == 4 && isAuthorized){
            new PatientMenu(userId).setVisible(true);
            super.dispose();
        }
        else if(roleId == 5 && isAuthorized){
            new RelativeMenu().setVisible(true);
            super.dispose();
        }
        else{
            JOptionPane.showMessageDialog(this, "User is not authorized. / Wrong credentials.");
        }
    }

    public void checkAuthentication(){

    }





}