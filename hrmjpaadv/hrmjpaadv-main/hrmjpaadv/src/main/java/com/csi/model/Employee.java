package com.csi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    private int empId;

    @Size(min = 2, message = "Name should be atleast 2 character")
    private String empName;

    private String empAddress;

    @Column(unique = true)
    private long empContactNumber;

    private double empSalary;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date empDOB;

    @Email(message = "Email ID Must be valid")
    @Column(unique = true)
    private String empEmailId;

    @Size(min = 4, message = "Name should be atleast 4 character")
    private String empPassword;
}
