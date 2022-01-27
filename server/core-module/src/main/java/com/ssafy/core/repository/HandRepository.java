package com.ssafy.core.repository;

import com.ssafy.core.entity.Handcontent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HandRepository extends JpaRepository<Handcontent, Long> {

}
