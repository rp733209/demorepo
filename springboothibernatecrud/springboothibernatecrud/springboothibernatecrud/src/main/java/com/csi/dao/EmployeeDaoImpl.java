package com.csi.dao;

import com.csi.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    private static SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();

    @Override
    public void saveData(Employee employee) {

        Session session = factory.openSession();

        Transaction transaction = session.beginTransaction();

        session.save(employee);

        transaction.commit();

    }

    @Override
    public List<Employee> getAllData() {

        Session session = factory.openSession();
        return session.createQuery("from Employee").list();
    }

    @Override
    public void updateData(int empId, Employee employee) {

        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        for (Employee employee1 : getAllData()) {
            if (employee1.getEmpId() == empId) {
                employee1.setEmpAddress(employee.getEmpAddress());
                employee1.setEmpDOB(employee.getEmpDOB());
                employee1.setEmpSalary(employee.getEmpSalary());
                employee1.setEmpName(employee.getEmpName());
                employee1.setEmpContactNumber(employee.getEmpContactNumber());
                session.update(employee1);
                transaction.commit();
            }
        }
    }

    @Override
    public void deleteDataById(int empId) {
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        for (Employee employee1 : getAllData()) {
            if (employee1.getEmpId() == empId) {

                session.delete(employee1);
                transaction.commit();
            }
        }
    }
}
