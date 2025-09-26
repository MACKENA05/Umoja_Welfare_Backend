package com.monicah.Umoja_Welfare.Service.Impl;

import com.monicah.Umoja_Welfare.DTO.BaseApiResponse;
import com.monicah.Umoja_Welfare.DTO.request.MeetingRegisterDTO;
import com.monicah.Umoja_Welfare.DTO.request.MemberDTO;
import com.monicah.Umoja_Welfare.Entity.MeetingRegisterEntity;
import com.monicah.Umoja_Welfare.Repository.MeetingRegisterRepository;
import com.monicah.Umoja_Welfare.Service.MeetingService;
import com.monicah.Umoja_Welfare.Utils.DBUtilService.MeetingDBUtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRegisterRepository meetingRegisterRepository;
    private final MeetingDBUtilService meetingDBUtilService;
    @Override
    public BaseApiResponse createMeeting(MeetingRegisterDTO meetingRegisterDTO) throws Exception {
        LocalDate generatedDate = LocalDate.now();
        log.info("Checking for existing meetings on generated date: {}", generatedDate);
        List<MeetingRegisterEntity> existingMeetings =
                meetingDBUtilService.searchByDate(generatedDate);

        if (!existingMeetings.isEmpty()){
             log.warn("Meeting creation failed - Meeting already exists on {}", generatedDate);

            return BaseApiResponse.builder()
                    .data(null)
                    .status(409)
                    .message("Meeting creation failed!")
                    .errors(" A meeting with the same date already exists!")
                    .build();
        }
        var  createMeetings = MeetingRegisterEntity.builder()
               // .meetingDate(meetingRegisterDTO.getMeetingDate())
                .meetingDate(LocalDate.now())
                .meetingType(meetingRegisterDTO.getMeetingType())
                .meetingNotes(meetingRegisterDTO.getMeetingNotes())
                .build();
  MeetingRegisterEntity meeting = meetingDBUtilService.CreateMeeting(createMeetings);
  return new BaseApiResponse(createMeetings, 200, "Meeting created successfully", null);
    }

}
