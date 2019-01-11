package com.example.demo.controllers;

import com.example.demo.dtos.responses.PatientCredentialsDTO;
import com.example.demo.dtos.responses.RelativeCredentialsDTO;
import com.example.demo.dtos.responses.RelativeDetailsDTO;
import com.example.demo.helper.PasswordGenerator;
import com.example.demo.models.Patient;
import com.example.demo.repository.UserRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/relativedetails", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class RelativeController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserRepository userRepository;

    @PostMapping("")
    public List<RelativeDetailsDTO> getRelativeDetails(@RequestBody int patientId){
        List<RelativeDetailsDTO> relativeDetailsDTOS = new ArrayList<>();
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select relative_first_name, relative_last_name, patient_id from relatives where patient_id ="+patientId);
        for (Map<String, Object> row : list) {
            RelativeDetailsDTO relativeDetailsDTO = new RelativeDetailsDTO();
            relativeDetailsDTO.setPatientId((Integer) row.get("patient_id"));
            relativeDetailsDTO.setRelativeFirstName((String) row.get("relative_first_name"));
            relativeDetailsDTO.setRelativeLastName((String) row.get("relative_last_name"));
            relativeDetailsDTOS.add(relativeDetailsDTO);
        }

        return relativeDetailsDTOS;
    }

    @PostMapping("/getcredentials")
    public RelativeCredentialsDTO addRelative(@RequestBody RelativeDetailsDTO relativeDetailsDTO){
        System.out.println(relativeDetailsDTO.getRelativeFirstName()+"***-*-*-*-*-");
        String username = "";
        String patientPassword = generatePassword();

        username = relativeDetailsDTO.getRelativeFirstName().substring(0,1).toUpperCase() + relativeDetailsDTO.getRelativeLastName()
                +String.valueOf(userRepository.setUsername(relativeDetailsDTO.getRelativeFirstName(),relativeDetailsDTO.getRelativeLastName()));

        String hashedPassword = Hashing.sha256()
                .hashString(patientPassword, StandardCharsets.UTF_8)
                .toString();

        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = jdbcTemplate.getDataSource().getConnection();
            String insertPatientString = "insert into users(first_name,last_name,birth_date,username,password,is_authorized, role_id) values(?,?,?,?,?,?,?)";
            preparedStatement = con.prepareStatement(insertPatientString, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, relativeDetailsDTO.getRelativeFirstName().substring(0,1).toUpperCase()+relativeDetailsDTO.getRelativeFirstName().substring(1));
            preparedStatement.setString(2, relativeDetailsDTO.getRelativeLastName().substring(0,1).toUpperCase()+relativeDetailsDTO.getRelativeLastName().substring(1));
            preparedStatement.setString(3, "");
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, hashedPassword);
            preparedStatement.setString(6, "false");
            preparedStatement.setInt(7, 5);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            String insertPatientString1 = "insert into relatives(relative_first_name,relative_last_name,patient_id) values(?,?,?,?)";
            preparedStatement = con.prepareStatement(insertPatientString1, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, relativeDetailsDTO.getRelativeFirstName().substring(0,1).toUpperCase()+relativeDetailsDTO.getRelativeFirstName().substring(1));
            preparedStatement.setString(2, relativeDetailsDTO.getRelativeLastName().substring(0,1).toUpperCase()+relativeDetailsDTO.getRelativeLastName().substring(1));
            preparedStatement.setInt(4, relativeDetailsDTO.getPatientId());
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(preparedStatement!= null && !preparedStatement.isClosed())
                    preparedStatement.close();
                if(con!=null && !con.isClosed())
                    con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        RelativeCredentialsDTO relativeCredentialsDTO = new RelativeCredentialsDTO();
        relativeCredentialsDTO.setId(userRepository.getUserIdByUsername(username));
        relativeCredentialsDTO.setPassword(patientPassword);
        relativeCredentialsDTO.setUsername(username);

        return relativeCredentialsDTO;
    }

    public String generatePassword(){
        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(true)
                .useLower(true)
                .useUpper(true)
                .build();
        String password = passwordGenerator.generate(8);
        return password;
    }

    @PostMapping("/authorize")
    public String authorizeNurse(@RequestBody Integer relativeId){
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String info = "";
        try {
            con = jdbcTemplate.getDataSource().getConnection();

            String getUserQueryString = "update users set is_authorized = ? where user_id = ?";
            preparedStatement = con.prepareStatement(getUserQueryString);
            preparedStatement.setString(1,"true");
            preparedStatement.setInt(2,relativeId);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            info = "Nurse with user id = "+relativeId+ " is successfully authorized.";

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
