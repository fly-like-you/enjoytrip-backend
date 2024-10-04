package com.ssafy.trip.model;

import java.sql.Date;

public class TripDto {
	private Integer id;
	
	private String name;
	
	private Integer memberId;
	private Date startDate;
	private Date endDate;
	private Date createdAt;
	
	public TripDto() {}

	public TripDto(Integer id, String name, Integer memberId, Date startDate, Date endDate, Date createdAt) {
		this.id = id;
		this.name = name;
		this.memberId = memberId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "TripDto{" +
				"id=" + id +
				", name='" + name + '\'' +
				", memberId=" + memberId +
				", startDate=" + startDate +
				", endDate=" + endDate +
				", createdAt=" + createdAt +
				'}';
	}

	public TripDto(String name, Integer memberId, Date startDate, Date endDate, Date createdAt) {
		this.name = name;
		this.memberId = memberId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdAt = createdAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Integer getMemberId() {
		return memberId;
	}



	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}



	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}



	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	

	
}
