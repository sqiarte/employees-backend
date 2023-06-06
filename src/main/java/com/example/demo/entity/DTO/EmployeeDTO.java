package com.example.demo.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private String id;
    private String name;
    private String username;
    private String password;
    private List<String> email;
    private AddressDTO address;
    private String telephone;
}
