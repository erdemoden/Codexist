package com.erdem.codexist.Services;

import DTOS.PlacesApiResponse.Results;
import com.erdem.codexist.Entities.PlacesSave;
import com.erdem.codexist.Repositories.PlacesSaveRepo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class PlaceService {
    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private final PlacesSaveRepo placeSaveRepo;
    public PlacesSave getRequestForNearbyPlaces(String lat,String lon,String radius) throws URISyntaxException, IOException, InterruptedException {
        PlacesSave placesSave = placeSaveRepo.findByLatLonRad(lat+lon+radius);
        if(placesSave!=null){
            return placesSave;
        }
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(String.format("https://maps.googleapis.com/maps/api/place/nearbysearch/json?key=AIzaSyBfHOXW8c7QiN6_iJR6Hx5V7r8RYpnqBps&location=%s,%s&radius=%s&type=restaurant",lat,lon,radius)))
                .GET().build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> getResponse = httpClient.send(getRequest,BodyHandlers.ofString());
        Results results = mapper.readValue(getResponse.body(),Results.class);
        PlacesSave savedPlacesSave = new PlacesSave();
        savedPlacesSave.setResults(results);
        savedPlacesSave.setLatLonRad(lat+lon+radius);
        placeSaveRepo.save(savedPlacesSave);
        System.out.println(savedPlacesSave.getResults().getResults().get(0).getName());
        return savedPlacesSave;
        //return  StringEscapeUtils.unescapeJava(getResponse.body());
    }
}
