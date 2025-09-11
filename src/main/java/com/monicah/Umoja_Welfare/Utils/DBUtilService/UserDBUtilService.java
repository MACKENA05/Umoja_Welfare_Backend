package com.monicah.Umoja_Welfare.Utils.DBUtilService;

import com.monicah.Umoja_Welfare.Entity.UserEntity;
import com.monicah.Umoja_Welfare.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
@AllArgsConstructor
@Service

public class UserDBUtilService {
    private final UserRepository userRepository;

    public Optional<UserEntity> checkUserEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
