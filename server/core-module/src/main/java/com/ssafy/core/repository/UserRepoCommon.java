package com.ssafy.core.repository;

import com.ssafy.core.code.JoinCode;
import com.ssafy.core.code.YNCode;
import com.ssafy.core.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepoCommon {
    User findUserLogin(String uid, JoinCode type);

    User findByUid(String uid, YNCode isBind);

}






































