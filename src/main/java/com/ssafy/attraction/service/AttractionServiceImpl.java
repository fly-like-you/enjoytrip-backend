package com.ssafy.attraction.service;

import com.ssafy.attraction.dao.AttractionDao;
import com.ssafy.attraction.dao.AttractionDaoImpl;
import com.ssafy.attraction.model.AttractionsDto;
public class AttractionServiceImpl implements AttractionService{

	private final static AttractionServiceImpl instance = new AttractionServiceImpl();
	private static AttractionDao attractionDao = AttractionDaoImpl.getInstance();
	public static AttractionServiceImpl getInstance() {
		return instance;
	}
	
	private AttractionServiceImpl() {}
	
	@Override
	public AttractionsDto searchAttractionsAll() {
		return attractionDao.searchAttractionsAll();
	}

	@Override
	public AttractionsDto searchAttracionByAreaCode(Integer areaCode) {
		return attractionDao.searchAttractionsByAreacode(areaCode);
	}


}
