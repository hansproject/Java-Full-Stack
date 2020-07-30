package com.hibernate.testingwithannotations;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
//import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Test {

    public static void main(String[] args) {

//        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("/com/hibernateTesting/hibernate.cfg.xml").build();
//        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        // Create session
//        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        SessionFactory sessionFactory = new Configuration().
                configure().
                //addPackage("com.xyz") //add package if used.
                        addAnnotatedClass(Employee.class).
                        configure("hibernate.cfg.xml").
                        buildSessionFactory();
        Session session = sessionFactory.openSession();

        // Begin transaction
        Transaction t = session.beginTransaction();


//        Employee emp1 = new Employee(125,"Ndef","IV");
//        session.save(emp1);
        // Name parameter query
//        Query<Employee> query = session.createQuery("FROM com.hibernateTestingWithAnnotations.Employee WHERE empID = :employee_id");
//        query.setParameter("employee_id",123);

        // Positional query
        Query query = session.createQuery("FROM com.hibernateTestingWithAnnotations.Employee WHERE empID =?0");
        query.setParameter(0,123);

        // Add
        List<Employee> empList = query.list();

        for(Employee emp : empList){
            System.out.println(emp.getEmpInfo());
        }
//        Employee emp2 = new Employee(456,"Senpai","AFR");
//        session.save(emp2);


        // Commit transaction
        t.commit();

        //
        // End transaction
        sessionFactory.close();
        session.close();

        System.out.println("Success....!");
    }
}
