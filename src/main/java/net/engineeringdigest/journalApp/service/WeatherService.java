package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    @Autowired
    AppCache appCache;

    @Autowired
    private RestTemplate  restTemplate;

    public WeatherResponse getWeather(String city){
        String finalAPI = appCache.appCache.get("weather_api").replace("<city>", city).replace("<apikey>", appCache.appCache.get("api_key"));
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);

        return response.getBody();
    }

}
