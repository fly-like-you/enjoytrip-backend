package com.ssafy.trip.service;

public class TripServiceImpl implements TripService {
	private static TripService instance = new TripServiceImpl();
	private TripServiceImpl() {}
	public static TripService getInstance() {
		return instance;
	}
	

}
