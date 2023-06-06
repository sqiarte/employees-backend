package com.example.demo.service;

import com.example.demo.entity.DTO.SearchRequestDTO;
import com.example.demo.entity.DTO.SearchResponseDTO;
import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    public List<Employee> getEmployees() {
        return this.employeeRepo.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        return this.employeeRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Employee not exist with id: " + id));
    }

    public Employee addEmployee(Employee employee) {
        return this.employeeRepo.save(employee);
    }

    public Employee updateEmployee(Integer id, Employee employee) {
        Employee updateEmployee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Employee not exist with id: " + id));
        updateEmployee.setName(employee.getName());
        updateEmployee.setSurname(employee.getSurname());
        updateEmployee.setTelephone(employee.getTelephone());
        updateEmployee.setEmail(employee.getEmail());
        updateEmployee.setAddress(employee.getAddress());
        updateEmployee.setDistrict(employee.getDistrict());
        updateEmployee.setSubdistrict(employee.getSubdistrict());
        updateEmployee.setCity(employee.getCity());
        updateEmployee.setProvince(employee.getProvince());
        updateEmployee.setCountry(employee.getCountry());
        updateEmployee.setPostcode(employee.getPostcode());
        return this.employeeRepo.save(updateEmployee);
    }

    public void deleteEmployee(Integer id) {
        Employee deleteEmployee = employeeRepo.findById(id).orElse(null);
        if (deleteEmployee == null) {
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, "Employee not exist with id: " + id);
        }
        employeeRepo.delete(deleteEmployee);
    }


    //SEARCH

    public List<Employee> searchEmployee(SearchRequestDTO request) {
        System.out.println(request);
        List<Employee> employees = new ArrayList<>();
        String id = request.getId();
        String name = request.getName();
        String surname = request.getSurname();
        if (!id.isEmpty() && !name.isEmpty() && !surname.isEmpty()) {
            // id + name + surname
            List<Employee> employee = employeeRepo.findByPartialIdAndNameAndSurname(id, name, surname);
            if (employee != null) {
                employees.addAll(employee);
            }
            System.out.println("id + name + surname");
        } else {
            if (id.isEmpty()) {
                if (!name.isEmpty()) {
                    if (!surname.isEmpty()) {
                        // name + surname
                        List<Employee> searchEmployees = employeeRepo.findByPartialNameAndSurname(name, surname);
                        employees.addAll(searchEmployees);
                        System.out.println("name + surname");
                    } else {
                        // only name
                        List<Employee> nameEmployees = employeeRepo.findByPartialName(name);
                        employees.addAll(nameEmployees);
                        System.out.println("name");
                    }
                } else if (!surname.isEmpty()) {
                    // only surname
                    List<Employee> surnameEmployees = employeeRepo.findByPartialSurname(surname);
                    employees.addAll(surnameEmployees);
                    System.out.println("surname");
                }
            } else {
                if (!name.isEmpty()) {
                    // id + name
                    List<Employee> nameEmployees = employeeRepo.findByPartialIdAndName(id, name);
                    employees.addAll(nameEmployees);
                    System.out.println("id + name");
                } else if (!surname.isEmpty()){
                    // id + surname
                    List<Employee> surnameEmployees = employeeRepo.findByPartialIdAndSurname(id, surname);
                    employees.addAll(surnameEmployees);
                    System.out.println("id + surname");
                } else {
                    // only id
                    List<Employee> employee = employeeRepo.findByPartialId(id);
                    if (employee != null) {
                        employees.addAll(employee);
                    }
                    System.out.println("id");
                }
            }
        }
        return employees;
    }





}
