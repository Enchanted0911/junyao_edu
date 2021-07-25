package icu.junyao.statisticsService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wu
 */
@SpringBootApplication
@ComponentScan(basePackages = {"icu.junyao"})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("icu.junyao.statistics.mapper")
@EnableScheduling
public class StatisticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class, args);
    }
}
