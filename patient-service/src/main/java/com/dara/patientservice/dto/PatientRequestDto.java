package com.dara.patientservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PatientRequestDto {

    @NotBlank(message = "name is required")
    @Size(message = "name can not exceed 100 characters")
    private String name;
    @NotBlank(message = "email is required")
    @Email(message = "email should be valid")
    private String email;
    @NotBlank(message = "address is required")
    private String address;
    @NotBlank(message = "date Of Birth is required")
    private String dateOfBirth;
    @NotBlank(message = "register Date is required")
    private String registerDate;

}
