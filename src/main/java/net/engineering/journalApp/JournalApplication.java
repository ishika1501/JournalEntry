package net.engineering.journalApp;

import jakarta.annotation.PostConstruct;
import net.engineering.journalApp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				SpringApplication.run(JournalApplication.class, args);

//		EmailService emailService = context.getBean(EmailService.class);
//		emailService.sendEmail("ishika15raj@gmail.com", "Test", "Hello Ishika");
	}
}