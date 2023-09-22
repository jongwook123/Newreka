package com.d103.newreka.hottopic.service;

import com.d103.newreka.hottopic.repo.KeyWordRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KeyWordService {

    private final KeyWordRepo keyWordRepo;


}
