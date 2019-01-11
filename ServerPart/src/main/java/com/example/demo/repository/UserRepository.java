package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int getUserIdByUsername(String username) {
        String selectStr = "select user_id from users where username = ?";
        int userId = 0;
        PreparedStatement preparedStatement = null;
        Connection con = null;
        ResultSet keys = null;
        try {
            con = jdbcTemplate.getDataSource().getConnection();

            preparedStatement = con.prepareStatement(selectStr, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, username);
            keys = preparedStatement.executeQuery();
            keys.next();
            userId = keys.getInt(1);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(keys!=null && !keys.isClosed())
                    keys.close();
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

    public int getLastUserId(){
        String sql = "select user_id from users where user_id = (select max(user_id) from users)";
        int lastUserId = jdbcTemplate.queryForObject(sql,Integer.class);
        System.out.println("LAST USER ID: "+lastUserId);
        return lastUserId;
    }

    public int setUsername(String firstName, String lastName){
        int recordCount = 0;
        firstName = firstName.toUpperCase();
        String firstChar = firstName.substring(0,1).toUpperCase();
        String selectStr = "select count(*) from users where first_name like ? and last_name = ?";
        PreparedStatement preparedStatement = null;
        Connection con = null;
        ResultSet keys = null;
        try {
            con = jdbcTemplate.getDataSource().getConnection();

            preparedStatement = con.prepareStatement(selectStr, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, firstChar+"%");
            preparedStatement.setString(2, lastName);

            keys = preparedStatement.executeQuery();
            keys.next();
            recordCount = keys.getInt(1);
            System.out.println(recordCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try{
                if(keys!=null && !keys.isClosed())
                    keys.close();
                if(preparedStatement!= null && !preparedStatement.isClosed())
                    preparedStatement.close();
                if(con!=null && !con.isClosed())
                    con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }


        return recordCount;
    }

}
