package com.ssafy.tripattraction.model;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.trip.model.TripDto;

public class TripAttractionDto {
    private Integer id;
    private Integer tripId;
    private TripDto trip;
    private Integer attractionId;
    private AttractionDto attraction;
    private Integer order;

    public TripAttractionDto() {}

    public TripAttractionDto(Integer id, Integer tripId, TripDto trip, Integer attractionId, AttractionDto attraction,
                             Integer order) {
        this.id = id;
        this.tripId = tripId;
        this.trip = trip;
        this.attractionId = attractionId;
        this.attraction = attraction;
        this.order = order;
    }

    public TripAttractionDto(Integer tripId, Integer attractionId) {
        this.tripId = tripId;
        this.attractionId = attractionId;
    }

    public TripAttractionDto(TripDto trip, AttractionDto attraction, Integer order) {
        this.trip = trip;
        this.attraction = attraction;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public TripDto getTrip() {
        return trip;
    }

    public void setTrip(TripDto trip) {
        this.trip = trip;
    }

    public Integer getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(Integer attractionId) {
        this.attractionId = attractionId;
    }

    public AttractionDto getAttraction() {
        return attraction;
    }

    public void setAttraction(AttractionDto attraction) {
        this.attraction = attraction;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "TripAttractionDto[" +
                "tripId=" + tripId +
                ", trip=" + trip +
                ", attractionId=" + attractionId +
                ", attraction=" + attraction +
                ", order=" + order +
                ']';
    }
}
