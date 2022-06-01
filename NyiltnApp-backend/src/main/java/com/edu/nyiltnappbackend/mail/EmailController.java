package com.edu.nyiltnappbackend.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class handling email sending.
 */
@RestController
@RequestMapping("/api/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    /**
     * Sends a text mail.
     *
     * @param to      email address of a user.
     * @param subject subject of the email.
     * @param text    content of the email.
     */
    @PostMapping("/simplemail")
    public void sendSimpleMail(@RequestParam("to") String to,
                               @RequestParam("subject") String subject,
                               @RequestParam("text") String text) {
        emailService.sendSimpleMessage(to, subject, text);
    }
}

