package com.hibernate.daopattern;

public interface EmployeeDAO {

    public void insert(int empID, String empName, String empAdd);

    public Employee retrieve(int empID);

    public void update(int empID, String newName, String newAdd);

    public void delete(int empID);
}
