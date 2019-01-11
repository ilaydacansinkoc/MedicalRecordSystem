package com.example.demo.controllers;

import com.example.demo.dtos.requests.DoctorRegisterDTO;
import com.example.demo.dtos.requests.NurseRegisterDTO;
import com.example.demo.dtos.responses.DoctorCredentialsDTO;
import com.example.demo.dtos.responses.NurseCredentialsDTO;
import com.example.demo.helper.PasswordGenerator;
import com.example.demo.models.Doctor;
import com.example.demo.models.Nurse;
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
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class AdminController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/doctors")
    public List<Doctor> getDoctors(){
        List<Doctor> doctors = new ArrayList<>();
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from users where role_id = 2");
        for (Map<String, Object> row : list) {
            Doctor doctor = new Doctor();
            doctor.setId((Integer) row.get("user_id"));
            doctor.setFirstName((String) row.get("first_name"));
            doctor.setLastName((String) row.get("last_name"));
            doctors.add(doctor);
        }
        return doctors;
    }

    @GetMapping("/nurses")
    public List<Nurse> getNurses(){
        List<Nurse> nurses = new ArrayList<>();
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from users where role_id = 3");
        for (Map<String, Object> row : list) {
            Nurse nurse = new Nurse();
            nurse.setId((Integer) row.get("user_id"));
            nurse.setFirstName((String) row.get("first_name"));
            nurse.setLastName((String) row.get("last_name"));
            nurse.setBirthDate((String) row.get("birth_date"));
            nurses.add(nurse);
        }
        return nurses;
    }

    @PostMapping("/add_doctor")
    public DoctorCredentialsDTO addDoctor(@RequestBody DoctorRegisterDTO doctorRegisterDTO){
        String username = "";
        String doctorPassword = generatePassword();

        username = doctorRegisterDTO.getFirstName().substring(0,1).toUpperCase() + doctorRegisterDTO.getLastName()
                +String.valueOf(userRepository.setUsername(doctorRegisterDTO.getFirstName(),doctorRegisterDTO.getLastName()));

        String hashedPassword = Hashing.sha256()
                .hashString(doctorPassword, StandardCharsets.UTF_8)
                .toString();

        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = jdbcTemplate.getDataSource().getConnection();
            String insertDoctorString = "insert into users(first_name,last_name,birth_date,username,password,is_authorized, role_id) values(?,?,?,?,?,?,?)";
            preparedStatement = con.prepareStatement(insertDoctorString, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, doctorRegisterDTO.getFirstName().substring(0,1).toUpperCase()+doctorRegisterDTO.getFirstName().substring(1));
            preparedStatement.setString(2, doctorRegisterDTO.getLastName().substring(0,1).toUpperCase()+doctorRegisterDTO.getLastName().substring(1));
            preparedStatement.setString(3, doctorRegisterDTO.getBirthDate());
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, hashedPassword);
            preparedStatement.setString(6, "false");
            preparedStatement.setInt(7, 2);
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

        DoctorCredentialsDTO doctorCredentialsDTO = new DoctorCredentialsDTO();
        doctorCredentialsDTO.setId(userRepository.getUserIdByUsername(username));
        doctorCredentialsDTO.setPassword(doctorPassword);
        doctorCredentialsDTO.setUsername(username);

        return doctorCredentialsDTO;
    }

    @PostMapping("/add_nurse")
    public NurseCredentialsDTO addNurse(@RequestBody NurseRegisterDTO nurseRegisterDTO){
        String username = "";
        String nursePassword = generatePassword();

        username = nurseRegisterDTO.getFirstName().substring(0,1).toUpperCase() + nurseRegisterDTO.getLastName()
                +String.valueOf(userRepository.setUsername(nurseRegisterDTO.getFirstName(),nurseRegisterDTO.getLastName()));

        String hashedPassword = Hashing.sha256()
                .hashString(nursePassword, StandardCharsets.UTF_8)
                .toString();

        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = jdbcTemplate.getDataSource().getConnection();
            String insertDoctorString = "insert into users(first_name,last_name,birth_date,username,password,is_authorized, role_id) values(?,?,?,?,?,?,?)";
            preparedStatement = con.prepareStatement(insertDoctorString, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, nurseRegisterDTO.getFirstName().substring(0,1).toUpperCase()+nurseRegisterDTO.getFirstName().substring(1));
            preparedStatement.setString(2, nurseRegisterDTO.getLastName().substring(0,1).toUpperCase()+nurseRegisterDTO.getLastName().substring(1));
            preparedStatement.setString(3, nurseRegisterDTO.getBirthDate());
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, hashedPassword);
            preparedStatement.setString(6, "false");
            preparedStatement.setInt(7, 3);
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

        NurseCredentialsDTO nurseCredentialsDTO = new NurseCredentialsDTO();
        nurseCredentialsDTO.setId(userRepository.getUserIdByUsername(username));
        nurseCredentialsDTO.setPassword(nursePassword);
        nurseCredentialsDTO.setUsername(username);

        return nurseCredentialsDTO;
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

    @PostMapping("/authorize_doctor")
    public String authorizeDoctor(@RequestBody Integer doctorId){
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String info = "";
        try {
            con = jdbcTemplate.getDataSource().getConnection();

            String getUserQueryString = "update users set is_authorized = ? where user_id = ?";
            preparedStatement = con.prepareStatement(getUserQueryString);
            preparedStatement.setString(1,"true");
            preparedStatement.setInt(2,doctorId);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            info = "Doctor with user id = "+doctorId+ " is successfully authorized.";

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

    @PostMapping("/authorize_nurse")
    public String authorizeNurse(@RequestBody Integer nurseId){
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String info = "";
        try {
            con = jdbcTemplate.getDataSource().getConnection();

            String getUserQueryString = "update users set is_authorized = ? where user_id = ?";
            preparedStatement = con.prepareStatement(getUserQueryString);
            preparedStatement.setString(1,"true");
            preparedStatement.setInt(2,nurseId);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            info = "Nurse with user id = "+nurseId+ " is successfully authorized.";

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
