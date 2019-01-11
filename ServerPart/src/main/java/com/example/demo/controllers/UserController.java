package com.example.demo.controllers;

import com.example.demo.dtos.requests.UserCredentialsDTO;
import com.example.demo.repository.UserRepository;
import com.google.common.hash.Hashing;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.sql.*;

@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class UserController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    UserRepository userRepository;

    @PostMapping("")
    public int getUserRoleId(@RequestBody UserCredentialsDTO userCredentialsDTO){

        Integer roleId = null;
        String username = userCredentialsDTO.getUsername();
        String password = userCredentialsDTO.getPassword();

        String hashedPassword = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,hashedPassword);


        if (!currentUser.isAuthenticated()) {
            token.setRememberMe(true);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                System.out.println("There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                System.out.println("Password for account " + token.getPrincipal() + " was incorrect!");
            } catch (LockedAccountException lae) {
                System.out.println("The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
            }
        }

        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            con = jdbcTemplate.getDataSource().getConnection();

            String getRoleIdQueryString = "select role_id from users where username = ? and password = ?";
            preparedStatement = con.prepareStatement(getRoleIdQueryString);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hashedPassword);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                roleId = Integer.parseInt(resultSet.getString("role_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null && !resultSet.isClosed())
                    resultSet.close();
                if (preparedStatement != null && !preparedStatement.isClosed())
                    preparedStatement.close();
                if (con != null && !con.isClosed())
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return roleId;
    }

    @PostMapping("/userId")
    public Integer getUserId(@RequestBody UserCredentialsDTO userCredentialsDTO){

        Integer userId = null;
        String username = userCredentialsDTO.getUsername();
        String password = userCredentialsDTO.getPassword();

        String hashedPassword = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();


        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            con = jdbcTemplate.getDataSource().getConnection();
            String getRoleIdQueryString = "select user_id from users where username = ? and password = ?";
            preparedStatement = con.prepareStatement(getRoleIdQueryString);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,hashedPassword);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
                userId = Integer.parseInt(resultSet.getString("user_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(resultSet!=null && !resultSet.isClosed())
                    resultSet.close();
                if(preparedStatement!= null && !preparedStatement.isClosed())
                    preparedStatement.close();
                if(con!=null && !con.isClosed())
                    con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return userId;
    }

    @PostMapping("/authorize")
    public String authorizePatient(@RequestBody Integer patientId){
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String info = "";
        try {
            con = jdbcTemplate.getDataSource().getConnection();

            String getUserQueryString = "update users set is_authorized = ? where user_id = ?";
            preparedStatement = con.prepareStatement(getUserQueryString);
            preparedStatement.setString(1,"true");
            preparedStatement.setInt(2,patientId);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            info = "Patient with user id = "+patientId+ " is successfully authorized.";

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(resultSet!=null && !resultSet.isClosed())
                    resultSet.close();
                if(preparedStatement!= null && !preparedStatement.isClosed())
                    preparedStatement.close();
                if(con!=null && !con.isClosed())
                    con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return info;
    }

    @PostMapping("/authorized")
    public String getAuthorizedInfo(@RequestBody Integer patientId){
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String info = "";
        try {
            con = jdbcTemplate.getDataSource().getConnection();

            String getUserQueryString = "select is_authorized from users where user_id = ?";
            preparedStatement = con.prepareStatement(getUserQueryString);
            preparedStatement.setInt(1,patientId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            info = resultSet.getString("is_authorized");
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(resultSet!=null && !resultSet.isClosed())
                    resultSet.close();
                if(preparedStatement!= null && !preparedStatement.isClosed())
                    preparedStatement.close();
                if(con!=null && !con.isClosed())
                    con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return info;
    }

}
