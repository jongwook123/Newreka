package com.d103.newreka.hottopic.service;

import com.d103.newreka.hottopic.dto.TimeDto;
import com.d103.newreka.hottopic.repo.TimeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TimeService {

    private final TimeRepo timeRepo;

    public void saveTime(TimeDto timeDto){

    }

}
