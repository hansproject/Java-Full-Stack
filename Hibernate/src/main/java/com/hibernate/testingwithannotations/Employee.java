package com.hibernate.testingwithannotations;

import javax.persistence.*;

@ Entity
@ Table(name = "employeelist")
public class Employee {

    @ Id //@ GeneratedValue(strategy = GenerationType.IDENTITY)
    @ Column(name = "empID")
    public int empID=000;

    @ Column(name = "empName")
    public String empName;

    @ Column(name = "empAdd")
    public String empAdd;

    public Employee(int empID, String empName, String empAdd) {
        this.empID = empID;
        this.empName = empName;
        this.empAdd = empAdd;
    }

    public Employee(){}

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpAdd() {
        return empAdd;
    }

    public void setEmpAdd(String empAdd) {
        this.empAdd = empAdd;
    }

    public String getEmpInfo(){ return empID+ " "+ empName+ " "+ empAdd;}

    @Override
    public String toString() {
        return "Employee{" +
                "empID=" + empID +
                ", empName='" + empName + '\'' +
                ", empAdd='" + empAdd + '\'' +
                '}';
    }


}
