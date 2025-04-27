package com.example.rest_api.repository;

import com.example.rest_api.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeRepository {

    private final Map<String, Employee> employees = new HashMap<>();

    public Employee findById(String employeeId) {
        return employees.get(employeeId);
    }

    public Collection<Employee> findAll() {
        return employees.values();
    }

    public void save(Employee employee) {
        employees.put(employee.getEmployeeId(), employee);
    }

    public void deleteById(String employeeId) {
        employees.remove(employeeId);
    }

    public boolean existsById(String employeeId) {
        return employees.containsKey(employeeId);
    }

}
