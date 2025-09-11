package com.monicah.Umoja_Welfare.DTO.request;

import com.monicah.Umoja_Welfare.Entity.MemberStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MemberDTO implements Serializable {
   private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotBlank(message = "Membership number is required")
    private String membershipNumber;

    @NotNull(message = "Date joined is required")
    private LocalDate dateJoined;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status is required")
    private MemberStatus status = MemberStatus.ACTIVE;

    @DecimalMin(value = "0.00", inclusive = true, message = "Total contributions cannot be negative")
    @Digits(integer = 10, fraction = 2, message = "Invalid amount format")
    private BigDecimal totalContributions = BigDecimal.ZERO;
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
