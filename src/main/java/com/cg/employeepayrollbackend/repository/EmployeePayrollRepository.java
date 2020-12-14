package com.cg.employeepayrollbackend.repository;

import com.cg.employeepayrollbackend.model.EmployeePayrollData;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeePayrollRepository extends JpaRepository<EmployeePayrollData, Integer> {
}