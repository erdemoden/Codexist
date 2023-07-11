package DTOS.PlacesApiResponse;

import lombok.Data;

import java.util.List;

@Data
public class Results {
    private List<Result> results;
}
