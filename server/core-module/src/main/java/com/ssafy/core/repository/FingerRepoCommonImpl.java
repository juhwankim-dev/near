package com.ssafy.core.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
@Transactional
public class FingerRepoCommonImpl implements FingerRepoCommon {
    private final JPAQueryFactory queryFactory;
    private EntityManager em;

    public FingerRepoCommonImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.em = em;
    }

}
