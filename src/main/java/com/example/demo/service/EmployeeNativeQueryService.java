package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.util.List;

@Transactional
@Service
public class EmployeeNativeQueryService {

    @Autowired
    EmployeeRepo employeeRepo;
    @PersistenceContext
    private EntityManager entityManager;


    public List<Employee> getEmp() {
        String nativeQuery = "SELECT * FROM employee";
        Query query = entityManager.createNativeQuery(nativeQuery, Employee.class);
        return query.getResultList();
    }

    public Employee addEmp(Employee employee) {
        String nativeQuery = "INSERT INTO employee (name, surname, telephone, email, address, district, subdistrict, city, province, country, postcode) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Query query = entityManager.createNativeQuery(nativeQuery);
        query.setParameter(1, employee.getName());
        query.setParameter(2, employee.getSurname());
        query.setParameter(3, employee.getTelephone());
        query.setParameter(4, employee.getEmail());
        query.setParameter(5, employee.getAddress());
        query.setParameter(6, employee.getDistrict());
        query.setParameter(7, employee.getSubdistrict());
        query.setParameter(8, employee.getCity());
        query.setParameter(9, employee.getProvince());
        query.setParameter(10, employee.getCountry());
        query.setParameter(11, employee.getPostcode());
        query.executeUpdate();
        return employee;
    }

    public Employee updateEmp(Integer id, Employee employee) {
            String nativeQuery = "UPDATE employee SET name = ?, surname = ?, telephone = ?, email = ?, address = ?, district = ?, subdistrict = ?, city = ?, province = ?, country = ?, postcode = ? WHERE id = ?";
            Query query = entityManager.createNativeQuery(nativeQuery);
            query.setParameter(1, employee.getName());
            query.setParameter(2, employee.getSurname());
            query.setParameter(3, employee.getTelephone());
            query.setParameter(4, employee.getEmail());
            query.setParameter(5, employee.getAddress());
            query.setParameter(6, employee.getDistrict());
            query.setParameter(7, employee.getSubdistrict());
            query.setParameter(8, employee.getCity());
            query.setParameter(9, employee.getProvince());
            query.setParameter(10, employee.getCountry());
            query.setParameter(11, employee.getPostcode());
            query.setParameter(12, id);
            query.executeUpdate();
            return employee;
        }

    public void deleteEmp(Integer id) {
        String nativeQuery = "DELETE FROM employee WHERE id = ?";
        Query query = entityManager.createNativeQuery(nativeQuery);
        query.setParameter(1, id);
        int rowsAffected = query.executeUpdate();
        if (rowsAffected == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not exist with id: " + id);
        }
    }
}
