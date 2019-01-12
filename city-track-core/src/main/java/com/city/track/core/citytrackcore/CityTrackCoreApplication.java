package com.city.track.core.citytrackcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CityTrackCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CityTrackCoreApplication.class, args);
    }

}

