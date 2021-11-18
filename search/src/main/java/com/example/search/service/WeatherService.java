package com.example.search.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface WeatherService {
    Map<String, Map> findWeatherByCity(String city);
    Map<String, Map> findCityWeatherById(int id);
}
