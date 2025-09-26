package com.monicah.Umoja_Welfare.Controller;

import com.monicah.Umoja_Welfare.DTO.BaseApiResponse;
import com.monicah.Umoja_Welfare.DTO.request.MeetingRegisterDTO;
import com.monicah.Umoja_Welfare.DTO.request.MemberDTO;
import com.monicah.Umoja_Welfare.Service.MeetingService;
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
public class MeetingRegisterController {
    @Autowired
      public MeetingService meetingService;
    @PostMapping("/create_Meeting")
    public BaseApiResponse Meeting (@RequestBody @Valid MeetingRegisterDTO meetingRegisterDTO) throws Exception{
        return meetingService.createMeeting(meetingRegisterDTO);

    }

}
