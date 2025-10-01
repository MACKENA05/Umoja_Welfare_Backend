package com.monicah.Umoja_Welfare.Repository;

import com.monicah.Umoja_Welfare.Entity.MeetingRegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository

public interface MeetingRegisterRepository extends JpaRepository<MeetingRegisterEntity, Long>{
  //Optional<MeetingRegisterEntity> findByMeetingDate (LocalDate meetingDate);
    List<MeetingRegisterEntity> findAllByMeetingDate(LocalDate meetingDate);

  Optional<MeetingRegisterEntity> findById(Long id);
  //@Query("SELECT m FROM MeetingRegisterEntity m WHERE DATE(m.meetingDate) = :meetingDate")
  //List<MeetingRegisterEntity> findAllByMeetingDate(@Param("meetingDate") LocalDate meetingDate);

}
