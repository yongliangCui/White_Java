package com.tommy.white_jotter;

import com.tommy.white_jotter.config.ShiroConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableCaching
@SpringBootApplication
@Import(ShiroConfiguration.class)
@EnableTransactionManagement
public class WhiteJotterApplication {
    public static void main(String[] args) {
        SpringApplication.run(WhiteJotterApplication.class, args);
    }

}
