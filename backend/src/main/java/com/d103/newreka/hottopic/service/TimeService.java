package com.d103.newreka.hottopic.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d103.newreka.hottopic.domain.Time;
import com.d103.newreka.hottopic.dto.TimeDto;
import com.d103.newreka.hottopic.repo.TimeRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TimeService {

	private final TimeRepo timeRepo;

	@Transactional
	public void saveTime(TimeDto timeDto) {
		String[] args = timeDto.getTime().split("-");
		LocalDateTime date = (LocalDateTime.of(Integer.parseInt(args[0]), Integer.parseInt(args[1]),
			Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]), 0));
		Time time = Time.builder()
			.timeId(timeDto.getTimeId())
			.time(date)
			.build();
		timeRepo.save(time);
	}

}
