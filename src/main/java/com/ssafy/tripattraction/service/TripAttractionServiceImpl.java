package com.ssafy.tripattraction.service;

import com.ssafy.tripattraction.dao.TripAttractionDao;
import com.ssafy.tripattraction.dao.TripAttractionDaoImpl;
import com.ssafy.tripattraction.model.TripAttractionDto;
import com.ssafy.tripattraction.model.TripAttractionsDto;

public class TripAttractionServiceImpl implements TripAttractionService {
    private static TripAttractionService instance = new TripAttractionServiceImpl();
    private static TripAttractionDao tripAttractionDao = TripAttractionDaoImpl.getInstance();
    private TripAttractionServiceImpl() {}
    public static TripAttractionService getInstance() {
        return instance;
    }

    @Override
    public Integer createTripAttraction(TripAttractionDto tripAttractionDto) {
        return tripAttractionDao.createTripAttraction(tripAttractionDto);
    }

    @Override
    public TripAttractionsDto searchTripAttractionsByTripId(Integer tripId) {
        return tripAttractionDao.searchTripAttractionsByTripId(tripId);
    }

    @Override
    public TripAttractionDto searchTripAttractionsById(Integer id) {
        return tripAttractionDao.searchTripAttractionsById(id);
    }

    @Override
    public void modifyTripAttraction(TripAttractionDto tripAttractionDto) {
        tripAttractionDao.modifyTripAttraction(tripAttractionDto);
    }

    @Override
    public void deleteTripAttraction(Integer id) {
        tripAttractionDao.deleteTripAttraction(id);
    }
}
