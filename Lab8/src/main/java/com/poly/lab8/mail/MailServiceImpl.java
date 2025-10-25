package com.poly.lab8.mail;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    private final List<Mail> queue = new ArrayList<>();

    private boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    @Override
    public void send(Mail mail) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        // from
        if (isNullOrEmpty(mail.getFrom())) {
            helper.setFrom("WebShop <web-shop@gmail.com>");
            helper.setReplyTo("WebShop <web-shop@gmail.com>");
        } else {
            helper.setFrom(mail.getFrom());
            helper.setReplyTo(mail.getFrom());
        }

        // to/cc/bcc
        helper.setTo(mail.getTo().split("[,;]+"));
        if (!isNullOrEmpty(mail.getCc())) {
            helper.setCc(mail.getCc().split("[,;]+"));
        }
        if (!isNullOrEmpty(mail.getBcc())) {
            helper.setBcc(mail.getBcc().split("[,;]+"));
        }

        // subject & body
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getBody(), true);

        // attachments
        if (!isNullOrEmpty(mail.getFilenames())) {
            for (String path : mail.getFilenames().split("[,;]+")) {
                File file = new File(path.trim());
                if (file.exists()) {
                    FileSystemResource res = new FileSystemResource(file);
                    helper.addAttachment(file.getName(), res);
                }
            }
        }

        mailSender.send(message);
    }

    @Override
    public void push(Mail mail) {
        synchronized (queue) {
            queue.add(mail);
        }
    }

    // scheduled job: runs every 500ms and drain queue
    @Scheduled(fixedDelay = 500)
    public void run() {
        while (true) {
            Mail m = null;
            synchronized (queue) {
                if (!queue.isEmpty()) {
                    m = queue.remove(0);
                }
            }
            if (m == null) break;
            try {
                send(m);
            } catch (Exception e) {
                // log and optionally requeue / persist. For lab, just print stack.
                e.printStackTrace();
            }
        }
    }
}
