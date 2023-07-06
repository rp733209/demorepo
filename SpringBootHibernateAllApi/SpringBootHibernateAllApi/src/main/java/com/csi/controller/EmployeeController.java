package com.csi.controller;

import com.csi.handleexception.RecordNotFound;
import com.csi.model.Employee;
import com.csi.service.Employeeservice;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class EmployeeController {

    @Autowired
    Employeeservice employeeserviceImpl;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Employee employee) {
        log.info(".....Data Send To DataBase.....");
        employeeserviceImpl.signUp(employee);
        return ResponseEntity.ok("SignUp Done Succesfully");
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword) {

        return ResponseEntity.ok(employeeserviceImpl.signIn(empEmailId, empPassword));
    }

    @Cacheable(value = "empId")
    @GetMapping("/getdatabyid/{empId}")
    public ResponseEntity<Employee> getDataById(@PathVariable int empId) {
        log.info(".........Data Fetching From DataBase........");
        return ResponseEntity.ok(employeeserviceImpl.getDataById(empId));
    }

    @GetMapping("/getdatabyname/{empName}")
    public ResponseEntity<List<Employee>> getDataByName(@PathVariable String empName) {

        return ResponseEntity.ok(employeeserviceImpl.getDataByName(empName));
    }

    @GetMapping("/sortbysalary")
    public ResponseEntity<List<Employee>> sortBySalary() {
        return ResponseEntity.ok(employeeserviceImpl.sortBySalary());
    }

    @GetMapping("/getdatabycontactnumber/{empContactNumber}")
    public ResponseEntity<Employee> getDataByContactNumber(@PathVariable long empContactNumber) {
        return ResponseEntity.ok(employeeserviceImpl.getDataByContactNumber(empContactNumber));
    }

    @GetMapping("/getdatabyemailid/{empEmailId}")
    public ResponseEntity<Employee> getDataByEmailId(@PathVariable String empEmailId) {
        return ResponseEntity.ok(employeeserviceImpl.getDataByEmailId(empEmailId));
    }

    @GetMapping("getdatabyusinganyinput/{input}")
    public ResponseEntity<List<Employee>> getDataByUsingAnyInput(@PathVariable String input) {
        return ResponseEntity.ok(employeeserviceImpl.getDataByAnyInput(input));

    }

    @GetMapping("/getalldata")
    public ResponseEntity<List<Employee>> getAllData() {
        return ResponseEntity.ok(employeeserviceImpl.getAllData());
    }

    @GetMapping("/filterdatabysalary/{empSalary}")
    public ResponseEntity<List<Employee>> filterDataBySalary(@PathVariable double empSalary) {
        return ResponseEntity.ok(employeeserviceImpl.filterDataBySalary(empSalary));
    }

    @GetMapping("/sortbyname")
    public ResponseEntity<List<Employee>> sortByName() {
        return ResponseEntity.ok(employeeserviceImpl.sortByName());
    }

    @GetMapping("/sortbyid")
    public ResponseEntity<List<Employee>> sortById() {
        return ResponseEntity.ok(employeeserviceImpl.sortById());
    }

    @GetMapping("/checkloaneligibilitystatus/{empSalary}")
    public ResponseEntity<List<Employee>> checkLoanEligibilityStatus(double empSalary) {
        return ResponseEntity.ok(employeeserviceImpl.checkLoanEligibilityStatus(empSalary));
    }

    @PostMapping("/savebulkofdata/{employee}")
    public ResponseEntity<String> saveBulkOfData(@RequestBody List<Employee> employees) {
        employeeserviceImpl.savebulkOfData(employees);
        return ResponseEntity.ok("Saved data SuccesFully");
    }

    @PutMapping("/updatedatabyid/{empId}")
    public ResponseEntity<String> updateDataById(@PathVariable int empId, @RequestBody Employee employee) {
        int dbEmpId = employeeserviceImpl.getDataById(empId).getEmpId();
        if (dbEmpId == empId) {
            employeeserviceImpl.updateDataById(empId, employee);
        } else {
            throw new RecordNotFound("Employee Id Does Not exist");
        }
        return ResponseEntity.ok("Data Updated Successfully");
    }

    @DeleteMapping("/deletedatabyid/{empId}")
    public ResponseEntity<String> deleteDataById(@PathVariable int empId) {
        employeeserviceImpl.deleteById(empId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }

    @DeleteMapping("/deletealldata")
    public ResponseEntity<String> deleteAllData() {
        employeeserviceImpl.deleteAllData();
        return ResponseEntity.ok("All Data Deleted Successfully");
    }

    @GetMapping("/getdatabydob/{empDOB}")
    public ResponseEntity<List<Employee>> getDataByDOB(String empDOB) {
        return ResponseEntity.ok(employeeserviceImpl.getDataByDOB(empDOB));
    }

    @GetMapping("/sortbydob")
    public ResponseEntity<List<Employee>> sortByDOB() {
        return ResponseEntity.ok(employeeserviceImpl.sortByDOB());

    }


}
