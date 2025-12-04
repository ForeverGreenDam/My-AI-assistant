package com.greendam.ai;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class RunApp {
    public static void main(String[] args) {
        SpringApplication.run(RunApp.class, args);
        log.info("启动成功");
    }
}
