package com.ssafy.core.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.core.entity.Handcontent;
import com.ssafy.core.entity.QBookmark;
import com.ssafy.core.entity.QHandcontent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class HandRepoCommonImpl implements HandRepoCommon{
    private final JPAQueryFactory queryFactory;
    private EntityManager em;

    public HandRepoCommonImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.em = em;
    }


}
