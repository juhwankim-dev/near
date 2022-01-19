package com.ssafy.api.service;

import com.ssafy.core.code.JoinCode;
import com.ssafy.core.code.YNCode;
import com.ssafy.core.entity.User;
import com.ssafy.core.exception.ApiMessageException;
import com.ssafy.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SignService {
    private final static Logger LOG = Logger.getGlobal();
    private final UserRepository userRepository;


    /**
     * id로 회원정보 조회
     *
     * @param id
     * @return
     * @throws Exception
     */
    public User findUserById(long id) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new ApiMessageException("존재하지 않는 회원정보입니다."));
        return user;
    }

    /**
     * uid로 user 조회
     *
     * @param uid
     * @return
     * @throws Exception
     */
    public User findByUid(String uid, YNCode isBind) throws Exception {
        return userRepository.findByUid(uid, isBind);
    }

    /**
     * 회원가입 후 userId 리턴
     *
     * @param user
     * @return
     */
    @Transactional(readOnly = false)
    public long userSignUp(User user) {
        User signUpUser = userRepository.save(user);
        return signUpUser.getId();
    }

    /**
     * uid, type으로 회원정보 조회
     *
     * @param uid
     * @param type
     * @return
     */
    public User findUserByUidType(String uid, JoinCode type) {
        return userRepository.findUserLogin(uid, type);
    }

    /**
     * 회원 엔티티 저장
     *
     * @param user
     */
    @Transactional(readOnly = false)
    public void saveUser(User user) {
        userRepository.save(user);
    }


    /** 2022-01-19 by 김예진
    *  @brief 입력한 회원 탈퇴 처리를 하는 메소드

    *  @date 2022-01-19

    *  @return

    *  @param user

    */
    public void resign(User user) {
        LOG.info(user.getRoles().toString());
//        userRepository.delete(user);
    }

}
