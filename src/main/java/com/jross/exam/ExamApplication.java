package com.jross.exam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Boot Class
 * @author jpereira 05/16/2018
 */
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages={"com.jross"})
public class ExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class, args);
    }
}
