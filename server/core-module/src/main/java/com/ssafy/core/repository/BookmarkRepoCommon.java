package com.ssafy.core.repository;

import com.ssafy.core.entity.Bookmark;
import com.ssafy.core.entity.Handcontent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepoCommon {

    void delete(Long userId, Long handcontent_key);

    List<Handcontent> handContentList (Long userId);

}
