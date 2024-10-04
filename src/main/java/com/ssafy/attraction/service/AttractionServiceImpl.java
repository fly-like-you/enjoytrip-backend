package com.ssafy.attraction.service;

import com.ssafy.attraction.dao.AttractionDaoImpl;
import com.ssafy.attraction.model.AttractionsDto;
public class AttractionServiceImpl implements AttractionService{

	final static AttractionServiceImpl instance = new AttractionServiceImpl();
	
	public static AttractionServiceImpl getBoardService() {
		return instance;
	}
	
	private AttractionServiceImpl() {}
	
	@Override
	public AttractionsDto searchAttractionsAll() {
		return AttractionDaoImpl.getInstance().searchAttractionsAll();
	}

}
