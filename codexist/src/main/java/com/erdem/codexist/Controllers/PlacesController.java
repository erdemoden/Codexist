package com.erdem.codexist.Controllers;

import com.erdem.codexist.Entities.PlacesSave;
import com.erdem.codexist.Services.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("places")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PlacesController {

    private final PlaceService placeService;

    @GetMapping
    public PlacesSave deneme(@RequestParam String lat, @RequestParam String lon, @RequestParam String radius) throws URISyntaxException, IOException, InterruptedException {
        //System.out.println(placeService.getRequestForNearbyPlaces(lang,lon,radius));
        return placeService.getRequestForNearbyPlaces(lat,lon,radius);
    }
}
