package com.example.detail.detailservice;

import com.example.detail.config.DetailEndpointConfig;
import com.example.detail.pojo.City;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.websocket.EndpointConfig;
import java.util.ArrayList;
import java.util.List;

@Service
public class DetailServiceImpl implements DetailService{
    private final RestTemplate restTemplate;
    public DetailServiceImpl(RestTemplate getRestTemplate) {
        this.restTemplate = getRestTemplate;
    }

    @Override
    @Retryable(include = IllegalAccessError.class)
    public List<Integer> findCityIdByName(String city) {
        City[] cities = restTemplate.getForObject(DetailEndpointConfig.queryWeatherByCity + city, City[].class);
        List<Integer> ans = new ArrayList<>();
        for(City c: cities) {
            if(c != null && c.getWoeid() != null) {
                ans.add(c.getWoeid());
            }
        }
        return ans;
    }

}
