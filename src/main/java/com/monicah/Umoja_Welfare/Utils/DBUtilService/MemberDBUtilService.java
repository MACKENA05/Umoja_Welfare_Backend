package com.monicah.Umoja_Welfare.Utils.DBUtilService;

import com.monicah.Umoja_Welfare.Entity.MemberEntity;
import com.monicah.Umoja_Welfare.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class MemberDBUtilService {
    @Autowired
    private MemberRepository memberRepository;
    public MemberEntity RegisterMember (MemberEntity memberEntity) {
        return memberRepository.save(memberEntity);
    }
    public Optional <MemberEntity> checkByPhone (String phoneNumber){
        return memberRepository.findByPhoneNumber(phoneNumber);
    }
    public Optional<MemberEntity> checkByPhoneOrEmail(String phoneNumber, String email) {
        return memberRepository.findByPhoneNumberOrEmail(phoneNumber, email);
    }



}
