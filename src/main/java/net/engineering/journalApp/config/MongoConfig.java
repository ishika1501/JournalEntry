package net.engineering.journalApp.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;



@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.uri:mongodb://localhost/newDb}")
    private String mongoUri;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @PostConstruct
    public void init() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║           MONGODB CONFIGURATION DEBUG INFO                 ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║ URI from properties: " + mongoUri);
        System.out.println("║ Database from properties: " + databaseName);
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }

    @Override
    protected String getDatabaseName() {
        System.out.println("getDatabaseName() called, returning: " + databaseName);
        return databaseName;
    }

    @Override
    public MongoClient mongoClient() {
        ConnectionString connectionString = new ConnectionString(mongoUri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }
}
