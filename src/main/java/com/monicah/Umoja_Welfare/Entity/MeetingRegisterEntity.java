package com.monicah.Umoja_Welfare.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="meeting_registers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "attendanceRecords")

public class MeetingRegisterEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")  // primary key column
    private Long id;

    @Column(name="meeting_date",nullable = false)
    private LocalDate meetingDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "meeting_type", nullable = false)
    private MeetingType meetingType = MeetingType.MONTHLY;
    @Column(name = "meeting_notes", columnDefinition = "TEXT")
    private String meetingNotes;

    @OneToMany(mappedBy = "meetingRegister", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttendanceRecordEntity> attendanceRecords = new ArrayList<>();



    }
