package com.yannavalon.apollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableApolloConfig
@SpringBootApplication
public class TestApplloApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestApplloApplication.class, args);
    }

}
