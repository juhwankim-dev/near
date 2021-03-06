package com.ssafy.api.service;

import com.ssafy.api.dto.req.BookmarkReqDTO;
import com.ssafy.core.entity.Bookmark;
import com.ssafy.core.entity.Handcontent;
import com.ssafy.core.exception.ApiMessageException;
import com.ssafy.core.repository.BookmarkRepository;
import com.ssafy.core.repository.HandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HandService {
    private HandRepository handRepository;
    private BookmarkRepository bookmarkRepository;


    @Autowired
    public HandService(HandRepository handRepository, BookmarkRepository bookmarkRepository) {
        this.handRepository = handRepository;
        this.bookmarkRepository = bookmarkRepository;
    }

    @Transactional(readOnly = true)
    public List<Handcontent> handList() throws Exception {
        return handRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Handcontent> bookmarkList(Long userId) throws Exception {

        return bookmarkRepository.handContentList(userId);
    }

    @Transactional(readOnly = false)
    public Bookmark save(Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    @Transactional(readOnly = true)
    public Handcontent findById(Long id) throws Exception {
        return handRepository.findById(id).orElseThrow(
                () -> new ApiMessageException("존재하지 않는 수어 입니다."));
    }

    @Transactional(readOnly = false)
    public void delete(Long userId, Long handcontent_key) {
        bookmarkRepository.delete(userId, handcontent_key);
    }

}
