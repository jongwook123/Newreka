package com.d103.newreka.user.repo;

import com.d103.newreka.user.domain.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapRepo extends JpaRepository<Scrap, Long> {
    List<Scrap> findByUser_Id(Long userId);
}