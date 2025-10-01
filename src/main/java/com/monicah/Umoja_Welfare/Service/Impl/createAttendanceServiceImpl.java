package com.monicah.Umoja_Welfare.Service.Impl;

import com.monicah.Umoja_Welfare.DTO.BaseApiResponse;
import com.monicah.Umoja_Welfare.DTO.request.AttendanceRecordDTO;
import com.monicah.Umoja_Welfare.Entity.AttendanceRecordEntity;
import com.monicah.Umoja_Welfare.Exceptions.MemberExistException;
import com.monicah.Umoja_Welfare.Exceptions.RegisterExistException;
import com.monicah.Umoja_Welfare.Repository.AttendanceRecordRepository;
import com.monicah.Umoja_Welfare.Repository.MeetingRegisterRepository;
import com.monicah.Umoja_Welfare.Repository.MemberRepository;
import com.monicah.Umoja_Welfare.Service.createAttendanceService;
import com.monicah.Umoja_Welfare.Utils.DBUtilService.AttendanceDBUtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class createAttendanceServiceImpl implements createAttendanceService {
    private final MemberRepository memberRepository;
    private final AttendanceRecordRepository attendanceRecordRepository;
    private final AttendanceDBUtilService attendanceDBUtilService;
    private final MeetingRegisterRepository meetingRegisterRepository;
    @Override
    public BaseApiResponse createAttendance(AttendanceRecordDTO attendanceRecordDTO) throws Exception {
        log.info("Creating attendance record for member: {}",attendanceRecordDTO.getPhoneNumber(),
                attendanceRecordDTO.getMeetingId());

        var members =memberRepository.findById(attendanceRecordDTO.getMemberId())
                .orElseThrow(() -> new MemberExistException("Member with id is not available " ));


        var meeting = meetingRegisterRepository.findById(attendanceRecordDTO.getMeetingId())
                .orElseThrow(() -> new RegisterExistException("Meeting not found"));

        var attendanceRecords = AttendanceRecordEntity.builder()
                .attendanceStatus(attendanceRecordDTO.getAttendanceStatus())
                .meetingType(attendanceRecordDTO.getMeetingType())
                .arrivalTime(attendanceRecordDTO.getArrivalTime())

               // .penaltyAmount(penalty)
                .penaltyPaid(attendanceRecordDTO.isPenaltyPaid())
                //.penaltyReason(penalty.compareTo(BigDecimal.ZERO) > 0 ? "Late/Absent" : null)
                .member(members)
                .meetingRegister(meeting)
                .build();
        AttendanceRecordEntity attendRecords = attendanceDBUtilService.registerAttendance(attendanceRecords);
        //log.info("Attendance recorded successfully for member {}", member.getFullName());

        return new BaseApiResponse(true,
                200,
                "Attendance recorded successfully for: "+attendanceRecordDTO.getPhoneNumber()+" attend successfully",
                null);

    }

}
