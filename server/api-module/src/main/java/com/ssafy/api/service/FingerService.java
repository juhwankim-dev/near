package com.ssafy.api.service;

import com.ssafy.core.entity.Fingercontent;
import com.ssafy.core.exception.ApiMessageException;
import com.ssafy.core.repository.FingerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FingerService {
    private FingerRepository fingerRepository;

    @Autowired
    public FingerService(FingerRepository fingerRepository) {
        this.fingerRepository = fingerRepository;
    }

    @Transactional(readOnly = true)
    public List<Fingercontent> fingerList() throws Exception {

        List<Fingercontent> result = fingerRepository.findAll();

        return result;
    }

    @Transactional(readOnly = true)
    public Fingercontent fingerDetail(long id) throws Exception {
        return fingerRepository.findById(id).orElseThrow(() -> new ApiMessageException(("존재 하지 않는 지문자 입니다")));
    }

}
