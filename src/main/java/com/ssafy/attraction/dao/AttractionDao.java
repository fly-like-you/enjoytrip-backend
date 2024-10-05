package com.ssafy.attraction.dao;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.AttractionsDto;

public interface AttractionDao {

	Integer createAttraction(AttractionDto attractionDto);

	AttractionDto searchAttractionById(Integer id);
	AttractionsDto searchAttractionsByContentTypeId(Integer contentTypeId);
	AttractionsDto searchAttractionsByAreacode(Integer areaCode);
	AttractionsDto searchAttractionsAll();

	AttractionsDto searchAttractions(int numOfRows, int pageNo, String areaCode, String contentTypeId);

	void updateAttraction(AttractionDto attractionDto);

	void deleteAttraction(Integer id);
}
