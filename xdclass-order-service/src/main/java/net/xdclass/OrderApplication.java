package net.xdclass;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
// 开启服务发现
@EnableDiscoveryClient
// 开启Feign 支持
@EnableFeignClients
public class OrderApplication {

    public static void main(String[] args){
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
//    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
