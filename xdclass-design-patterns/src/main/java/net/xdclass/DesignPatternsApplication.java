package net.xdclass;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DesignPatternsApplication {

    public static void main(String[] args){
        SpringApplication.run(DesignPatternsApplication.class, args);
    }


}