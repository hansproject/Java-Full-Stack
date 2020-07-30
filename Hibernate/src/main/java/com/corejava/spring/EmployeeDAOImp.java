package com.corejava.spring;


import com.hibernate.daopattern.Employee;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class EmployeeDAOImp implements EmployeeDAO {

    private String tableName;
    private String tableParameters;
    private String username;
    private String password;
    private String driverName;
    private String url;

    public EmployeeDAOImp(){
        super();
        setTableParameters("(empID int(3), empName varchar(30), empAdd varchar(30))");}

    public EmployeeDAOImp(String tableName, String username, String password, String  driverName){
        setTableName(tableName);
        setTableParameters("(empID int(3), empName varchar(30), empAdd varchar(30))");
        setUsername(username);
        setPassword(password);
        setDriverName(driverName);
        setUrl("jdbc:mysql://localhost:3306/company");
    }

    public boolean createTable(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // here is the ClassNotFoundException
            Connection connection = DriverManager.getConnection(url, username, password);

            String sql = "create table "+ tableName+ tableParameters;
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Table created Successfully...!");
            connection.close();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("**** Error table not created....!");
            return false;
        }
    }

    public boolean insert(int empID, String empName, String empAdd) {
        try {
            Class.forName(driverName); // here is the ClassNotFoundException
            Connection connection = DriverManager.getConnection(url, "root", "root");

            String insertQuery = "insert into " + tableName + " values(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setInt(1,empID);
            statement.setString(2,empName);
            statement.setString(3,empAdd);

            int result = statement.executeUpdate();
            connection.close();
            if(result == 1) {
                System.out.println("Row inserted successfully....!");
                return true;
            }
            else
                return false;

        } catch (ClassNotFoundException| SQLException e) {
            e.printStackTrace();
            System.out.println("****Error occured....!");
            return false;
        }

    }

    public boolean insertImage(String imgName, String imgType){
        try {
            Class.forName(driverName); // here is the ClassNotFoundException
            Connection connection = DriverManager.getConnection(url, "root", "root");

            String insertQuery = "insert into " + tableName + "(Name, Type, Logo) values(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1,imgName);
            statement.setString(2,imgType);
            FileInputStream fin = new FileInputStream("C:\\Users\\ndeff\\Downloads\\logo.png");
            statement.setBinaryStream(3, fin);
//            statement.execute();

            int result = statement.executeUpdate();
            connection.close();
            if(result == 1) {
                System.out.println("Row inserted successfully....!");
                return true;
            }
            else
                return false;

        } catch (ClassNotFoundException | SQLException | FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("****Error occured....!");
            return false;
        }
    }

    public Employee retrieve(int empID) {
        return null;
    }

    public boolean update(int empID, String empName, String empAdd) {
        try {
            Class.forName(driverName); // here is the ClassNotFoundException
            Connection connection = DriverManager.getConnection(url, "root", "root");

            String sqlQuery = "UPDATE "+ tableName+ " SET empName =? , empAdd =? WHERE empID =?";

            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1,empName);
            statement.setString(2,empAdd);
            statement.setInt(3,empID);

            int result = statement.executeUpdate();
            connection.close();
            if(result == 1) {
                System.out.println("Row updated successfully....!");
                return true;
            }
            else
                return false;

        } catch (ClassNotFoundException| SQLException e) {
            e.printStackTrace();
            System.out.println("****Error occured....!");
            return false;
        }
    }

    public boolean delete(int empID) {
        return false;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTableParameters() {
        return tableParameters;
    }

    public void setTableParameters(String tableParameters) {
        this.tableParameters = tableParameters;
    }
}
