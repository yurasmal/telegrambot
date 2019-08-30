package by.ps.rstelegrambot.bot;

import by.ps.rstelegrambot.entity.City;
import by.ps.rstelegrambot.service.CityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class JsonResponseParser implements ServerResponse {

//    @Autowired
//    CityService cityService;
//
//    @Override
//    public String getResponse(String cityName) {
//
//        City city = cityService.findByCityName(cityName);
//
//        if (city == null) {
//            return "City not found, try another one!";
//        }
//
//        return city.getMessage();
//    }

    @Override
    public String getResponse(String apiUrl){

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == 404) {
                return "City not found, try another one!";
            }

            String text = readData(connection);
            String response = parseJsonData(text);

            return response;

        } catch (IOException e){
            e.printStackTrace();
            return "The service is not available, try again later!";
        }

    }

    private String readData(HttpURLConnection connection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String input;
        StringBuffer response = new StringBuffer();

        while ((input = reader.readLine()) != null) {
            response.append(input);
        }
        reader.close();

        return response.toString();
    }

    private String parseJsonData(String data) throws IOException {

        return new ObjectMapper()
                    .readTree(data)
                    .get("message")
                   .toString()
                    .replace("\"", "");
    }
}
