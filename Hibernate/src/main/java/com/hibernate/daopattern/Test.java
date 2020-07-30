package com.hibernate.daopattern;

public class Test {
    public static void main(String[] args) {

        EmployeeDAOImp employeeDAOImp = new EmployeeDAOImp();

       // System.out.println(employeeDAOImp.retrieve(123).toString());
        employeeDAOImp.update(124, "'Me'","'CONG'");
    }
}
