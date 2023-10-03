package com.d103.newreka.hottopic.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d103.newreka.hottopic.domain.KeyWord;
import com.d103.newreka.hottopic.domain.Time;
import com.d103.newreka.hottopic.dto.KeyWordDto;
import com.d103.newreka.hottopic.repo.KeyWordRepo;
import com.d103.newreka.hottopic.repo.TimeRepo;

import lombok.RequiredArgsConstructor;

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

	@Transactional
	public List<KeyWordDto> getKeyWordList() {
		List<KeyWord> keyWords = keyWordRepo.findTop10ByOrderByKeyWordIdDesc();
		List<KeyWordDto> keyWordsDtos = keyWords.stream()
			.map(KeyWordDto::fromEntity)
			.collect(Collectors.toList());
		return keyWordsDtos;
	}

}
