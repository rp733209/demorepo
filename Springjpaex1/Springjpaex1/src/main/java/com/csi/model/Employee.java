package com.csi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Employee {

    @Id
    @GeneratedValue
    private int empId;

    private String empName;

    private String empAddress;

    @Column(unique = true)
    private long empContactNumber;

    private double empSalary;

    @Column(unique = true)
    private long empUID;

    @Column(unique = true)
    private String empPancardNumber;

    @Column(unique = true)
    @Valid
    private String empEmailId;

    private String empPassword;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date empDOB;

    private String empGender;
}
