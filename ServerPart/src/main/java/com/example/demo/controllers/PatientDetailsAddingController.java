package com.example.demo.controllers;

import com.example.demo.dtos.responses.PatientDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/add_patient_details", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class PatientDetailsAddingController {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @PostMapping("")
    public void addPatientDetails(@RequestBody PatientDetailsDTO patientDetailsDTO) {
        Connection con = null;
        PreparedStatement preparedStatement = null;

        try {
            con = jdbcTemplate.getDataSource().getConnection();
            String insertPatientString = "insert into patient_details(diagnostic,prescription,user_id,doctor_id) values(?,?,?,?)";
            preparedStatement = con.prepareStatement(insertPatientString, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, patientDetailsDTO.getDiagnostics());
            preparedStatement.setString(2, patientDetailsDTO.getPrescription());
            preparedStatement.setInt(3, patientDetailsDTO.getUserId());
            preparedStatement.setInt(4, patientDetailsDTO.getDoctorId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null && !preparedStatement.isClosed())
                    preparedStatement.close();
                if (con != null && !con.isClosed())
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
