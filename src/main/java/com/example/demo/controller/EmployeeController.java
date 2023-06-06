package com.example.demo.controller;


import com.example.demo.entity.DTO.SearchRequestDTO;
import com.example.demo.entity.DTO.SearchResponseDTO;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.service.EmployeeNativeQueryService;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeNativeQueryService nativeQueryService;

    @Autowired
    EmployeeRepo employeeRepo;


    //JPA
    @GetMapping(path = "/employees")
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> response = this.employeeService.getEmployees();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        Employee response = this.employeeService.getEmployeeById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = this.employeeService.addEmployee(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path = "/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee employee) {
        Employee updatedEmployee = this.employeeService.updateEmployee(id, employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.CREATED);
    }

//    @DeleteMapping(path = "/employee/{id}")
//    public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {
//        try {
//            this.employeeService.deleteEmployee(id);
//            return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
//        } catch (ResponseStatusException ex) {
//            return new ResponseEntity<>(ex.getMessage(), ex.getStatusCode());
//        }
//    }

    @DeleteMapping(path = "/employee/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable Integer id) {
        try {
            this.employeeService.deleteEmployee(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Employee deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", ex.getMessage());
            return new ResponseEntity<>(errorResponse, ex.getStatusCode());
        }
    }





    //NATIVE QUERY
    @GetMapping(path = "native/employees")
    public ResponseEntity<List<Employee>> getEmp() {
        List<Employee> response = this.nativeQueryService.getEmp();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "native/create")
    public ResponseEntity<Employee> addEmp(@RequestBody Employee employee) {
        Employee createdEmployee = this.nativeQueryService.addEmp(employee);
        return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path = "native/employee/{id}")
    public ResponseEntity<Employee> updateEmp(@PathVariable Integer id, @RequestBody Employee employee) {
        Employee updatedEmployee = this.nativeQueryService.updateEmp(id, employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.CREATED);
    }

    @DeleteMapping (path="native/employee/{id}")
    public ResponseEntity<String> deleteEmp(@PathVariable Integer id) {
        try {
            this.nativeQueryService.deleteEmp(id);
            return new ResponseEntity<>("Employee deleted successfully", HttpStatus.OK);
        } catch (ResponseStatusException ex) {
            return new ResponseEntity<>(ex.getMessage(), ex.getStatusCode());
        }
    }

    //SEARCH
    @PostMapping (path="/search")
    public ResponseEntity<List<Employee>> searchEmployee(@RequestBody SearchRequestDTO request) {
        List<Employee> response;
        if (!request.getId().isEmpty() || !request.getName().isEmpty() || !request.getSurname().isEmpty()) {
            response = this.employeeService.searchEmployee(request);
        } else {
            response = this.employeeService.getEmployees();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
