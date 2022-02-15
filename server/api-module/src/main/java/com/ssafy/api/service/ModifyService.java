package com.ssafy.api.service;

import com.ssafy.api.dto.req.ModifyUserReqDTO;
import com.ssafy.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ModifyService {
    private final UserRepository userRepository;

    @Transactional(readOnly = false)
    public boolean updateAddress(long id, String address, String addressDetail) {
        return userRepository.updateAddress(id, address, addressDetail) > 1;
    }

    @Transactional(readOnly = false)
    public boolean updatePassword(long id, String password) {
        return userRepository.updatePassword(id, password) > 0;
    }


    @Transactional(readOnly = false)
    public boolean updatePhone(long id, String phone) {
        return userRepository.updatePhone(id, phone) > 0;
    }

    @Transactional(readOnly = false)
    public boolean updateNickname(long id, String nickname) {
        return (userRepository.updateNickname(id, nickname) > 0);
    }

    @Transactional(readOnly = false)
    public boolean updateEmail(long id, String email) {
        return (userRepository.updateEmail(id, email) > 0);
    }

    @Transactional(readOnly = false)
    public boolean updateUser(long id, ModifyUserReqDTO user) {
        if (userRepository.updatePassword(id, user.getPassword()) > 0
                && userRepository.updateNickname(id, user.getNickname()) > 0
                && userRepository.updateEmail(id, user.getEmail()) > 0) {
            return true;
        }
        return false;
    }
}
