package com.ssafy.trip.service;

import com.ssafy.trip.dao.TripDao;
import com.ssafy.trip.dao.TripDaoImpl;
import com.ssafy.trip.model.TripDto;
import com.ssafy.trip.model.TripsDto;

public class TripServiceImpl implements TripService {
	private static TripService instance = new TripServiceImpl();
	private static TripDao tripDao = TripDaoImpl.getTripDao();
	private TripServiceImpl() {}
	public static TripService getInstance() {
		return instance;
	}


	@Override
	public TripsDto searchTripsAll() {
		return tripDao.searchTripsAll();
	}

	@Override
	public TripsDto findByMemberId(Integer memberId) {
		return tripDao.findByMemberId(memberId);
	}

	@Override
	public TripDto findById(Integer tripId) {
		return tripDao.findById(tripId);
	}

	@Override
	public Integer createTrip(TripDto tripDto) {
		return tripDao.createTrip(tripDto);
	}

	@Override
	public void modifyTrip(TripDto tripDto) {
		tripDao.modifyTrip(tripDto);
	}

	@Override
	public void deleteTrip(Integer tripId) {
		tripDao.deleteTrip(tripId);
	}
}
