package com.ssafy.tripattraction.dao;

import com.ssafy.tripattraction.model.TripAttractionDto;
import com.ssafy.tripattraction.model.TripAttractionsDto;

public interface TripAttractionDao {
    Integer createTripAttraction(TripAttractionDto tripAttractionDto);
    TripAttractionsDto searchTripAttractionsByTripId(Integer tripId);
    TripAttractionDto searchTripAttractionsById(Integer id);
    void modifyTripAttraction(TripAttractionDto tripAttractionDto);
    void deleteTripAttraction(Integer id);
}

