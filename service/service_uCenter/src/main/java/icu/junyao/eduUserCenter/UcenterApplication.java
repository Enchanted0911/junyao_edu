package icu.junyao.eduUserCenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wu
 */
@SpringBootApplication
@ComponentScan(basePackages = {"icu.junyao"})
@MapperScan("icu.junyao.eduUserCenter.mapper")
@EnableDiscoveryClient
public class UcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
