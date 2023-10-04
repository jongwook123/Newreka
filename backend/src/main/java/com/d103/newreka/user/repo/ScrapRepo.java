package com.d103.newreka.user.repo;

import com.d103.newreka.user.domain.Scrap;
import com.d103.newreka.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapRepo extends JpaRepository<Scrap, User> {
    List<Scrap> findByUserId(User user);
}