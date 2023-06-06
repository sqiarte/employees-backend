package com.example.demo.repository;
import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository <Employee, Integer> {
        @Query("SELECT e FROM Employee e WHERE e.name LIKE %:name% AND e.surname LIKE %:surname%")
        List<Employee> findByPartialNameAndSurname(@Param("name") String name, @Param("surname") String surname);

        @Query("SELECT e FROM Employee e WHERE e.name LIKE %:name% AND e.surname LIKE %:surname% AND e.id LIKE %:id%")
        List<Employee> findByPartialIdAndNameAndSurname( @Param("id") String id, @Param("name") String name, @Param("surname") String surname);
        @Query("SELECT e FROM Employee e WHERE e.name LIKE %:name%")
        List<Employee> findByPartialName(@Param("name") String name);

        @Query("SELECT e FROM Employee e WHERE e.surname LIKE %:surname%")
        List<Employee> findByPartialSurname(@Param("surname") String surname);

        @Query("SELECT e FROM Employee e WHERE e.id LIKE %:id%")
        List<Employee> findByPartialId(@Param("id") String name);

        @Query("SELECT e FROM Employee e WHERE e.name LIKE %:name% AND e.id LIKE %:id%")
        List<Employee> findByPartialIdAndName( @Param("id") String id, @Param("name") String name);

        @Query("SELECT e FROM Employee e WHERE e.surname LIKE %:surname% AND e.id LIKE %:id%")
        List<Employee> findByPartialIdAndSurname( @Param("id") String id, @Param("surname") String surname);


}
