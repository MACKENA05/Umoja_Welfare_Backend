package com.monicah.Umoja_Welfare.DTO.response;

import com.monicah.Umoja_Welfare.Entity.AttendanceStatus;
import com.monicah.Umoja_Welfare.Entity.MeetingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendanceResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
   // private MeetingType meetingType;
    private LocalDateTime arrivalTime;
   // private BigDecimal penaltyAmount;
   // private boolean penaltyPaid;
    private BigDecimal monthlyContribution;

}
