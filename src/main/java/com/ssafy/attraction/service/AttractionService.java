package com.ssafy.attraction.service;

import com.ssafy.attraction.model.AttractionsDto;

public interface AttractionService {
	AttractionsDto searchAttractionsAll();
	AttractionsDto searchAttracionByAreaCode(Integer areaCode);
    AttractionsDto searchAttractions(int numOfRows, int pageNo, String areaCode, String contentTypeId);
}
