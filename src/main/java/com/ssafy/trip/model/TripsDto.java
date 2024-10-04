package com.ssafy.trip.model;

import java.util.ArrayList;
import java.util.List;

public class TripsDto {
	private List<TripDto> trips = new ArrayList<>();

	public List<TripDto> getTrips() {
		return trips;
	}

	public void setTrips(List<TripDto> trips) {
		this.trips = trips;
	}
	
	public void addTrip(TripDto tripDto) {
		trips.add(tripDto);
	}

	public TripsDto() {};
	public TripsDto(List<TripDto> trips) {
		super();
		this.trips = trips;
	}
	
}
