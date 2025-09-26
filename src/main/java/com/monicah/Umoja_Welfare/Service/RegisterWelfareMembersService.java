package com.monicah.Umoja_Welfare.Service;

import com.monicah.Umoja_Welfare.DTO.BaseApiResponse;
import com.monicah.Umoja_Welfare.DTO.request.MemberDTO;

public interface RegisterWelfareMembersService {
    BaseApiResponse RegisterMembers (MemberDTO memberDTO) throws Exception;
    BaseApiResponse UpdateMembers( String phoneNumber, MemberDTO memberDTO) throws Exception;
    BaseApiResponse memberList() throws Exception;
    //BaseApiResponse deactivateMembers( String phoneNumber, MemberDTO memberDTO) throws Exception;

}
