package com.erdem.codexist.Repositories;

import com.erdem.codexist.Entities.PlacesSave;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PlacesSaveRepo extends MongoRepository<PlacesSave,String> {

    @Query("{'latLonRad':?0}")
    PlacesSave findByLatLonRad(String latLonRad);

}
