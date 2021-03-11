package com.szcinda.express;


import com.szcinda.express.controller.netty.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.szcinda.express")
@EnableJpaRepositories(basePackages = "com.szcinda.express")
@EntityScan(basePackages = "com.szcinda.express.persistence")
@EnableScheduling
public class RPAApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(RPAApplication.class, args);
        new NettyServer().bind(63360);
    }
}
