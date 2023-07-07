package com.csi.service;

import com.csi.dao.EmployeeDaoImpl;
import com.csi.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl {
    @Autowired
    EmployeeDaoImpl employeeDaoimpl;

    public void signUp(Employee employee) {
        employeeDaoimpl.signUp(employee);
    }

    public boolean signIn(String empEmailId, String empPassword) {

        return employeeDaoimpl.signIn(empEmailId, empPassword);
    }

    public Optional<Employee> getDataById(int empId) {
        return employeeDaoimpl.getDataById(empId);
    }

    public List<Employee> getDataByName(String empName) {

        return employeeDaoimpl.getDataByName(empName);
    }

    public List<Employee> getDataByDOB(String empDOB) {

        return employeeDaoimpl.getDataByDOB(empDOB);
    }

    public Employee getDataByUId(long empUID) {

        return employeeDaoimpl.getDataByUId(empUID);
    }

    public Employee getDataByContactNumber(long empContactNumber) {
        return employeeDaoimpl.getDataByContactNumber(empContactNumber);
    }

    public List<Employee> getDataByGender(String empGender) {

        return employeeDaoimpl.getDataByGender(empGender);
    }

    public List<Employee> getAllData() {

        return employeeDaoimpl.getAllData();
    }

    public List<Employee> findByInput(String input) {

        return employeeDaoimpl.findByInput(input);
    }

    public Employee updatedataById(int empId, Employee employee) {
        return employeeDaoimpl.updatedataById(empId, employee);
    }

    public void saveBulkOfData(List<Employee> employees) {

        employeeDaoimpl.saveBulkOfData(employees);
    }

    public void deleteDataById(int empId) {

        employeeDaoimpl.deleteDataById(empId);
    }

    public void deleteAllData() {

        employeeDaoimpl.deleteAllData();
    }

}

