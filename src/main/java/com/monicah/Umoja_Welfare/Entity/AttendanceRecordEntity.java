package com.monicah.Umoja_Welfare.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="attendance_register")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AttendanceRecordEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "meeting_status", nullable = false)
    private AttendanceStatus attendanceStatus = AttendanceStatus.PRESENT;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MeetingType meetingType = MeetingType.MONTHLY;

    private LocalDateTime arrivalTime;

    @Column(precision = 8,scale = 2)
    private BigDecimal penaltyAmount = BigDecimal.ZERO;

    @Column(nullable = false)
    private boolean penaltyPaid;
    @Column(length = 255)
    private String penaltyReason;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;


    @UpdateTimestamp
    private LocalDateTime updatedAt;
    // Many attendance records belong to one member
    @ManyToOne
    @JoinColumn(name = "phone_number", nullable = false)
    private MemberEntity member;

    // Many attendance records belong to one meeting
    @ManyToOne
    @JoinColumn(name = "meeting_id", nullable = false)
    private MeetingRegisterEntity meetingRegister;

}
