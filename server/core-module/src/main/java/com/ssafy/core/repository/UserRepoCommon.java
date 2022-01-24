package com.ssafy.core.repository;

import com.ssafy.core.code.JoinCode;
import com.ssafy.core.code.YNCode;
import com.ssafy.core.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepoCommon {
    User findUserLogin(String uid, JoinCode type);

    User findByUid(String uid, YNCode isBind);

    User findByEmail(String email);

    User findByNickname(String nickname);

    long updatePassword(long id, String password);

    long updateAddress(long id, String address, String addressDetail);

    long updatePhone(long id, String phone);

    long updateNickname(long id, String nicknmae);

}






































