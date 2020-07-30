package com.hibernate.inheritance;

public class ContractEmp extends Employee {

    public float payPerHour;
    public String contractPeriod;

    public ContractEmp() {}

    public ContractEmp(int empID, String empName, String empAdd, float payPerHour, String contractPeriod){
        super(empID,empName,empAdd);
        this.payPerHour = payPerHour;
        this.contractPeriod = contractPeriod;
    }

    public float getPayPerHour() {
        return payPerHour;
    }

    public void setPayPerHour(float payPerHour) {
        this.payPerHour = payPerHour;
    }

    public String getContractPeriod() {
        return contractPeriod;
    }

    public void setContractPeriod(String contractPeriod) {
        this.contractPeriod = contractPeriod;
    }
}
