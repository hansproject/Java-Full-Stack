package com.hibernate.testing;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Test {

    public static void main(String[] args) {

        String b = "i";
        String a = new String("hello");


        System.out.println("address of a: " + a.hashCode());
        System.out.println("address of b: " + b.hashCode());

        b = "hello";
        System.out.println("address of b after a = b: " + b.hashCode());
//        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("/com/hibernateTesting/hibernate.cfg.xml").build();
//        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        // Create session
//        SessionFactory factory = meta.getSessionFactoryBuilder().build();
//        SessionFactory sessionFactory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .buildSessionFactory();
//        Session session = sessionFactory.openSession();
//
//        // Begin transaction
//        Transaction t = session.beginTransaction();
////        Query<Employee> query = session.createQuery("FROM com.hibernateTesting.Employee");
//        Query<Employee> query = session.createQuery("UPDATE Employee emp set empAdd ='Hyderabad' where empID=123");
//        int count = query.executeUpdate();
//
//        System.out.println(count + " Record(s) Updated.");

//        // Add
//        List<Employee> results = query.list();
//        System.out.println("EmpDetails are: "+ results.get(0).getEmpID());
//        Employee emp1 = new Employee(123,"Hans","CMR");
//        session.save(emp1);
//
//        Employee emp2 = new Employee(456,"Peter","AFR");
//        session.save(emp2);


//        // Commit transaction
//        t.commit();
        // End transaction
//        sessionFactory.close();
//        session.close();
//        System.out.println("Success....!");
    }
}
