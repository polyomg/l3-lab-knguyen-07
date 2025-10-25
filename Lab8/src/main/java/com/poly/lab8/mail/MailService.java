package com.poly.lab8.mail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface MailService {

    @Data
    @Builder
    @NoArgsConstructor           // ✅ Thêm dòng này
    @AllArgsConstructor          // ✅ Và thêm cái này luôn để builder chạy tốt
    public static class Mail {
        private String from;
        private String to;
        private String cc;
        private String bcc;
        private String subject;
        private String body;
        private String filenames; // comma separated list
    }

    void send(Mail mail) throws Exception;

    default void send(String to, String subject, String body) throws Exception {
        Mail mail = Mail.builder()
                .to(to).subject(subject).body(body).build();
        this.send(mail);
    }

    void push(Mail mail);

    default void push(String to, String subject, String body) {
        this.push(Mail.builder().to(to).subject(subject).body(body).build());
    }
}
