package com.monicah.Umoja_Welfare.Utils.DBUtilService;

import com.monicah.Umoja_Welfare.Entity.MeetingRegisterEntity;
import com.monicah.Umoja_Welfare.Repository.MeetingRegisterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class MeetingDBUtilService {
   // @Autowired
   private final MeetingRegisterRepository meetingRegisterRepository;
    public MeetingRegisterEntity CreateMeeting (MeetingRegisterEntity meetingRegisterEntity){
        log.info("Saving meeting: {}", meetingRegisterEntity);
        return meetingRegisterRepository.save(meetingRegisterEntity);
    }
    public List<MeetingRegisterEntity> searchByDate (LocalDate meetingDate) {
        if (meetingDate == null) {
            throw new IllegalArgumentException("Meeting date cannot be null.");
        }
        log.debug("Searching for meetings on date: {}", meetingDate);
        return meetingRegisterRepository.findAllByMeetingDate(meetingDate);
    }
}
