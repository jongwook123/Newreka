package com.d103.newreka.user.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.d103.newreka.user.domain.Scrap;

@Repository
public interface ScrapRepo extends JpaRepository<Scrap, Long> {
	List<Scrap> findByUserId_Id(Long id);
}
