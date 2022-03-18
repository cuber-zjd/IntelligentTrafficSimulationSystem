package com.cuber;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cuber.mapper")
public class IntelligentTrafficSimulationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntelligentTrafficSimulationSystemApplication.class, args);
    }

}
