package com.ssafy.tripattraction.service;

import com.ssafy.tripattraction.model.TripAttractionDto;
import com.ssafy.tripattraction.model.TripAttractionsDto;

public interface TripAttractionService {
    Integer createTripAttraction(TripAttractionDto tripAttractionDto);
    TripAttractionsDto searchTripAttractionsByTripId(Integer tripId);
    TripAttractionDto searchTripAttractionsById(Integer id);
    void modifyTripAttraction(TripAttractionDto tripAttractionDto);
    void deleteTripAttraction(Integer id);
}
