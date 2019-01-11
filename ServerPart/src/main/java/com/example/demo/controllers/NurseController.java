package com.example.demo.controllers;

import com.example.demo.models.Nurse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ResponseStatus(HttpStatus.OK)
@RequestMapping(value = "/nurses", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class NurseController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("")
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

    @PostMapping("/authorize")
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
