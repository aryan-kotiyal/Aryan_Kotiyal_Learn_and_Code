package com.example.rest_api.service;

import com.example.rest_api.constants.Messages;
import com.example.rest_api.exception.EmployeeNotFoundException;
import com.example.rest_api.model.Employee;
import com.example.rest_api.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Collection<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(String employeeId) {
        Employee employee = employeeRepository.findById(employeeId);
        if (employee == null) {
            throw new EmployeeNotFoundException(String.format(Messages.EMPLOYEE_NOT_FOUND, employeeId));
        }
        return employee;
    }

    public String createEmployee(Employee employee) {
        employeeRepository.save(employee);
        return Messages.EMPLOYEE_CREATED;
    }

    public String updateEmployee(Employee employee) {
        if (!employeeRepository.existsById(employee.getEmployeeId())) {
            throw new EmployeeNotFoundException(String.format(Messages.EMPLOYEE_NOT_FOUND_FOR_UPDATE, employee.getEmployeeId()));
        }
        employeeRepository.save(employee);
        return Messages.EMPLOYEE_UPDATED;
    }

    public String deleteEmployee(String employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new EmployeeNotFoundException(String.format(Messages.EMPLOYEE_NOT_FOUND_FOR_DELETE, employeeId));
        }
        employeeRepository.deleteById(employeeId);
        return Messages.EMPLOYEE_DELETED;
    }

}
