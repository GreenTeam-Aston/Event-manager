package aston.greenteam.eventmanager.util;

import aston.greenteam.eventmanager.dtos.WeatherDTO;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class WeatherUtil {
    private JSONObject object;

    public WeatherDTO getWeatherDto(LocalDateTime localDate) {
        try {
            object = new JSONObject(getJsonWeather());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        JSONObject weatherObject = object
                .getJSONObject("forecast")
                .getJSONArray("forecastday")
                .getJSONObject(1)
                .getJSONArray("hour")
                .getJSONObject(localDate.getHour());

        WeatherDTO dto = new WeatherDTO();
        dto.setDescription(weatherObject.getJSONObject("condition").getString("text"));
        dto.setTemperature(weatherObject.getBigDecimal("temp_c"));
        dto.setHumidity(weatherObject.getBigDecimal("humidity"));
        dto.setWindSpeed(weatherObject.getBigDecimal("wind_kph"));

        return dto;
    }

    private String getJsonWeather() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://weatherapi-com.p.rapidapi.com/forecast.json?q=Saint-Petersburg&days=2"))
                .header("X-RapidAPI-Key", "49ddd8a6b5mshbbb9f82a0c38a96p113604jsn71a462ef21fe")
                .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
