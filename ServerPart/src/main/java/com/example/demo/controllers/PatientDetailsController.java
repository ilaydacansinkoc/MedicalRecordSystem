package com.example.demo.controllers;

import com.example.demo.dtos.responses.PatientDetailsDTO;
import com.example.demo.models.Nurse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/patientdetails", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class PatientDetailsController {

    @Autowired
    JdbcTemplate jdbcTemplate;


    @PostMapping("")
    public List<PatientDetailsDTO> getPatientDetails(@RequestBody Integer userId){
        List<PatientDetailsDTO> patientDetailsDTOS = new ArrayList<>();
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select diagnostic, prescription, user_id, doctor_id from patient_details where user_id = "+userId);
        for (Map<String, Object> row : list) {
            PatientDetailsDTO patientDetailsDTO = new PatientDetailsDTO();
            patientDetailsDTO.setPrescription((String) row.get("prescription"));
            patientDetailsDTO.setDiagnostics((String) row.get("diagnostic"));
            patientDetailsDTO.setUserId((Integer) row.get("user_id"));
            patientDetailsDTO.setDoctorId((Integer) row.get("doctor_id"));
            patientDetailsDTOS.add(patientDetailsDTO);
        }
        return patientDetailsDTOS;
    }


}
