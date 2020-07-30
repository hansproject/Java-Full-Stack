package com.hibernate.inheritance;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Test {

    public static void main(String[] args) {

//        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("/com/hibernateTesting/hibernate.cfg.xml").build();
//        Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();
        // Create session
//        SessionFactory factory = meta.getSessionFactoryBuilder().build();
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = sessionFactory.openSession();

        // Begin transaction
        Transaction t = session.beginTransaction();

        RegEmployee emp1 = new RegEmployee(126,"Ramy","India", 100f, 0);
        session.save(emp1);
//
        ContractEmp emp2 = new ContractEmp(457,"Reddy","India",20f, "1 year");
        session.save(emp2);


        // Commit transaction
        t.commit();
        // End transaction
        sessionFactory.close();
        session.close();
        System.out.println("Success....!");
    }
}
