package services;

import config.DBConfig;
import entities.Employee;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthService  {

    private final DBConfig dbConfig;


    public AuthService() {
        dbConfig = new DBConfig();
    }

    public String login(String username, String password) {
        String id = "";
        try (Statement st = this.dbConfig.getConnection().createStatement()) {

            String query = String.format("SELECT * FROM employee \n" +
                    "WHERE username=\"%s\" AND password=\"%s\";", username, password);

            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                id = rs.getString("emp_id");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Username or password!", "Invalid Login", 0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}

