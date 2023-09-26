package com.d103.newreka.hottopic.repo;

import com.d103.newreka.hottopic.domain.KeyWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyWordRepo extends JpaRepository<KeyWord, Long> {

//    KeyWord findById(Long id);
}
