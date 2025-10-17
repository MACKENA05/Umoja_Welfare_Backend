package com.monicah.Umoja_Welfare.Controller;

import com.monicah.Umoja_Welfare.DTO.BaseApiResponse;
import com.monicah.Umoja_Welfare.DTO.request.AttendanceRecordDTO;
import com.monicah.Umoja_Welfare.DTO.response.AttendanceResponseDTO;
import com.monicah.Umoja_Welfare.Service.createAttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path= "api/v1/auth")
public class AttendanceRecordsController {
    @Autowired
    public createAttendanceService attendanceService;
    @PostMapping("/attendance_Meeting")
    public BaseApiResponse attendanceRecords(@Valid @RequestBody AttendanceRecordDTO attendanceRecordDTO) throws Exception{
        return attendanceService.createAttendance(attendanceRecordDTO);
    }
    @GetMapping("/getList")
    public BaseApiResponse attendanceList(AttendanceResponseDTO attendanceResponseDTO) throws Exception{
        return attendanceService.attendanceList(attendanceResponseDTO);
    }
    //this does not map any
    @GetMapping("/list")
    public BaseApiResponse getAttendList() throws Exception{
        return attendanceService.attendList();
    }
}
