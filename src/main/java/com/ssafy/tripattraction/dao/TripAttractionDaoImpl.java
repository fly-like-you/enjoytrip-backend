package com.ssafy.tripattraction.dao;

import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.trip.model.TripDto;
import com.ssafy.tripattraction.model.TripAttractionDto;
import com.ssafy.tripattraction.model.TripAttractionsDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DBUtil;

public class TripAttractionDaoImpl implements TripAttractionDao {
    private static TripAttractionDaoImpl instance = new TripAttractionDaoImpl();

    private TripAttractionDaoImpl() {}

    public static TripAttractionDaoImpl getInstance() {
        return instance;
    }

    // tripAttractions 테이블에 데이터를 추가하는 메서드
    @Override
    public Integer createTripAttraction(TripAttractionDto tripAttractionDto) {
        String sql = "INSERT INTO tripAttractions (trip_id, attraction_id, `order`) VALUES (?, ?, ?)";
        
        try (
                Connection conn = DBUtil.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        sql,
                        Statement.RETURN_GENERATED_KEYS
                );
        ) {

            // PreparedStatement에 값 설정
            pstmt.setInt(1, tripAttractionDto.getTripId());
            pstmt.setInt(2, tripAttractionDto.getAttractionId());
            pstmt.setInt(3, tripAttractionDto.getOrder());

            // 쿼리 실행
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public TripAttractionsDto searchTripAttractionsByTripId(Integer tripId) {
        String sql =
                "SELECT ta.trip_id, ta.attraction_id, ta.`order`, " +
                "t.id AS trip_id, t.trip_name, t.member_id, t.start_date, t.end_date, t.created_at, " +
                "a.no AS attraction_id, a.title, a.addr1, a.content_type_id, a.first_image1, a.latitude, a.longitude " +
                "FROM tripAttractions ta " +
                "JOIN trips t ON ta.trip_id = t.id " +
                "JOIN attractions a ON ta.attraction_id = a.no " +
                "WHERE ta.trip_id = ?";

        TripAttractionsDto tripAttractionsDto = new TripAttractionsDto();

        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, tripId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    // TripAttractionDto 생성 및 기본 정보 설정
                    TripAttractionDto tripAttractionDto = new TripAttractionDto();
                    tripAttractionDto.setTripId(rs.getInt("trip_id"));
                    tripAttractionDto.setAttractionId(rs.getInt("attraction_id"));
                    tripAttractionDto.setOrder(rs.getInt("order"));

                    // TripDto 설정
                    TripDto tripDto = new TripDto();
                    tripDto.setId(rs.getInt("trip_id"));
                    tripDto.setName(rs.getString("trip_name"));
                    tripDto.setMemberId(rs.getInt("member_id"));
                    tripDto.setStartDate(rs.getDate("start_date"));
                    tripDto.setEndDate(rs.getDate("end_date"));
                    tripDto.setCreatedAt(rs.getDate("created_at"));
                    tripAttractionDto.setTrip(tripDto);

                    // AttractionDto 설정
                    AttractionDto attractionDto = new AttractionDto();
                    attractionDto.setTitle(rs.getString("title"));
                    attractionDto.setAddr(rs.getString("addr1"));
                    attractionDto.setType(String.valueOf(rs.getInt("content_type_id"))); // content_type_id를 문자열로 변환
                    attractionDto.setImgUrl(rs.getString("first_image1"));
                    attractionDto.setLat(rs.getDouble("latitude"));
                    attractionDto.setLng(rs.getDouble("longitude"));
                    tripAttractionDto.setAttraction(attractionDto);

                    // TripAttractionsDto에 추가
                    tripAttractionsDto.addTripAttraction(tripAttractionDto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tripAttractionsDto;
    }

    @Override
    public TripAttractionDto searchTripAttractionsById(Integer id) {
        String sql =
                  "SELECT id, `order`, trip_id, attraction_id "
                + "FROM tripAttractions "
                + "WHERE id = ?";

        TripAttractionDto tripAttractionDto = null;

        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    tripAttractionDto = new TripAttractionDto();
                    tripAttractionDto.setId(rs.getInt("id"));
                    tripAttractionDto.setTripId(rs.getInt("trip_id"));
                    tripAttractionDto.setAttractionId(rs.getInt("attraction_id"));
                    tripAttractionDto.setOrder(rs.getInt("order"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tripAttractionDto;
    }


    @Override
    public void modifyTripAttraction(TripAttractionDto tripAttractionDto) {
        String sql =
                  "UPDATE tripAttractions "
                + "SET attraction_id = ?, "
                + "trip_id = ?, "
                + "`order` = ? "
                + "WHERE id = ? ";

        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, tripAttractionDto.getAttractionId());
            pstmt.setInt(3, tripAttractionDto.getTripId());
            pstmt.setInt(2, tripAttractionDto.getOrder());
            pstmt.setInt(4, tripAttractionDto.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTripAttraction(Integer id) {
        String sql =
              "DELETE FROM tripAttractions "
            + "WHERE id = ?";

        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
