package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Slf4j
public class ApplicationController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Employee employee) {

        employeeServiceImpl.signUp(employee);
        return ResponseEntity.ok("Data saved Succesfully");
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<String> signIn(@PathVariable String empEmailId, @PathVariable String empPassword) {
        boolean flag = employeeServiceImpl.signIn(empEmailId, empPassword);

        if (flag == true)
            return ResponseEntity.ok("SignIn Done Succesfully");
        else
            return ResponseEntity.ok("invalid emaildid or password");
    }

    @GetMapping("/getdatabyid/{empId}")
    public Optional<Employee> getDataById(@PathVariable int empId) {
        return employeeServiceImpl.getDataById(empId);
    }

    @GetMapping("/getdatabyname/{empName}")
    public ResponseEntity<List<Employee>> getDataByName(@PathVariable String empName) {
        return ResponseEntity.ok(employeeServiceImpl.getDataByName(empName));
    }

    @GetMapping("/getdatabydob/{empDOB}")
    public ResponseEntity<List<Employee>> getDataByDob(@PathVariable String empDOB) {
        return ResponseEntity.ok(employeeServiceImpl.getDataByDOB(empDOB));
    }

    @GetMapping("/getdatabyuid/{empUID}")
    public ResponseEntity<Employee> getDataByUID(@PathVariable long empUID) {
        return ResponseEntity.ok(employeeServiceImpl.getDataByUId(empUID));
    }

    @GetMapping("/getdatabycontactnumber/{empContactNumber}")
    public ResponseEntity<Employee> getDataByContactNumber(@PathVariable long empContactNumber) {
        return ResponseEntity.ok(employeeServiceImpl.getDataByContactNumber(empContactNumber));
    }

    @GetMapping("/getdatabygender/{empGender}")
    public ResponseEntity<List<Employee>> getDataByGender(@PathVariable String empGender) {
        return ResponseEntity.ok(employeeServiceImpl.getDataByGender(empGender));
    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<Employee>> getAllData() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData());
    }

    @GetMapping("/findbyanyinpput/{input}")
    public ResponseEntity<List<Employee>> findDataByAnyInput(@PathVariable String input) {
        return ResponseEntity.ok(employeeServiceImpl.findByInput(input));
    }

    @PutMapping("/updatedatabyid/{empId}")
    public ResponseEntity<Employee> updateDataById(@PathVariable int empId, @RequestBody Employee employee) {
        Employee employee1 = employeeServiceImpl.getDataById(empId).orElseThrow(() -> new RecordNotFoundException("Id Does Not Exist"));
        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpPassword(employee.getEmpPassword());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpGender(employee.getEmpGender());
        employee1.setEmpPancardNumber(employee.getEmpPancardNumber());
        employee1.setEmpUID(employee.getEmpUID());
        return ResponseEntity.ok(employeeServiceImpl.updatedataById(empId, employee1));
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Employee>> sortByName() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpName)).collect(Collectors.toList()));
    }

    @GetMapping("/sortbysalary")
    public ResponseEntity<List<Employee>> sortBySalary() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary)).collect(Collectors.toList()));
    }

    @GetMapping("/sortbydob")
    public ResponseEntity<List<Employee>> sortByDOB() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpDOB)).collect(Collectors.toList()));
    }

    @GetMapping("/sortbygender")
    public ResponseEntity<List<Employee>> sortByGender() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparing(Employee::getEmpGender)).collect(Collectors.toList()));

    }

    @GetMapping("/sortbyid")
    public ResponseEntity<List<Employee>> sortById() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().sorted(Comparator.comparingInt(Employee::getEmpId)).collect(Collectors.toList()));
    }

    @GetMapping("/filterbysalary/{empSalary}")
    public ResponseEntity<List<Employee>> filterBySalary(@PathVariable double empSalary) {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp -> emp.getEmpSalary() >= empSalary).collect(Collectors.toList()));
    }

    @GetMapping("/filterbygender/{empGender}")
    public ResponseEntity<List<Employee>> filterByGender(@PathVariable String empGender) {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp -> emp.getEmpGender().equals(empGender)).collect(Collectors.toList()));
    }

    @GetMapping("/checkloanstatus")
    public ResponseEntity<List<Employee>> checkLoanEligibilityStatus() {
        return ResponseEntity.ok(employeeServiceImpl.getAllData().stream().filter(emp -> emp.getEmpSalary() >= 50000).collect(Collectors.toList()));
    }

    @PostMapping("/savebulkofdata")
    public ResponseEntity<String> saveBulkOfData(@RequestBody List<Employee> employees) {
        employeeServiceImpl.saveBulkOfData(employees);
        return ResponseEntity.ok("Bulk Of data Saved Succesfully");

    }

    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> deleteDataById(@PathVariable int empId) {
        employeeServiceImpl.deleteDataById(empId);
        return ResponseEntity.ok("Data Deleted Succesfully");

    }

    @DeleteMapping("/deletealldata")
    public ResponseEntity<String> deleteAllData() {
        employeeServiceImpl.deleteAllData();
        return ResponseEntity.ok("All data Deleted");
    }


}
