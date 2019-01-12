package com.city.track.core.citytrackcore.proxy;

import com.city.track.core.citytrackcore.bean.WeatherBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "weather-searcher", url="localhost:8081")
public interface WeatherSearcherProxy {
    @GetMapping("/" + Repositories.Weather.OPEN_WEATHER_MAP_REPOSITORY + "?city={city}&latitude={lat}&longitude={lon}")
    WeatherBean getWeatherFromOWM(@PathVariable("city") String city,
                                  @PathVariable("lat") String latitude,
                                  @PathVariable("lon") String longitude);
}
