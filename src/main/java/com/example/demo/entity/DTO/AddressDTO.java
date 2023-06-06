package com.example.demo.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private String address;
    private String district;
    private String subdistrict;
    private String city;
    private String province;
    private String country;
    private String postcode;
}
