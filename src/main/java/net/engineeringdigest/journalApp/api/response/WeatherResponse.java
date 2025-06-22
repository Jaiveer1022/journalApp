package net.engineeringdigest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WeatherResponse {
    public Current current;

    public class Current{

        public int temperature;
        @JsonProperty("weather_descriptions")
        public List<String> weather_descriptions;
        public int feelslike;
    }



}
