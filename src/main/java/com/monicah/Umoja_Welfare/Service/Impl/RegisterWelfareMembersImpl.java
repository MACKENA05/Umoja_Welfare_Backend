package com.monicah.Umoja_Welfare.Service.Impl;

import com.monicah.Umoja_Welfare.DTO.BaseApiResponse;
import com.monicah.Umoja_Welfare.DTO.request.MemberDTO;
import com.monicah.Umoja_Welfare.DTO.response.MemberResponseDTO;
import com.monicah.Umoja_Welfare.Entity.MemberEntity;
import com.monicah.Umoja_Welfare.Exceptions.MemberExistException;
import com.monicah.Umoja_Welfare.Repository.MemberRepository;
import com.monicah.Umoja_Welfare.Service.RegisterWelfareMembersService;
import com.monicah.Umoja_Welfare.Utils.DBUtilService.MemberDBUtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class RegisterWelfareMembersImpl implements RegisterWelfareMembersService {
    @Autowired
    private final MemberDBUtilService memberDBUtilService;
    private final MemberRepository memberRepository;
    @Override
    public BaseApiResponse RegisterMembers (MemberDTO memberDTO) throws Exception {
        //var checkMember = memberDBUtilService.checkByPhone(memberDTO.getPhoneNumber());
        var checkMember = memberDBUtilService.checkByPhoneOrEmail(memberDTO.getPhoneNumber(), memberDTO.getEmail());
        if (checkMember.isPresent()){
            log.info("Member already exists ");
            //throw new MemberExistException("member with the number Exist!");
            return BaseApiResponse.builder()
                    .data(null)
                    .status(404)
                    .message("Error!")
                    .errors(" Member with this phone or email already exists!")
                    //.errors("member with Phone No: " + memberDTO.getPhoneNumber()+ "exists!")
                    .build();
        }
        var member = MemberEntity.builder()
                .firstName(memberDTO.getFirstName())
                .lastName(memberDTO.getLastName())
                .email(memberDTO.getEmail())
                .phoneNumber(memberDTO.getPhoneNumber())
                .totalContributions(memberDTO.getTotalContributions())
                .createdAt(memberDTO.getCreatedAt())
                .dateJoined(memberDTO.getDateJoined())
                .membershipNumber(memberDTO.getMembershipNumber())
                .status(memberDTO.getStatus())
                .build();
        MemberEntity saveMembers = memberDBUtilService.RegisterMember(member);
        return new BaseApiResponse(member, 200, "Member with No: "+memberDTO.getPhoneNumber()+" registered successfully", null);


    }

    @Override
    public BaseApiResponse UpdateMembers(String phoneNumber, MemberDTO memberDTO) {

        MemberEntity memberEntity = memberRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new MemberExistException("Member not found!"));

        if (memberEntity ==null) {
            log.info("Member does not exists ");
            //throw new MemberExistException("member with the number Exist!");
            return BaseApiResponse.builder()
                    .data(null)
                    .status(404)
                    .message("Error!")
                    .errors(" Member with this phone does not exists!")
                    .build();
        }
        memberEntity.setFirstName(memberDTO.getFirstName());
        memberEntity.setLastName(memberDTO.getLastName());
        memberEntity.setUpdatedAt(LocalDateTime.now());
        memberEntity.setEmail(memberDTO.getEmail());
        MemberEntity editMemberEntity = memberRepository.save(memberEntity);
        return new BaseApiResponse(true,
                200,
                "Member details updated successfully",
                editMemberEntity);

    }
    @Override
    public BaseApiResponse memberList() throws Exception {
        List<MemberEntity> members = memberRepository.findAll();
        var memberDetails = members.stream().map(memberEntity ->{
               MemberResponseDTO memberResponseDTO = new MemberResponseDTO();
                 memberResponseDTO.setFirstName(memberEntity.getFirstName());
                 memberResponseDTO.setLastName(memberEntity.getLastName());
                 memberResponseDTO.setEmail(memberEntity.getEmail());
                 memberResponseDTO.setDateJoined(memberEntity.getDateJoined());
                 memberResponseDTO.setTotalContributions(memberEntity.getTotalContributions());
                 memberResponseDTO.setPhoneNumber(memberEntity.getPhoneNumber());
                 memberResponseDTO.setMembershipNumber(memberEntity.getMembershipNumber());

                    return memberResponseDTO;
                }
                ).toList();
        return new BaseApiResponse(memberDetails,
                200,
                "Members fetched successfully",
                null);

    }

}
