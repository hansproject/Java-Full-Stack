package com.hibernate.daopattern;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EmployeeDAOImp implements EmployeeDAO {

    public SessionFactory sessionFactory;
    public Session session;

    public EmployeeDAOImp(){}

    public void openSession(){
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public void closeSession(){
        session.flush();
        session.close();
        sessionFactory.close();
    }

    public void insert(int empID, String empName, String empAdd) {

        openSession();
        // Begin transaction
        Transaction t = session.beginTransaction();
        Employee emp1 = new Employee(empID,empName,empAdd);
        session.save(emp1);

        // Commit transaction
        t.commit();
        // End transaction
        closeSession();
        System.out.println("Success....!");
    }

    public Employee retrieve(int empID) {

        openSession();
        // Begin transaction
        Transaction t = session.beginTransaction();
//        // Positional query
//        Query query = session.createQuery("FROM com.hibernateDAOPattern.Employee WHERE empID =?0");
//        query.setParameter(0,empID);

        // SQL Queries
        //______________________

        String sqlQuery = "SELECT * FROM employeelist WHERE empID = :employee_id";
        SQLQuery query = session.createSQLQuery(sqlQuery);
        query.addEntity("com.hibernateDAOPattern.Employee");
        query.setParameter("employee_id",empID);
        List<Employee> results = query.list();

        t.commit();
        closeSession();
        System.out.println("Success....!");

        return results.get(0);
    }

    public void update(int empID, String newName, String newAdd) {

        openSession();
        // Begin transaction
        Transaction t = session.beginTransaction();
        String sqlQuery = "UPDATE employeeList SET empName ="+ newName + " , empAdd ="+ newAdd +" WHERE empID = :employee_id";
        SQLQuery query = session.createSQLQuery(sqlQuery);
        query.addEntity("com.hibernateDAOPattern.Employee");
        query.setParameter("employee_id",empID);
//        Query<com.hibernateTesting.Employee> query = session.createQuery("UPDATE Employee emp set empAdd ='Hyderabad' where empID=?0");
//        query.setParameter(0, empID);
        int count = query.executeUpdate();

        System.out.println(count + " Record(s) Updated.");
        closeSession();

    }

    public void delete(int empID) {

    }


}
