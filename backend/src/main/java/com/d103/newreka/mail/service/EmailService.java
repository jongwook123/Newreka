package com.d103.newreka.mail.service;

import com.d103.newreka.hottopic.domain.KeyWord;
import com.d103.newreka.hottopic.repo.KeyWordRepo;
import com.d103.newreka.user.domain.User;
import com.d103.newreka.user.repository.UserRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Collections;
import java.util.List;

@Service
public class EmailService {

    private final JavaMailSender emailSender;
    private final UserRepository userRepository; // Assuming you have a UserRepository to fetch all users.
    private final KeyWordRepo keyWordRepo; // Assuming you have a KeyWordRepository to fetch keywords.

    public EmailService(JavaMailSender emailSender, UserRepository userRepository,
                        KeyWordRepo keyWordRepo) {
        this.emailSender = emailSender;
        this.userRepository = userRepository;
        this.keyWordRepo = keyWordRepo;
    }

    // @Scheduled(cron = "0 42 01 * * ?") // This will run the method at 8 AM every day.
    // public void sendDailySummaryEmails() throws MessagingException {
    // 	List<User> users = userRepository.findAll(); // Fetch all users.
    //
    // 	for (User user : users) {
    // 		List<KeyWord> keywords = keyWordRepo.findTop10ByOrderByKeyWordIdDesc(); // Fetch all keywords for each user.
    //
    // 		Collections.reverse(keywords);
    //
    // 		// for (KeyWord keyword : keywords) {
    // 		// 	sendSummaryEmail(user, keywords);
    // 		// }
    // 		sendSummaryEmail(user, keywords);
    // 	}
    // }
    @Scheduled(cron = "0 0 9 * * ?") // This will run the method at 9 AM every day.
    public void sendMorningSummaryEmails() throws MessagingException {
        List<User> users = userRepository.findAll(); // Fetch all users.

        for (User user : users) {
            List<KeyWord> keywords = keyWordRepo.findTop10ByOrderByKeyWordIdDesc(); // Fetch all keywords for each user.

            Collections.reverse(keywords);

            sendSummaryEmail(user, keywords);
        }
    }

    @Scheduled(cron = "0 0 18 * * ?") // This will run the method at 6 PM every day.
    public void sendEveningSummaryEmails() throws MessagingException {
        List<User> users = userRepository.findAll(); // Fetch all users.

        for (User user : users) {
            List<KeyWord> keywords = keyWordRepo.findTop10ByOrderByKeyWordIdDesc(); // Fetch all keywords for each user.

            Collections.reverse(keywords);

            sendSummaryEmail(user, keywords);
        }
    }

    public void sendSummaryEmail(User user, List<KeyWord> keywords) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("krbest98@naver.com");
        helper.setTo(user.getEmail());

        String subject = String.format("%s님, 오늘의 키워드 요약 정보입니다", user.getName());
        helper.setSubject(subject);

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("<h1 style=\"font-size: 24px;\">안녕하세요 %s님,</h1>", user.getName()));

        for (KeyWord keyword : keywords) {
            String summary = keyword.getSummary();
            if (summary == null || summary.length() <= 4) {
                continue;
            }
            sb.append(
                    String.format("<p style=\"font-size: 18px;\"><strong>%s</strong>에 대한 요약 정보:</p>", keyword.getName()));
            sb.append(String.format("<p style=\"font-size: 16px;\">%s</p>", summary));
            sb.append("<br/>"); // Adds a line break between different keywords.
        }

        // Set the second parameter to 'true' to indicate that this is an HTML email
        helper.setText(sb.toString(), true);

        emailSender.send(message);
    }

}
