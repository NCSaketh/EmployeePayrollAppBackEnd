package com.cg.employeepayrollbackend.services;

import java.util.ArrayList;
import java.util.List;

import com.cg.employeepayrollbackend.exceptions.EmployeePayrollException;
import com.cg.employeepayrollbackend.repository.EmployeePayrollRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.employeepayrollbackend.dto.EmployeePayrollDTO;
import com.cg.employeepayrollbackend.model.EmployeePayrollData;

@Service
@Slf4j
public class EmployeePayrollService implements IEmployeePayrollService {

    @Autowired
    private EmployeePayrollRepository employeeRepository;

    private List<EmployeePayrollData> employeePayrollList = new ArrayList<>();

    public List<EmployeePayrollData> getEmployeePayrollData() {
        return employeePayrollList;
    }

    public EmployeePayrollData getEmployeePayrollDataById(int empId) {
        return employeePayrollList.stream()
                .filter(empData -> empData.getEmployeeId()==empId)
                .findFirst()
                .orElseThrow(() -> new EmployeePayrollException("Employee Not Found"));
    }

    public EmployeePayrollData createEmployeePayrollData(EmployeePayrollDTO empPayrollDTO) {
        EmployeePayrollData empData = null;
        empData = new EmployeePayrollData(employeePayrollList.size()+1,empPayrollDTO);
        log.debug("Emp Data: "+empData.toString());
        employeePayrollList.add(empData);
        return employeeRepository.save(empData);
    }

    public EmployeePayrollData updateEmployeePayrollData(int empId, EmployeePayrollDTO empPayrollDTO) {
        EmployeePayrollData empData = this.getEmployeePayrollDataById(empId);
        empData.setName(empPayrollDTO.name);
        empData.setSalary(empPayrollDTO.salary);
        empData.setGender(empPayrollDTO.gender);
        empData.setStartDate(empPayrollDTO.startDate);
        empData.setNote(empPayrollDTO.note);
        empData.setProfilePic(empPayrollDTO.profilePic);
        empData.setDepartments(empPayrollDTO.department);
        employeePayrollList.set(empId-1,empData);
        return empData;
    }

    public void deleteEmployeePayrollData(int empId) { employeePayrollList.remove(empId-1); }
}