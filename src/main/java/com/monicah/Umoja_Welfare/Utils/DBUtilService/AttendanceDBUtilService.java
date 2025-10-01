package com.monicah.Umoja_Welfare.Utils.DBUtilService;

import com.monicah.Umoja_Welfare.Entity.AttendanceRecordEntity;
import com.monicah.Umoja_Welfare.Repository.AttendanceRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AttendanceDBUtilService {
    private final AttendanceRecordRepository attendanceRecordRepository;

public AttendanceRecordEntity registerAttendance( AttendanceRecordEntity attendanceRecordEntity){
    log.info("Saving attendance:{}",attendanceRecordEntity);
    return attendanceRecordRepository.save(attendanceRecordEntity);

}


}
