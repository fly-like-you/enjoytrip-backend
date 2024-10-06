package com.ssafy.trip.service;

import com.ssafy.trip.model.TripDto;
import com.ssafy.trip.model.TripsDto;

public interface TripService {
    // 여행 CRUD
    // Read
    TripsDto searchTripsAll();
    TripsDto findByMemberId(Integer memberId);
    TripDto findById(Integer tripId);

    // Create
    Integer createTrip(TripDto tripDto);

    // Update
    void modifyTrip(TripDto tripDto);

    // Delete
    void deleteTrip(Integer tripId);

}
