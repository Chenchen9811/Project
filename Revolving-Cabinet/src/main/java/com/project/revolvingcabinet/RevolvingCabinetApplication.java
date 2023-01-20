package com.project.revolvingcabinet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.project.revolvingcabinet.dao")
@SpringBootApplication
public class RevolvingCabinetApplication {

    public static void main(String[] args) {
        SpringApplication.run(RevolvingCabinetApplication.class, args);
    }

}
