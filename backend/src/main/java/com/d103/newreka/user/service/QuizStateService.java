package com.d103.newreka.user.service;

import com.d103.newreka.hottopic.repo.KeyWordRepo;
import com.d103.newreka.quiz.repo.QuizRepo;
import com.d103.newreka.user.domain.QuizState;
import com.d103.newreka.user.domain.User;
import com.d103.newreka.user.dto.QuizStateDto;
import com.d103.newreka.user.repo.QuizStateRepo;
import com.d103.newreka.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuizStateService {

    private final QuizStateRepo quizStateRepo;
    private final UserRepository userRepository;
    private final KeyWordRepo keyWordRepo;
    @Transactional
    public void saveQuizState(QuizStateDto quizStateDto){
        QuizState quizState = QuizState.builder()
                .stateId(quizStateDto.getStateId())
                .category(quizStateDto.getCategory())
                .createTime(quizStateDto.getCreateTime())
                .user(userRepository.getReferenceById(quizStateDto.getUser()))
                .keyWord(keyWordRepo.getReferenceById(quizStateDto.getKeyWord()))
                .build();
        quizStateRepo.save(quizState);
    }

    @Transactional
    public HashMap<String,Integer> myKeyWord(User user){

        HashMap<String,Integer> keyWordList = new HashMap<>();

        System.out.println("잘 들어는 옴");

        List<QuizState> list = quizStateRepo.findByUser_IdAndCategory(user.getId(),"사회");
        keyWordList.put("사회",list.size());
        list = quizStateRepo.findByUser_IdAndCategory(user.getId(),"경제");
        keyWordList.put("경제",list.size());
        list = quizStateRepo.findByUser_IdAndCategory(user.getId(),"정치");
        keyWordList.put("정치",list.size());
        list = quizStateRepo.findByUser_IdAndCategory(user.getId(),"세계");
        keyWordList.put("세계",list.size());
        list = quizStateRepo.findByUser_IdAndCategory(user.getId(),"IT");
        keyWordList.put("IT",list.size());
        list = quizStateRepo.findByUser_IdAndCategory(user.getId(),"생활");
        keyWordList.put("생활",list.size());
        list = quizStateRepo.findByUser_IdAndCategory(user.getId(),"오피니언");
        keyWordList.put("오피니언",list.size());

        System.out.println("실행 잘 됨");

        return keyWordList;
    }
    //사회, 경제, 정치, 세계, IT, 생활, 오피니언
}
