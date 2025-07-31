package com.example.demo.employee;

import com.example.demo.datajpa.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeSerivceImpl implements EmployeeService {

    //    private EmployeeDAOJpa repository;
    private EmployeeRepository repository;

    @Autowired
    public EmployeeSerivceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public Employee findById(int id) {
        //return repository.findById(id);
        Optional<Employee> employee = repository.findById(id);
        return employee.orElse(null);
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }
}
