package com.monicah.Umoja_Welfare.DTO.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberResponseDTO {
    private String firstName;
    private String lastName;
    @Email(message = "Email must be valid")
    private String email;
    private String phoneNumber;
    private String membershipNumber;
    private LocalDate dateJoined;
    private BigDecimal totalContributions;
}
