package com.hibernate.inheritance;

public class RegEmployee extends Employee {

    public float salary;
    public int bonus;

    public RegEmployee(){}

    public RegEmployee(int empID, String empName, String empAdd, float salary, int bonus){
        super(empID,empName,empAdd);
        this.salary = salary;
        this.bonus  = bonus;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}

