package com.monicah.Umoja_Welfare.Controller;

import com.monicah.Umoja_Welfare.DTO.BaseApiResponse;
import com.monicah.Umoja_Welfare.DTO.request.AttendanceRecordDTO;
import com.monicah.Umoja_Welfare.Service.createAttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
