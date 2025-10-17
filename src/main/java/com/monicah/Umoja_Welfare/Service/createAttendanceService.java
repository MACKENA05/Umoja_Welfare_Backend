package com.monicah.Umoja_Welfare.Service;


import com.monicah.Umoja_Welfare.DTO.BaseApiResponse;
import com.monicah.Umoja_Welfare.DTO.request.AttendanceRecordDTO;
import com.monicah.Umoja_Welfare.DTO.response.AttendanceResponseDTO;

public interface createAttendanceService {
  BaseApiResponse createAttendance (AttendanceRecordDTO attendanceRecordDTO) throws Exception;
  BaseApiResponse attendanceList(AttendanceResponseDTO attendanceResponseDTO) throws Exception;
  BaseApiResponse attendList() throws Exception;


}
