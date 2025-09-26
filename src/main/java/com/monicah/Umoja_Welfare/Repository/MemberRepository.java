package com.monicah.Umoja_Welfare.Repository;

import com.monicah.Umoja_Welfare.Entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByPhoneNumber(String phoneNumber);
    Optional<MemberEntity> findByPhoneNumberOrEmail(String phoneNumber, String email);
    List<MemberEntity> findAll();



}
