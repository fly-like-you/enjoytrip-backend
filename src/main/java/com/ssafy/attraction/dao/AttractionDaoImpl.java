package com.ssafy.attraction.dao;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.AttractionsDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DBUtil;

public class AttractionDaoImpl implements AttractionDao {
	private static AttractionDao instance = new AttractionDaoImpl();
	
	private AttractionDaoImpl() {}

	public static AttractionDao getInstance() {
		return instance;
	}

	public Integer createAttraction(AttractionDto attractionDto) {
		String sql = "INSERT INTO attractions (title, addr1, content_type_id, first_image1, latitude, longitude) " +
				"VALUES (?, ?, ?, ?, ?, ?)";

		try (
				Connection conn = DBUtil.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
		) {
			// PreparedStatement에 값 설정
			pstmt.setString(1, attractionDto.getTitle());
			pstmt.setString(2, attractionDto.getAddr());
			pstmt.setInt(3, Integer.parseInt(attractionDto.getType())); // 문자열을 숫자로 변환하여 저장
			pstmt.setString(4, attractionDto.getImgUrl());
			pstmt.setDouble(5, attractionDto.getLat());
			pstmt.setDouble(6, attractionDto.getLng());

			int affectedRows = pstmt.executeUpdate();

			if (affectedRows > 0) {
				// 생성된 기본 키를 반환
				try (ResultSet rs = pstmt.getGeneratedKeys()) {
					if (rs.next()) {
						return rs.getInt(1); // 생성된 Attraction의 id 반환
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // 실패 시 -1 반환
	}

	public AttractionDto searchAttractionById(Integer id) {
		String sql = "SELECT no, title, addr1, content_type_id, first_image1, latitude, longitude " +
				"FROM attractions WHERE no = ?";

		AttractionDto attractionDto = null;

		try (
				Connection conn = DBUtil.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)
		) {
			pstmt.setInt(1, id);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					attractionDto = new AttractionDto();
					attractionDto.setId(rs.getInt("no"));
					attractionDto.setTitle(rs.getString("title"));
					attractionDto.setAddr(rs.getString("addr1"));
					attractionDto.setType(String.valueOf(rs.getInt("content_type_id")));
					attractionDto.setImgUrl(rs.getString("first_image1"));
					attractionDto.setLat(rs.getDouble("latitude"));
					attractionDto.setLng(rs.getDouble("longitude"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return attractionDto;
	}

	@Override
	public AttractionsDto searchAttractionsByContentTypeId(Integer contentTypeId) {
		String sql =
				"SELECT a.no, a.title, a.addr1, a.content_type_id, a.first_image1, a.latitude, a.longitude " +
				"FROM attractions a " +
				"JOIN contenttypes ct ON a.content_type_id = ct.content_type_id " +
				"WHERE a.content_type_id = ?";

		AttractionsDto attractionList = new AttractionsDto();

		try (
				Connection conn = DBUtil.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)
		) {
			pstmt.setInt(1, contentTypeId);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					AttractionDto attractionDto = new AttractionDto();
					attractionDto.setTitle(rs.getString("title"));
					attractionDto.setAddr(rs.getString("addr1"));
					attractionDto.setType(String.valueOf(rs.getInt("content_type_id")));
					attractionDto.setImgUrl(rs.getString("first_image1"));
					attractionDto.setLat(rs.getDouble("latitude"));
					attractionDto.setLng(rs.getDouble("longitude"));

					attractionList.addAttraction(attractionDto);  // 리스트에 AttractionDto 추가
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return attractionList;
	}


	public AttractionsDto searchAttractionsAll() {
	    String sql = 
	              "SELECT title, c.content_type_name, first_image1, latitude, longitude, addr1 "
	            + "FROM attractions a INNER JOIN contenttypes c "
	            + "ON a.content_type_id = c.content_type_id;";
	    

		AttractionsDto attractions = new AttractionsDto();
	    try (Connection conn = DBUtil.getInstance().getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery()) {
	        
	        while (rs.next()) {
	            String title = rs.getString("title");
	            String contentType = rs.getString("content_type_name");
	            String imageUrl = rs.getString("first_image1");
	            double latitude = rs.getDouble("latitude");
	            double longitude = rs.getDouble("longitude");
	            String address = rs.getString("addr1");

	            attractions.addAttraction(new AttractionDto(title, address, contentType, imageUrl, latitude, longitude));
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return attractions;
	}

	public void updateAttraction(AttractionDto attractionDto) {
		String sql =
			  "UPDATE attractions "
			+ "SET title = ?, "
			+ "addr1 = ?, "
			+ "content_type_id = ?, "
			+ "first_image1 = ?, "
			+ "latitude = ?, "
			+ "longitude = ? " +
			  "WHERE no = ?";

		try (
				Connection conn = DBUtil.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)
		) {
			pstmt.setString(1, attractionDto.getTitle());
			pstmt.setString(2, attractionDto.getAddr());
			pstmt.setInt(3, Integer.parseInt(attractionDto.getType()));
			pstmt.setString(4, attractionDto.getImgUrl());
			pstmt.setDouble(5, attractionDto.getLat());
			pstmt.setDouble(6, attractionDto.getLng());
			pstmt.setInt(7, attractionDto.getId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAttraction(Integer id) {
		String sql =
				"DELETE FROM attractions "
			  + "WHERE no = ?";

		try (
				Connection conn = DBUtil.getInstance().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)
		) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



}
