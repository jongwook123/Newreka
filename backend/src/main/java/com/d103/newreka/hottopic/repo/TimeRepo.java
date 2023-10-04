package com.d103.newreka.hottopic.repo;

import com.d103.newreka.hottopic.domain.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TimeRepo extends JpaRepository<Time, Long> {

    Time findByTime(LocalDateTime time);
}
