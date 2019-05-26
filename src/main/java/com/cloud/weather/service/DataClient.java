package com.cloud.weather.service;

import com.cloud.weather.vo.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("weather-client-zuul")
public interface DataClient {

    @GetMapping("/city/cities")
    List<City> listCity() throws Exception;

}
