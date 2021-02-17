package co.com.properties;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PropertyApp {

    public static void main(String[] args) {
        SpringApplication.run(PropertyApp.class, args);
    }

    @Bean
    CommandLineRunner runner(ConnectionKinesisAwsProperty awsProperty){
        return args ->{
            System.out.println(awsProperty.getKinesisConnectionUrl());
        };
    }

}
