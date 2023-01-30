package com.project.revolvingcabinet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.project.revolvingcabinet.dao")
@SpringBootApplication
@EnableScheduling // 开启定时任务
public class RevolvingCabinetApplication {

    public static void main(String[] args) {
        SpringApplication.run(RevolvingCabinetApplication.class, args);
    }

}
