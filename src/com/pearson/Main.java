package com.pearson;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        String dbUrl = "jdbc:mysql://localhost:3306/java_mysql?useSSL=false";
        String username = "root";
        String password = "root";

        try{
            Connection connection = DriverManager.getConnection(dbUrl, username, password);
            if(connection != null){
                System.out.println("Connected !");

                // Executing INSERT statement
                String sql = "INSERT INTO users (id, name) VALUES (?, ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1,2);
                preparedStatement.setString(2,"Sarath");

//                int insertedRowCount = preparedStatement.executeUpdate();
//                if(insertedRowCount > 0){
//                    System.out.println("A new user has been added succesfully !");
//                }

                // Executing SELECT statement
                System.out.println("----TABLE users----");

                sql = "SELECT * FROM users";

                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery(sql);

                while(resultSet.next()){
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);

                    System.out.println("id: " + id + " name: " + name);
                }

                System.out.println("--------- ---------");

                // Executing UPDATE statement
                System.out.println("----UPDATING----");

                sql = "UPDATE users SET name = ? WHERE id = ?";

                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setString(1,"Ajith");
                preparedStatement.setInt(2,2);

                int updatedRowCount = preparedStatement.executeUpdate();
                if(updatedRowCount > 0){
                    System.out.println("user table updated");
                }

                // Executing DELETE statement
                sql = "DELETE FROM users WHERE id = ?";

                preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1,2);

                int deletedRowCount = preparedStatement.executeUpdate();

                if(deletedRowCount > 0){
                    System.out.println("One User Deleted !");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }


    }
}
