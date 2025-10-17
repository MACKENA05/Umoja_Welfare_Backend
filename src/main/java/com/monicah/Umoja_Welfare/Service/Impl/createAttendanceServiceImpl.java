package com.monicah.Umoja_Welfare.Service.Impl;

import com.monicah.Umoja_Welfare.DTO.BaseApiResponse;
import com.monicah.Umoja_Welfare.DTO.request.AttendanceRecordDTO;
import com.monicah.Umoja_Welfare.DTO.response.AttendanceResponseDTO;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class createAttendanceServiceImpl implements createAttendanceService {
    private final MemberRepository memberRepository;
    private final AttendanceRecordRepository attendanceRecordRepository;
    private final AttendanceDBUtilService attendanceDBUtilService;
    private final MeetingRegisterRepository meetingRegisterRepository;
    private final PenaltyCalculatorServiceImpl penaltyCalculatorService;
    @Override
    public BaseApiResponse createAttendance(AttendanceRecordDTO attendanceRecordDTO) throws Exception {
        log.info("Creating attendance record for member:{}",attendanceRecordDTO.getPhoneNumber(),
                attendanceRecordDTO.getMeetingId());

        var members =memberRepository.findById(attendanceRecordDTO.getMemberId())
                .orElseThrow(() -> new MemberExistException("Member with id is not available " ));


        var meeting = meetingRegisterRepository.findById(attendanceRecordDTO.getMeetingId())
                .orElseThrow(() -> new RegisterExistException("Meeting not found"));
        LocalDate paymentDate = attendanceRecordDTO.getArrivalTime().toLocalDate();

        BaseApiResponse penaltyResponse = penaltyCalculatorService.calculateMonthlyContributionPenalty(paymentDate);
        log.info("Penalty service message: {}", penaltyResponse.getMessage());

        BigDecimal baseAmount = new BigDecimal("1000");
        BigDecimal penalty = BigDecimal.ZERO;
        Object data = penaltyResponse.getData();
        if (data instanceof BigDecimal) {
            penalty = (BigDecimal) data;
        } else if (data != null) {
            try {
                penalty = new BigDecimal(data.toString());
            } catch (NumberFormatException ex) {
                log.warn("Unable to parse penalty amount from response data: {}", data, ex);
                // leave penaltyAmount as ZERO or handle as needed
            }
        }
        var attendanceRecords = AttendanceRecordEntity.builder()
                .attendanceStatus(attendanceRecordDTO.getAttendanceStatus())
                .meetingType(attendanceRecordDTO.getMeetingType())
                .arrivalTime(attendanceRecordDTO.getArrivalTime())
                .penaltyAmount(penalty)
                .monthlyContribution(baseAmount)
                .penaltyPaid(attendanceRecordDTO.isPenaltyPaid())
                .penaltyReason(penalty.compareTo(BigDecimal.ZERO) > 0 ? "Late/Absent" : "On Time")
                .member(members)
                .meetingRegister(meeting)
                .build();
        AttendanceRecordEntity attendRecords = attendanceDBUtilService.registerAttendance(attendanceRecords);

        return new BaseApiResponse(
                true,
                200,
                "Attendance recorded successfully for member with ID: "+attendanceRecordDTO.getMemberId()+" attend successfully",
                null);

    }
    @Override
    public BaseApiResponse attendanceList(AttendanceResponseDTO attendanceResponseDTO) throws Exception {
        List <AttendanceRecordEntity> attandanceList = attendanceRecordRepository.findAll();
        var memberList = attandanceList.stream().map(attendRecordList->{
            AttendanceResponseDTO attendDTO = new AttendanceResponseDTO();
            attendDTO.setId(attendRecordList.getId());
            attendDTO.setFirstName(attendRecordList.getMember().getFirstName());
            attendDTO.setLastName(attendRecordList.getMember().getLastName());
            attendDTO.setArrivalTime(attendRecordList.getArrivalTime());
            attendDTO.setMonthlyContribution(attendRecordList.getMonthlyContribution());
            return attendDTO;
                }).collect(Collectors.toList());
        return BaseApiResponse.builder()
                .data(memberList)
                .status(200)
                .message("Attendance list fetched successfully")
                .build();
    }
//this is DTO Projection via Repository (New Version) simple and clear
    @Override
    public BaseApiResponse attendList() throws Exception {
        List<AttendanceResponseDTO> memberList = attendanceRecordRepository.findAttendanceList();

        return BaseApiResponse.builder()
                .data(memberList)
                .status(200)
                .message("Attendance list fetched successfully")
                .build();
    }



}
