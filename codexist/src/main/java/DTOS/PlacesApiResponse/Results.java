package DTOS.PlacesApiResponse;

import DTOS.PlacesApiResponse.Result;
import lombok.Data;

import java.util.List;

@Data
public class Results {
    private List<Result> results;
}
