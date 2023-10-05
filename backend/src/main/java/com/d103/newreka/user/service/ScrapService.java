package com.d103.newreka.user.service;

import com.d103.newreka.hottopic.domain.Article;
import com.d103.newreka.hottopic.domain.KeyWord;
import com.d103.newreka.hottopic.repo.ArticleRepo;
import com.d103.newreka.hottopic.repo.KeyWordRepo;
import com.d103.newreka.user.domain.Scrap;
import com.d103.newreka.user.domain.User;
import com.d103.newreka.user.dto.ScrapDto;
import com.d103.newreka.user.repo.ScrapRepo;
import com.d103.newreka.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScrapService {
    private final ScrapRepo scrapRepo;
    private final UserRepository userRepository;
    private final KeyWordRepo keyWordRepo;
    private final ArticleRepo articleRepo;

    @Transactional
    public void saveScrap(ScrapDto scrapDto, User user) {
        KeyWord keyWord = keyWordRepo.getReferenceById(scrapDto.getKeyWord());
        // User user = userRepository.getReferenceById(scrapDto.getUser());
        Article article = articleRepo.getReferenceById(scrapDto.getArticle());
        Scrap scrap = Scrap.builder()
                // .link(scrapDto.getLink())
                // .category(scrapDto.getCategory())
                .createTime(LocalDateTime.now())
                // .thumbnail(scrapDto.getThumbnail())
                .userId(user)
                .keyWordId(keyWord)
                .articleId(article)
                .build();
        scrapRepo.save(scrap);
    }

    @Transactional
    public List<Scrap> getScrapList(User user) {
        return scrapRepo.findByUserId_Id(user.getId());
    }

    @Transactional
    public List<Scrap> getScrapCategoryList(User user, String category) {
        List<Scrap> scr = scrapRepo.findByUserId_Id(user.getId());
        List<Scrap> scraps = new ArrayList<>();
        System.out.println("category: " + category + " userid: " user.getId());
        for (int i = 0; i < scr.size(); i++) {
            Scrap curScr = scr.get(i);

            System.out.println(curScr.getArticleId().getCategory());
            if (scr.get(i).getArticleId().getCategory().equals(category))
                scraps.add(scr.get(i));
        }
        return scraps;
    }
}
