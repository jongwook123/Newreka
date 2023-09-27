package com.d103.newreka.user.repo;

import com.d103.newreka.user.domain.Scrap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapRepo extends JpaRepository<Scrap,Long> {
}
