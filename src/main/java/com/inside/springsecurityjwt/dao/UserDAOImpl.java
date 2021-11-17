package com.inside.springsecurityjwt.dao;

import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component("userDAO")
public class UserDAOImpl implements UserDAO {

    DBWorker dbWorker = new DBWorker();
    String QUERY = "SELECT * FROM user_table";
    Statement statement;

    private String messageDB;
    List<String> messageList = new ArrayList<>();

    @Override
    public List<String> getMessageDB() {
        try {
            statement = dbWorker.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);
            while (resultSet.next()) {
                messageList.add(messageDB = resultSet.getString("message"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messageList;
    }
}
