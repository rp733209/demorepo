package com.csi.dao;

import com.csi.model.Employee;
import com.csi.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDaoImpl {

    @Autowired
    EmployeeRepository employeeRepositoryImpl;

    public void signUp(Employee employee) {
        employeeRepositoryImpl.save(employee);
    }

    public boolean signIn(String empEmailId, String empPassword) {
        boolean flag = false;
        Employee employee = employeeRepositoryImpl.findByEmpEmailIdAndEmpPassword(empEmailId, empPassword);
        if (employee != null && employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)) {
            flag = true;
        }
        return flag;
    }

    public Optional<Employee> getDataById(int empId) {

        return employeeRepositoryImpl.findById(empId);
    }

    public List<Employee> getDataByName(String empName) {
        return employeeRepositoryImpl.findByEmpName(empName);
    }

    public List<Employee> getDataByDOB(String empDOB) {

        List<Employee> list = new ArrayList<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (Employee employee : getAllData()) {
            String dobDATE = simpleDateFormat.format(employee.getEmpDOB());
            if (empDOB.equals(dobDATE)) {
                list.add(employee);
            }

        }


        return list;
    }

    public Employee getDataByUId(long empUID) {
        return employeeRepositoryImpl.findByEmpUID(empUID);
    }

    public Employee getDataByContactNumber(long empContactNumber) {
        return employeeRepositoryImpl.findByEmpContactNumber(empContactNumber);
    }

    public List<Employee> getDataByGender(String empGender) {
        return employeeRepositoryImpl.findByEmpGender(empGender);
    }

    public List<Employee> getAllData() {
        return employeeRepositoryImpl.findAll();
    }

    public List<Employee> findByInput(String input) {
        List<Employee> list = new ArrayList<>();
        for (Employee employee : getAllData()) {
            if (employee.getEmpName().equals(input) || employee.getEmpAddress().equals(input) || employee.getEmpGender().equals(input)
                    || employee.getEmpEmailId().equals(input) ||
                    employee.getEmpEmailId().equals(input) ||
                    employee.getEmpPancardNumber().equals(input) ||
                    String.valueOf(employee.getEmpId()).equals(input) ||
                    String.valueOf(employee.getEmpUID()).equals(input) ||
                    String.valueOf(employee.getEmpContactNumber()).equals(input)) {
                list.add(employee);
            }
        }
        return list;
    }

    public Employee updatedataById(int empId, Employee employee) {
        return employeeRepositoryImpl.save(employee);
    }

    public void saveBulkOfData(List<Employee> employees) {
        employeeRepositoryImpl.saveAll(employees);
    }

    public void deleteDataById(int empId) {
        employeeRepositoryImpl.deleteById(empId);
    }

    public void deleteAllData() {
        employeeRepositoryImpl.deleteAll();
    }

}
