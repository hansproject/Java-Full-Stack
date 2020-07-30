package com.corejava.spring;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Testing {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
//        Employee employee = (Employee)context.getBean("hello");
//        System.out.println(employee.toString());
        EmployeeDAOImp employeeDAOImp = new EmployeeDAOImp();
        employeeDAOImp.setUrl("jdbc:mysql://localhost:3306/company");
        employeeDAOImp.setDriverName("com.mysql.cj.jdbc.Driver");
        employeeDAOImp.setUsername("root");
        employeeDAOImp.setPassword("root");
        employeeDAOImp.setTableName("imagedb");
        employeeDAOImp.setTableParameters("(Name VARCHAR(255), Type VARCHAR(50), Logo BLOB)");
        employeeDAOImp.setDriverName("com.mysql.cj.jdbc.Driver");
        
//        employeeDAOImp.createTable();
        employeeDAOImp.insertImage("Canvs","JPEG");
       // employeeDAOImp.insert(123,"Hans","CMR");
//        employeeDAOImp.update(123, "Peter","CMR");
    }
}
