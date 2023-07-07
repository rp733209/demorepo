package com.csi.repo;

import com.csi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public Employee findByEmpEmailIdAndEmpPassword(String empEmailId, String empPassword);

    public List<Employee> findByEmpName(String empName);

    public List<Employee> findByEmpDOB(String empDOB);

    public Employee findByEmpUID(long empUID);

    public Employee findByEmpContactNumber(long empContactNumber);

    public List<Employee> findByEmpGender(String empGender);
}





