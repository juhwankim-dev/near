package com.ssafy.core.repository;

import com.ssafy.core.entity.Fingercontent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FingerRepository extends JpaRepository<Fingercontent, Long>, FingerRepoCommon {
}
