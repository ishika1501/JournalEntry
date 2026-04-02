package net.engineering.journalApp.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EmailServiceTest {

    @Test
    void testEmailServiceCreation() {
        EmailService emailService = new EmailService();
        assertNotNull(emailService);
    }

    @Test
    void testSendEmailMethod() {
        EmailService emailService = new EmailService();

        assertDoesNotThrow(() ->
                emailService.sendEmail("ishika15raj@gamil.com", "Test", "Hello")
        );
    }
}