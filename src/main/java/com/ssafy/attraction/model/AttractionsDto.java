package com.ssafy.attraction.model;

import java.util.ArrayList;
import java.util.List;

public class AttractionsDto {
    private List<AttractionDto> attractions = new ArrayList<>();

    public AttractionsDto(List<AttractionDto> attractions) {
        this.attractions = attractions;
    }

    public AttractionsDto() {
    }

    public List<AttractionDto> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<AttractionDto> attractions) {
        this.attractions = attractions;
    }

    public void addAttraction(AttractionDto attractionDto) {
        attractions.add(attractionDto);
    }
}
