package com.example.rest_api.service;

import com.example.rest_api.constants.Messages;
import com.example.rest_api.exception.EmployeeNotFoundException;
import com.example.rest_api.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {

    private final Map<String, Employee> employeeMap = new HashMap<>();

    public Collection<Employee> getAllEmployees() {
        return employeeMap.values();
    }

    public Employee getEmployeeById(String employeeId) {
        Employee employee = employeeMap.get(employeeId);
        if (employee == null) {
            throw new EmployeeNotFoundException(String.format(Messages.EMPLOYEE_NOT_FOUND, employeeId));
        }
        return employee;
    }

    public String createEmployee(Employee employee) {
        employeeMap.put(employee.getEmployeeId(), employee);
        return Messages.EMPLOYEE_CREATED;
    }

    public String updateEmployee(Employee employee) {
        if (!employeeMap.containsKey(employee.getEmployeeId())) {
            throw new EmployeeNotFoundException(String.format(Messages.EMPLOYEE_NOT_FOUND_FOR_UPDATE, employee.getEmployeeId()));
        }
        employeeMap.put(employee.getEmployeeId(), employee);
        return Messages.EMPLOYEE_UPDATED;
    }

    public String deleteEmployee(String employeeId) {
        if (!employeeMap.containsKey(employeeId)) {
            throw new EmployeeNotFoundException(String.format(Messages.EMPLOYEE_NOT_FOUND_FOR_DELETE, employeeId));
        }
        employeeMap.remove(employeeId);
        return Messages.EMPLOYEE_DELETED;
    }

}
