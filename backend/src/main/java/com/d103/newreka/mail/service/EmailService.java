package com.d103.newreka.mail.service;

import java.util.Collections;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.d103.newreka.hottopic.domain.KeyWord;
import com.d103.newreka.hottopic.repo.KeyWordRepo;
import com.d103.newreka.user.domain.User;
import com.d103.newreka.user.repository.UserRepository;

@Service
public class EmailService {

	private final JavaMailSender emailSender;
	private final UserRepository userRepository; // Assuming you have a UserRepository to fetch all users.
	private final KeyWordRepo keyWordRepo; // Assuming you have a KeyWordRepository to fetch keywords.
	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

	public EmailService(JavaMailSender emailSender, UserRepository userRepository,
		KeyWordRepo keyWordRepo) {
		this.emailSender = emailSender;
		this.userRepository = userRepository;
		this.keyWordRepo = keyWordRepo;
	}

	// 일시 중지
	// @Scheduled(cron = "0 30 8 * * ?") // This will run the method at 9 AM every day.
	public void sendMorningSummaryEmails() {
		emailSendEmail();
	}

	// 일시 중지
	// @Scheduled(cron = "0 0 18 * * ?") // This will run the method at 6 PM every day.
	public void sendEveningSummaryEmails() {
		emailSendEmail();
	}

	private void emailSendEmail() {
		List<User> users = userRepository.findAll(); // Fetch all users.

		List<KeyWord> keywords = keyWordRepo.findTop10ByOrderByKeyWordIdDesc(); // Fetch all keywords for each user.
		Collections.reverse(keywords);

		for (User user : users) {
			try {
				System.out.println(user.getEmail());
				sendSummaryEmail(user, keywords);
			} catch (Exception e) {
				logger.error("Failed to send email to user: " + user.getEmail(), e);
			}
		}
	}

	public void sendSummaryEmail(User user, List<KeyWord> keywords) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom("yourEmail");
		helper.setTo(user.getEmail());

		String subject = String.format("%s님, 오늘의 키워드 요약 정보입니다", user.getName());
		helper.setSubject(subject);

		StringBuilder sb = new StringBuilder();

		// Start of HTML and set the background color.
		sb.append("<div style=\"background-color: #EDDCCF; padding: 20px; width: 75%; margin: auto;\">");

		sb.append(
			"<h1 style=\"font-size: 40px;  margin-top: 30px; margin-bottom: 30px; text-align: center;\">NewReka</h1>");
		sb.append("<br/>"); // Adds a line break.

		for (KeyWord keyword : keywords) {
			String summary = keyword.getSummary();
			if (summary == null || summary.length() <= 4) {
				continue;
			}

			// Add a div with white background for each keyword and its summary.
			// Add border-radius to make corners rounded.
			sb.append(
				"<div style=\"background-color: #ffffff;  margin-left: 30px;  margin-right: 30px; margin-bottom: 20px; padding: 10px 40px; border-radius: 10px;\">");

			sb.append(
				String.format("<p style=\"font-size: 18px;\"><strong>%s</strong></p>", keyword.getName()));
			sb.append(String.format("<p style=\"font-size: 16px;\">%s</p>", summary));

			sb.append("</div>");
		}

		// End of HTML.
		sb.append("</div>");

		// Set the second parameter to 'true' to indicate that this is an HTML email
		helper.setText(sb.toString(), true);

		emailSender.send(message);
	}

}
