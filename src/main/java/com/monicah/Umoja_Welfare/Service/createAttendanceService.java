package com.monicah.Umoja_Welfare.Service;


import com.monicah.Umoja_Welfare.DTO.BaseApiResponse;
import com.monicah.Umoja_Welfare.DTO.request.AttendanceRecordDTO;

public interface createAttendanceService {
  BaseApiResponse createAttendance (AttendanceRecordDTO attendanceRecordDTO) throws Exception;

}
