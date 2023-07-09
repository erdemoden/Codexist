package com.erdem.codexist.Entities;

import DTOS.PlacesApiResponse.Result;
import DTOS.PlacesApiResponse.Results;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@Document(collection = "PlacesSave")
public class PlacesSave {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String latLonRad;
    private Results results;
}
