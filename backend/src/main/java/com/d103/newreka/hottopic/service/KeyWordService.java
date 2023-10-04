package com.d103.newreka.hottopic.service;

import com.d103.newreka.hottopic.domain.KeyWord;
import com.d103.newreka.hottopic.domain.Time;
import com.d103.newreka.hottopic.dto.KeyWordDto;
import com.d103.newreka.hottopic.repo.KeyWordRepo;
import com.d103.newreka.hottopic.repo.TimeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KeyWordService {

    private final KeyWordRepo keyWordRepo;
    private final TimeRepo timeRepo;

    @Transactional
    public void saveKeyWord(KeyWordDto keyWordDto) {
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

    public List<KeyWordDto> getKeyWordList() {
        List<KeyWord> keyWords = keyWordRepo.findTop10ByOrderByKeyWordIdDesc();
        List<KeyWordDto> keyWordsDtos = keyWords.stream()
                .map(KeyWordDto::fromEntity)
                .collect(Collectors.toList());
        return keyWordsDtos;
    }

    /**
     * 시간을 기준으로 키워드 검색후 반환
     */
    public List<KeyWordDto> getKeyWordListWithTime(String reqTime) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        LocalDateTime dateTime = LocalDateTime.parse(reqTime, formatter);

        Time timeEntity = timeRepo.findByTime(dateTime);

        if (timeEntity == null) {
            throw new RuntimeException("해당하는 시간의 데이터가 없습니다.");
        }

        return timeEntity.getKeyWords().stream()
                .map(KeyWordDto::fromEntity)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    Collections.reverse(list);
                    return list;
                }));
    }
}
