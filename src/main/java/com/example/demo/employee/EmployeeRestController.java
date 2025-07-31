package com.example.demo.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;
    private ObjectMapper objectMapper;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping("/employee")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employee/{id}")
    public Employee findById(@PathVariable int id) {
        var employee = employeeService.findById(id);

        //if id not found, it returns null
        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }
        return employee;
    }

    @PostMapping("/employee")
    public Employee save(@RequestBody Employee employee) {

        // also just in case they pass an id in JSON ... set it to 0
        // this is to force a save of new item ... instead of update
        employee.setId(0);
        return employeeService.save(employee);
    }

    @PutMapping("/employee")
    public Employee update(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    //patch - partial update
    // add mapping for employee/{id}
    @PatchMapping("/employee/{id}")
    public Employee patch(@PathVariable int id, @RequestBody Map<String, Object> patchPayload) {
        //retrieve the employee by id
        var employee = employeeService.findById(id);
        if (employee == null) throw new RuntimeException("Employee not found " + id);

        //throw exception if request body contains "id"
        if (patchPayload.containsKey("id"))
            throw new RuntimeException("Patch id not allowed " + patchPayload.get("id"));

        Employee patchedEmployee = apply(patchPayload, employee);
        return employeeService.save(patchedEmployee);
    }

    private Employee apply(Map<String, Object> patchPayload, Employee employee) {
        //convert employee object to a JSON object node
        ObjectNode employeeNode = objectMapper.convertValue(employee, ObjectNode.class);

        //convert patchPayLoad map to a JSON object node
        ObjectNode patchNode = objectMapper.convertValue(patchPayload, ObjectNode.class);

        //merge the patch updates into the employee node
        //This merges all fields from patchNode into employeeNode, overriding only the provided fields.
        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, Employee.class);
    }

    @DeleteMapping("/employee/{id}")
    public String delete(@PathVariable int id) {
        var employee = employeeService.findById(id);
        if (employee == null) throw new RuntimeException("Employee not found " + id);
        employeeService.deleteById(id);
        return "Employee deleted " + id;
    }
}
