package com.example.demo.employee;

import java.util.List;

public interface EmployeeDAOJpa {
    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteById(int id);
}
