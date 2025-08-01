package com.example.demo.datajpa;

import com.example.demo.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;


//@RepositoryRestResource(path="members")
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
