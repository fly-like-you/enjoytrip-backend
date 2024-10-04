package util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *  더미 데이터 생성 및 제거 유틸 클래스
 */
public class TestUtil {
    public static Integer createTempUser() {
        String sql =
                "INSERT INTO members (name, password, nickname, member_id, phone_number, email) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = DBUtil.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        sql,
                        Statement.RETURN_GENERATED_KEYS
                )
        ) {
            // 더미 데이터 설정
            pstmt.setString(1, "홍길동"); // name
            pstmt.setString(2, "password123"); // password
            pstmt.setString(3, "Johnny"); // nickname
            pstmt.setString(4, "member001"); // member_id
            pstmt.setInt(5, 1234567890); // phone_number
            pstmt.setString(6, "홍길동@example.com"); // email

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void deleteTempUser(Integer userId) {
        String sql =
              "DELETE FROM members\n"
            + "WHERE id = ?;";

        try (
                Connection conn = DBUtil.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Integer createTempTrip(Integer tempUserId) {
        String sql =
                "INSERT INTO trips (trip_name, start_date, member_id, end_date, created_at) " +
                        "VALUES (?, ?, ?, ?, ?)";

        try (
                Connection conn = DBUtil.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        sql,
                        Statement.RETURN_GENERATED_KEYS
                )
        ) {
            pstmt.setString(1, "제주도 여행");
            pstmt.setDate(2, Date.valueOf("2024-10-01"));
            pstmt.setInt(3, tempUserId);
            pstmt.setDate(4, Date.valueOf("2024-10-07"));
            pstmt.setDate(5, new Date(System.currentTimeMillis()));

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

    public static void deleteTempTrip(Integer tempTripId) {
        String sql =
              "DELETE FROM trips\n"
            + "WHERE id = ?;";

        try (
                Connection conn = DBUtil.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, tempTripId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Integer createTempAttraction() {
        String sql =
                "INSERT INTO attractions (title, addr1, content_type_id, first_image1, latitude, longitude) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = DBUtil.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        sql,
                        Statement.RETURN_GENERATED_KEYS
                )
        ) {
            // 더미 데이터 설정
            pstmt.setString(1, "한라산 국립공원"); // title
            pstmt.setString(2, "제주특별자치도 서귀포시"); // addr1
            pstmt.setInt(3, 12); // content_type_id (관광지 유형)
            pstmt.setString(4, "image_url_1.jpg"); // first_image1
            pstmt.setDouble(5, 33.499621); // latitude
            pstmt.setDouble(6, 126.531188); // longitude

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                // 생성된 기본 키를 받아옴
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) return rs.getInt(1); // attraction_id 반환
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void deleteTempAttraction(Integer tempAttractionId) {
        String sql =
                "DELETE FROM attractions\n"
                        + "WHERE no = ?;";

        try (
                Connection conn = DBUtil.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, tempAttractionId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
