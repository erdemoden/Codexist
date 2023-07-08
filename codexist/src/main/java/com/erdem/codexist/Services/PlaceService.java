package com.erdem.codexist.Services;

import DTOS.PlacesApiResponse.Results;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

@Service
public class PlaceService {
    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    public String getRequestForNearbyPlaces(String lang,String lon,String radius) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=AIzaSyBfHOXW8c7QiN6_iJR6Hx5V7r8RYpnqBps&location=40.943000,29.112930&radius=5000&type=restaurant"))
                .GET().build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest,BodyHandlers.ofString());
        Results results = mapper.readValue(getResponse.body(),Results.class);
        System.out.println(results.getResults().get(15).getName());
        return  StringEscapeUtils.unescapeJava(getResponse.body());
    }
}
