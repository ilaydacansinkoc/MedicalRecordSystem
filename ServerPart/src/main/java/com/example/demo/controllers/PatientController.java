package com.example.demo.controllers;

import com.example.demo.dtos.requests.PatientRegisterDTO;
import com.example.demo.dtos.responses.PatientCredentialsDTO;
import com.example.demo.dtos.responses.PatientDetailsDTO;
import com.example.demo.helper.PasswordGenerator;
import com.example.demo.models.Nurse;
import com.example.demo.models.Patient;
import com.example.demo.repository.UserRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.*;

@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/patients", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class PatientController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserRepository userRepository;

    private int userId;

    @PostMapping("/bydoctor")
    public List<Patient> getPatients(@RequestBody int doctorId){

        List<Patient> patients = new ArrayList<>();

        List<Map<String, Object>> list = jdbcTemplate.queryForList("select patient_first_name, patient_last_name, birth_date, doctor_id, patient_id from patients where doctor_id ="+doctorId);
        for (Map<String, Object> row : list) {
            Patient patient = new Patient();
            System.out.println(this.getUserId()+" **************");
            patient.setId((Integer) row.get("patient_id"));
            patient.setFirstName((String) row.get("patient_first_name"));
            patient.setLastName((String) row.get("patient_last_name"));
            patient.setBirthDate((String) row.get("birth_date"));
            patient.setDoctorId((Integer) row.get("doctor_id"));
            patients.add(patient);
        }

        return patients;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }
    public int getUserId(){
        return this.userId;
    }

    @PostMapping("")
    public PatientCredentialsDTO addPatient(@RequestBody PatientRegisterDTO patientRegisterDTO){


        String username = "";
        String patientPassword = generatePassword();

        username = patientRegisterDTO.getFirstName().substring(0,1).toUpperCase()
                + patientRegisterDTO.getLastName()
                +String.valueOf(userRepository.setUsername(patientRegisterDTO.getFirstName(),patientRegisterDTO.getLastName()));

        String hashedPassword = Hashing.sha256()
                .hashString(patientPassword, StandardCharsets.UTF_8)
                .toString();

        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = jdbcTemplate.getDataSource().getConnection();
            String insertPatientString = "insert into users(first_name,last_name,birth_date," +
                    "username,password,is_authorized, role_id) values(?,?,?,?,?,?,?)";
            preparedStatement = con.prepareStatement(insertPatientString, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, patientRegisterDTO.getFirstName().substring(0,1).toUpperCase()
                    +patientRegisterDTO.getFirstName().substring(1));
            preparedStatement.setString(2, patientRegisterDTO.getLastName().substring(0,1).toUpperCase()
                    +patientRegisterDTO.getLastName().substring(1));
            preparedStatement.setString(3, patientRegisterDTO.getBirthDate());
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, hashedPassword);
            preparedStatement.setString(6, "false");
            preparedStatement.setInt(7, 4);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            String insertPatientString1 = "insert into patients(patient_first_name,patient_last_name," +
                    "birth_date,doctor_id,patient_id) values(?,?,?,?,?)";
            preparedStatement = con.prepareStatement(insertPatientString1, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, patientRegisterDTO.getFirstName().substring(0,1).toUpperCase()
                    +patientRegisterDTO.getFirstName().substring(1));
            preparedStatement.setString(2, patientRegisterDTO.getLastName().substring(0,1).toUpperCase()
                    +patientRegisterDTO.getLastName().substring(1));
            preparedStatement.setString(3, patientRegisterDTO.getBirthDate());
            preparedStatement.setInt(4, patientRegisterDTO.getDoctorId());
            preparedStatement.setInt(5, userRepository.getUserIdByUsername(username));
            setUserId(userRepository.getUserIdByUsername(username));
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

        PatientCredentialsDTO patientCredentialsDTO = new PatientCredentialsDTO();
        patientCredentialsDTO.setId(userRepository.getUserIdByUsername(username));
        patientCredentialsDTO.setPassword(patientPassword);
        patientCredentialsDTO.setUsername(username);

        return patientCredentialsDTO;
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


}
