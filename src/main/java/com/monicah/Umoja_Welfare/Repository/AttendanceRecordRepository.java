package com.monicah.Umoja_Welfare.Repository;

import com.monicah.Umoja_Welfare.DTO.response.AttendanceResponseDTO;
import com.monicah.Umoja_Welfare.Entity.AttendanceRecordEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecordEntity, Long> {
// this is done thriugh mapping manually
    List<AttendanceRecordEntity> findAll();
// this one does not

    @Query("SELECT new com.monicah.Umoja_Welfare.DTO.response.AttendanceResponseDTO(" +
            "a.id, m.firstName, m.lastName, a.arrivalTime, a.monthlyContribution) " +
            "FROM AttendanceRecordEntity a JOIN a.member m")
    List<AttendanceResponseDTO> findAttendanceList();


}
