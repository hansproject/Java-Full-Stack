package com.corejava.spring;

import com.hibernate.daopattern.Employee;

public interface EmployeeDAO {

    public boolean createTable();

    public boolean insert(int empID, String empName, String empAdd);

    public Employee retrieve(int empID);

    public boolean update(int empID, String newName, String newAdd);

    public boolean delete(int empID);
}
