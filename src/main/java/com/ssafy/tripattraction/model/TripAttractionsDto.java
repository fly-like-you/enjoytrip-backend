package com.ssafy.tripattraction.model;

import java.util.ArrayList;
import java.util.List;

public class TripAttractionsDto {
    private List<TripAttractionDto> tripAttractions;

    public TripAttractionsDto() {
        tripAttractions = new ArrayList<>();
    }

    public List<TripAttractionDto> getTripAttractions() {
        return tripAttractions;
    }

    public void setTripAttractions(List<TripAttractionDto> tripAttractions) {
        this.tripAttractions = tripAttractions;
    }

    public void addTripAttraction(TripAttractionDto tripAttractionDto) {
        tripAttractions.add(tripAttractionDto);
    }
}
