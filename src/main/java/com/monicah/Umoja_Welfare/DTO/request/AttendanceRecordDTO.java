package com.monicah.Umoja_Welfare.DTO.request;

import com.monicah.Umoja_Welfare.Entity.AttendanceStatus;
import com.monicah.Umoja_Welfare.Entity.MeetingType;
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
public class AttendanceRecordDTO implements Serializable {
    private Long id;
    private Long meetingId;
    private Long memberId;
    private AttendanceStatus attendanceStatus= AttendanceStatus.PRESENT;
    private MeetingType meetingType = MeetingType.MONTHLY;
    private LocalDateTime arrivalTime;
    private BigDecimal penaltyAmount = BigDecimal.ZERO;
    private String phoneNumber;
    private boolean penaltyPaid = false;
    private LocalDate paymentDate;
    private BigDecimal monthlyContribution;

}
