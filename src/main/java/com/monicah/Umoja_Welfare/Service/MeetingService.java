package com.monicah.Umoja_Welfare.Service;

import com.monicah.Umoja_Welfare.DTO.BaseApiResponse;
import com.monicah.Umoja_Welfare.DTO.request.MeetingRegisterDTO;
public interface MeetingService {
    BaseApiResponse createMeeting (MeetingRegisterDTO meetingRegisterDTO) throws Exception;

}
