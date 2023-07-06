package com.csi.service;

import com.csi.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;


public interface Employeeservice {
    public void signUp(Employee employee);

    public boolean signIn(String empEmailId,String empPassword);

    public Employee getDataById(int empId);

    public List<Employee> getDataByName(String empName);

    public List<Employee> sortBySalary();

    public Employee getDataByContactNumber(long empContactNumber);

    public Employee getDataByEmailId(String empEmailId);

    public List<Employee> getDataByAnyInput(String input);

    public List<Employee> getAllData();

    public List<Employee> filterDataBySalary(double empSalary);

    public List<Employee> sortByName();

    public List<Employee> sortById();

    public List<Employee> checkLoanEligibilityStatus(double empSalary);

    public void savebulkOfData(List<Employee> employees);

    public void updateDataById(int empId,Employee employee);

    public void deleteById(int empId);

    public void deleteAllData();

    public List<Employee> getDataByDOB(String empDOB);

    public List<Employee> sortByDOB();

}
