package com.d103.newreka.hottopic.service;

import com.d103.newreka.hottopic.domain.KeyWord;
import com.d103.newreka.hottopic.domain.Time;
import com.d103.newreka.hottopic.dto.KeyWordDto;
import com.d103.newreka.hottopic.repo.KeyWordRepo;
import com.d103.newreka.hottopic.repo.TimeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KeyWordService {

    private final KeyWordRepo keyWordRepo;
    private final TimeRepo timeRepo;

    @Transactional
    public void saveKeyWord(KeyWordDto keyWordDto){
        Time time = timeRepo.getReferenceById(keyWordDto.getTimeId());
        KeyWord keyWord = KeyWord.builder()
                .keyWordId(keyWordDto.getKeyWordId())
                .name(keyWordDto.getName())
                .summary(keyWordDto.getSummary())
                .category(keyWordDto.getCategory())
                .time(time)
                .build();
        keyWordRepo.save(keyWord);
    }
    @Transactional
    public List<KeyWord> getKeyWordList(){return keyWordRepo.findAllByOrderByTime_TimeIdDesc();}

}
