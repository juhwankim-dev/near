package com.ssafy.api.service;

import com.ssafy.core.entity.Fingercontent;
import com.ssafy.core.entity.Handcontent;
import com.ssafy.core.repository.HandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HandService {
    private HandRepository handRepository;

    @Autowired
    public HandService(HandRepository handRepository) {
        this.handRepository = handRepository;
    }


    @Transactional(readOnly = true)
    public List<Handcontent> handList() throws Exception {
        List<Handcontent> result = handRepository.findAll();
        return result;
    }


}
