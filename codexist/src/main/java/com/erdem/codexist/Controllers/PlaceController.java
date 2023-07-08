package com.erdem.codexist.Controllers;

import com.erdem.codexist.Services.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("places")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping
    public String deneme(@RequestParam String lang,@RequestParam String lon,@RequestParam String radius) throws URISyntaxException, IOException, InterruptedException {
        //System.out.println(placeService.getRequestForNearbyPlaces(lang,lon,radius));
        placeService.getRequestForNearbyPlaces(lang,lon,radius);
        return null;
    }
}
