package DTOS.PlacesApiResponse;

import DTOS.PlacesApiResponse.Geometry;
import lombok.Data;

@Data
public class Result {
    private String name;
    private Geometry geometry;
    // getters and setters
}



