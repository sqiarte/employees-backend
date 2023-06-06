package com.example.demo.controller;


import com.example.demo.entity.DTO.EmployeeDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("")
public class PersonController {

    @GetMapping (path="/hello")
    public ResponseEntity<String> helloWorld() {
        String response = "Hello World!";
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    @PostMapping(path="/add")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employee) {
        System.out.println(employee);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

}
