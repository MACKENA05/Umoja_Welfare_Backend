package com.monicah.Umoja_Welfare.DTO.request;

import com.monicah.Umoja_Welfare.Entity.MeetingType;
import com.monicah.Umoja_Welfare.Entity.MemberStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MeetingRegisterDTO implements Serializable {
    private Long id;
    private LocalDate meetingDate;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Meeting Type is required")
    private MeetingType meetingType = MeetingType.MONTHLY;
    @NotNull(message = "Provide meeting notes")
    private String meetingNotes;
}
