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
        return (userRepository.updateAddress(id, address, addressDetail) > 1) ? true : false;
    }

    @Transactional(readOnly = false)
    public boolean updatePassword(long id, String password) {
        return (userRepository.updatePassword(id, password) > 0)  ? true : false;
    }


    @Transactional(readOnly = false)
    public boolean updatePhone(long id, String phone) {
        return (userRepository.updatePhone(id, phone) > 0)  ? true : false;
    }

    @Transactional(readOnly = false)
    public boolean updateNickname(long id, String nickname){
        return (userRepository.updateNickname(id, nickname) > 0 ? true : false);
    }

    @Transactional(readOnly = false)
    public boolean updateEmail(long id, String email){
        return (userRepository.updateEmail(id, email) > 0 ? true : false);
    }

    @Transactional(readOnly = false)
    public boolean updateUser(long id, ModifyUserReqDTO user){
        long cnt = 0;
        cnt += userRepository.updatePassword(id, user.getPassword())
                +  userRepository.updateNickname(id, user.getNickname())
                + userRepository.updateEmail(id, user.getEmail());

        return cnt >= 3 ? true : false;
    }
}
