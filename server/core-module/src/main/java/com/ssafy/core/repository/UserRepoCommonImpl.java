package com.ssafy.core.repository;

import com.ssafy.core.entity.QUser;
import com.ssafy.core.code.JoinCode;
import com.ssafy.core.code.YNCode;
import com.ssafy.core.entity.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserRepoCommonImpl implements UserRepoCommon{
    private final JPAQueryFactory queryFactory;
    private EntityManager em;

    public UserRepoCommonImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.em = em;
    }


    @Override
    public User findUserLogin(String uid, JoinCode type){
        User result = queryFactory
                .select(QUser.user)
                .from(QUser.user)
                .where(QUser.user.uid.eq(uid), QUser.user.joinType.eq(type), QUser.user.isBind.eq(YNCode.Y))
                .fetchFirst();

        return result;
    }


    @Override
    public User findByUid(String uid, YNCode isBind){
        User result = queryFactory
                .select(QUser.user)
                .from(QUser.user)
                .where(QUser.user.uid.eq(uid), checkUserIsBind(isBind))
                .fetchOne();

        return result;
    }

    @Override
    public User findByEmail(String email) {
        User result = queryFactory
                .select(QUser.user)
                .from(QUser.user)
                .where(QUser.user.email.eq(email))
                .fetchOne();
        return result;
    }

    @Override
    public User findByNickname(String nickname) {
        User result = queryFactory
                .select(QUser.user)
                .from(QUser.user)
                .where(QUser.user.nickname.eq(nickname))
                .fetchOne();
        return result;
    }

    @Override
    public long updatePassword(long id, String password) {

        long cnt = queryFactory.update(QUser.user)
                .where(QUser.user.id.eq(id))
                .set(QUser.user.password, password)
                .execute();

        return cnt;
    }

    @Override
    public long updateAddress(long id, String address, String addressDetail) {
        long cnt = queryFactory.update(QUser.user)
                .where(QUser.user.id.eq(id))
                .set(QUser.user.address, address)
                .execute();

        cnt += queryFactory.update(QUser.user)
                .where(QUser.user.id.eq(id))
                .set(QUser.user.addressDetail,addressDetail)
                .execute();

        return cnt;
    }

    @Override
    public long updatePhone(long id, String phone) {
        long cnt = queryFactory.update(QUser.user)
                .where(QUser.user.id.eq(id))
                .set(QUser.user.phone, phone)
                .execute();

        return cnt;
    }

    @Override
    public long updateNickname(long id, String nicknmae) {
        long cnt = queryFactory.update(QUser.user)
                .where(QUser.user.id.eq(id))
                .set(QUser.user.nickname, nicknmae)
                .execute();
        return cnt;
    }


    // isBind 조건만 체크
    public BooleanExpression checkUserIsBind(YNCode isBind){
        if(isBind == null)
            return null;

        return QUser.user.isBind.eq(isBind);
    }

}





