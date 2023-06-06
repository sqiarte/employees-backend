package com.example.demo.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponseDTO {
    private Integer id;
    private String name;
    private String surname;
    private String telephone;
    private String email;
}
