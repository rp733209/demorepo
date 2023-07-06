package com.csi.dao;

import com.csi.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    private static SessionFactory factory=new AnnotationConfiguration().configure().buildSessionFactory();


    @Override
    public void signUp(Employee employee) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(employee);
        transaction.commit();
    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {
        Session session = factory.openSession();
        boolean flag = false;
        for (Employee employee : getAllData()) {
            if (employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)) {
                flag = true;
                break;
            }

        }
        return flag;
    }

    @Override
    public Employee getDataById(int empId) {
        Session session = factory.openSession();

        Employee employee = new Employee();
        for (Employee employee1 : getAllData()) {
            if (employee1.getEmpId() == empId) {
                employee = employee1;
            }

        }
        return employee;
    }

    @Override
    public List<Employee> getDataByName(String empName) {

        return getAllData().stream().filter(e -> e.getEmpName().equals(empName)).collect(Collectors.toList());
    }

    @Override
    public List<Employee> sortBySalary() {
        return getAllData().stream().sorted(Comparator.comparingDouble(Employee::getEmpSalary)).collect(Collectors.toList());
    }

    @Override
    public Employee getDataByContactNumber(long empContactNumber) {
        return (Employee) getAllData().stream().filter(e -> e.getEmpContactNumber() == (empContactNumber)).collect(Collectors.toList()).get(0);
    }

    @Override
    public Employee getDataByEmailId(String empEmailId) {
        return (Employee) getAllData().stream().filter(e -> e.getEmpEmailId().equals(empEmailId)).collect(Collectors.toList()).get(0);
    }

    @Override
    public List<Employee> getDataByAnyInput(String input) {
        List<Employee> list = new ArrayList<>();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
        for (Employee employee : getAllData()) {
            String emDOB=simpleDateFormat.format(employee.getEmpDOB());
            if (employee.getEmpName().equals(input) || employee.getEmpAddress().equals(input) || employee.getEmpEmailId().equals(input) || String.valueOf(employee.getEmpId()).equals(input) || String.valueOf(employee.getEmpContactNumber()).equals(input) || emDOB.equals(input)) {
                list.add(employee);
            }
        }


        return list;
    }

    @Override
    public List<Employee> getAllData() {
        Session session = factory.openSession();

        return session.createQuery("from Employee").list();
    }

    @Override
    public List<Employee> filterDataBySalary(double empSalary) {
        return getAllData().stream().filter(employee -> employee.getEmpSalary() >= empSalary).collect(Collectors.toList());
    }

    @Override
    public List<Employee> sortByName() {
        return getAllData().stream().sorted(Comparator.comparing(Employee::getEmpName)).collect(Collectors.toList());
    }

    @Override
    public List<Employee> sortById() {
        return getAllData().stream().sorted(Comparator.comparing(Employee::getEmpId)).collect(Collectors.toList());
    }

    @Override
    public List<Employee> checkLoanEligibilityStatus(double empSalary) {

        return getAllData().stream().filter(emp -> emp.getEmpSalary() >= empSalary).collect(Collectors.toList());
    }

    @Override
    public void savebulkOfData(List<Employee> employees) {
        Session session = factory.openSession();

        for (Employee employee : employees) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();


        }

    }

    @Override
    public void updateDataById(int empId, Employee employee) {
        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

        for (Employee employee1 : getAllData()) {
            if (employee1.getEmpId() == empId) {
                employee1.setEmpName(employee.getEmpName());
                employee1.setEmpAddress(employee.getEmpAddress());
                employee1.setEmpContactNumber(employee.getEmpContactNumber());
                employee1.setEmpEmailId(employee.getEmpEmailId());
                employee1.setEmpDOB(employee.getEmpDOB());
                employee1.setEmpPassword(employee.getEmpPassword());
                employee1.setEmpSalary(employee.getEmpSalary());
                session.update(employee1);
                transaction.commit();

            }

        }

    }

    @Override
    public void deleteById(int empId) {

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        for (Employee employee : getAllData()) {
            if (employee.getEmpId() == empId) {
                session.delete(employee);
                transaction.commit();
            }

        }

    }

    @Override
    public void deleteAllData() {
        Session session = factory.openSession();

        for (Employee employee : getAllData()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }

    @Override
    public List<Employee> getDataByDOB(String empDOB) {
        Session session=factory.openSession();

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
        List<Employee> employees =session.createQuery("from Employee").list();
        List<Employee> emplist=new ArrayList<>();
        for (Employee employee : employees){
            String dbDOBDate=simpleDateFormat.format(employee.getEmpDOB());
            if (empDOB.equals(dbDOBDate)){
                emplist.add(employee);
            }

        }
        return emplist;
    }

    @Override
    public List<Employee> sortByDOB() {
        return getAllData().stream().sorted(Comparator.comparing(Employee::getEmpDOB)).collect(Collectors.toList());
    }
}
