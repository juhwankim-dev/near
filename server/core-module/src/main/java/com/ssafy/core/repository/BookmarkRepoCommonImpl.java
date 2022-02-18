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
public class BookmarkRepoCommonImpl implements BookmarkRepoCommon {
    private final JPAQueryFactory queryFactory;
    private EntityManager em;

    @Autowired
    public BookmarkRepoCommonImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.em = em;
    }


    @Override
    public void delete(Long userId, Long handcontent_key) {
        queryFactory.delete(QBookmark.bookmark)
                .where(QBookmark.bookmark.user.id.eq(userId)
                        .and(QBookmark.bookmark.handcontent.handcontent_key.eq(handcontent_key)))
                .execute();
    }

    @Override
    public List<Handcontent> handContentList(Long userId) {
        List<Handcontent> result = queryFactory.selectFrom(QHandcontent.handcontent)
                .innerJoin(QBookmark.bookmark)
                .on(QBookmark.bookmark.handcontent.handcontent_key.eq(QHandcontent.handcontent.handcontent_key))
                .where(QBookmark.bookmark.user.id.eq(userId))
                .fetch();

        return result;
    }
}
