package com.ssafy.member.dao;


import com.ssafy.member.model.MemberDto;


import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDaoImpl implements MemberDao {
    
    private static MemberDao memberdao = new MemberDaoImpl();
    
    private MemberDaoImpl() {}
    
    public static MemberDao getMemberDao() {
        return memberdao;
    }
    
    //회원 정보 입력
    @Override
    public Integer createMember(MemberDto memberDto) {
        String sql =
                  "INSERT INTO members(name, password, joined_at, nickname, member_id,phone_number, email ) \n"
                + "VALUE (?, ?, ?, ?, ?, ?, ?);";
        try (
                Connection conn = DBUtil.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        sql,
                        Statement.RETURN_GENERATED_KEYS
                );
        ) {
            pstmt.setString(1, memberDto.getName());
            pstmt.setString(2, memberDto.getPassword());
            pstmt.setDate(3, memberDto.getJoined_at());
            pstmt.setString(4, memberDto.getNickname());
            pstmt.setString(5, memberDto.getMember_id());
            pstmt.setInt(6, memberDto.getPhone_number());
            pstmt.setString(7, memberDto.getEmail());

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

    
    
    //모든 회원 정보를 조회.
    @Override
    public List<MemberDto> searchMemberAll(){
    	String sql = 
    			  "SELECT member_id, name, nickname, password,phone_number,email, joined_at "
    			+ "FROM members";
    	List<MemberDto> list = new ArrayList<>();
    	
    	try (Connection conn = DBUtil.getInstance().getConnection();
   	         PreparedStatement pstmt = conn.prepareStatement(sql);
   	         ResultSet rs = pstmt.executeQuery()) {
   	        
   	        while (rs.next()) {
   	        	String member_id = rs.getString("member_id");
   	            String name = rs.getString("name");
   	            String nickname = rs.getString("nickname");
   	            String password = rs.getString("password");
   	            int phone_number = rs.getInt("phone_number");
   	            String email = rs.getString("email");
   	            Date joined_at = rs.getDate("joined_at");
   	            list.add(new MemberDto(member_id,name,nickname,password,phone_number,email,joined_at));
   	            

   	            
   	        }
   	        
   	    } catch (SQLException e) {
   	        e.printStackTrace();
   	    }

   	    return list;
    	
    }
    	
    	
    @Override
    public void updateMember(MemberDto memberDto) {
        String sql = "UPDATE members SET name=?, nickname=?, password=?, phone_number=?, email=? WHERE member_id=?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, memberDto.getName());
            pstmt.setString(2, memberDto.getNickname());
            pstmt.setString(3, memberDto.getPassword());
            pstmt.setInt(4, memberDto.getPhone_number());
            pstmt.setString(5, memberDto.getEmail());
            pstmt.setString(6, memberDto.getMember_id());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	    
	
    
    @Override
    public MemberDto findById(String memberId) {
        String sql = "SELECT * FROM members WHERE member_id = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, memberId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new MemberDto(
                        rs.getString("member_id"),
                        rs.getString("name"),
                        rs.getString("nickname"),
                        rs.getString("password"),
                        rs.getInt("phone_number"),
                        rs.getString("email"),
                        rs.getDate("joined_at")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 새로 추가된 deleteMember 메서드
    @Override
    public boolean deleteMember(String memberId) {
        String sql = "DELETE FROM members WHERE member_id = ?";
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, memberId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    	
    }

	


    	

//    @Override
//    public MemberDto getMemberById(String memberId) throws SQLException {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        MemberDto memberDto = null;
//        try {
//            conn = DBUtil.getInstance().getConnection();
//            String sql = "SELECT * FROM members WHERE member_id = ?";
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, memberId);
//            rs = pstmt.executeQuery();
//            if (rs.next()) {
//                memberDto = new MemberDto();
//                memberDto.setMemberId(rs.getString("member_id"));
//                memberDto.setName(rs.getString("name"));
//                memberDto.setNickname(rs.getString("nickname"));
//                memberDto.setPassword(rs.getString("password"));
//                memberDto.setPhoneNumber(rs.getString("phone_number"));
//                memberDto.setEmail(rs.getString("email"));
//                memberDto.setJoinDate(rs.getTimestamp("join_date").toLocalDateTime());
//            }
//        } finally {
//            DBUtil.getInstance().close(rs, pstmt, conn);
//        }
//        return memberDto;
//    }

//    @Override
//    public List<MemberDto> getAllMembers() throws SQLException {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        List<MemberDto> memberList = new ArrayList<>();
//        try {
//            conn = DBUtil.getInstance().getConnection();
//            String sql = "SELECT * FROM members";
//            pstmt = conn.prepareStatement(sql);
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                MemberDto memberDto = new MemberDto();
//                memberDto.setMemberId(rs.getString("member_id"));
//                memberDto.setName(rs.getString("name"));
//                memberDto.setNickname(rs.getString("nickname"));
//                memberDto.setPassword(rs.getString("password"));
//                memberDto.setPhoneNumber(rs.getString("phone_number"));
//                memberDto.setEmail(rs.getString("email"));
//                memberDto.setJoinDate(rs.getTimestamp("join_date").toLocalDateTime());
//                memberList.add(memberDto);
//            }
//        } finally {
//            DBUtil.getInstance().close(rs, pstmt, conn);
//        }
//        return memberList;
//    }
//
//    @Override
//    public void updateMember(MemberDto memberDto) throws SQLException {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        try {
//            conn = DBUtil.getInstance().getConnection();
//            String sql = "UPDATE members SET name=?, nickname=?, password=?, phone_number=?, email=? WHERE member_id=?";
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, memberDto.getName());
//            pstmt.setString(2, memberDto.getNickname());
//            pstmt.setString(3, memberDto.getPassword());
//            pstmt.setString(4, memberDto.getPhoneNumber());
//            pstmt.setString(5, memberDto.getEmail());
//            pstmt.setString(6, memberDto.getMemberId());
//            pstmt.executeUpdate();
//        } finally {
//            DBUtil.getInstance().close(pstmt, conn);
//        }
//    }
//
//    @Override
//    public void deleteMember(String memberId) throws SQLException {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        try {
//            conn = DBUtil.getInstance().getConnection();
//            String sql = "DELETE FROM members WHERE member_id=?";
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, memberId);
//            pstmt.executeUpdate();
//        } finally {
//            DBUtil.getInstance().close(pstmt, conn);
//        }
//    }
//
//    @Override
//    public boolean checkDuplicateId(String memberId) throws SQLException {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtil.getInstance().getConnection();
//            String sql = "SELECT COUNT(*) FROM members WHERE member_id=?";
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, memberId);
//            rs = pstmt.executeQuery();
//            if (rs.next()) {
//                return rs.getInt(1) > 0;
//            }
//        } finally {
//            DBUtil.getInstance().close(rs, pstmt, conn);
//        }
//        return false;
//    }
//
//    @Override
//    public boolean checkDuplicateEmail(String email) throws SQLException {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try {
//            conn = DBUtil.getInstance().getConnection();
//            String sql = "SELECT COUNT(*) FROM members WHERE email=?";
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, email);
//            rs = pstmt.executeQuery();
//            if (rs.next()) {
//                return rs.getInt(1) > 0;
//            }
//        } finally {
//            DBUtil.getInstance().close(rs, pstmt, conn);
//        }
//        return false;
//    }
//
//    @Override
//    public List<MemberDto> searchMembersByName(String name) throws SQLException {
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        List<MemberDto> memberList = new ArrayList<>();
//        try {
//            conn = DBUtil.getInstance().getConnection();
//            String sql = "SELECT * FROM members WHERE name LIKE ?";
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, "%" + name + "%");
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                MemberDto memberDto = new MemberDto();
//                memberDto.setMemberId(rs.getString("member_id"));
//                memberDto.setName(rs.getString("name"));
//                memberDto.setNickname(rs.getString("nickname"));
//                memberDto.setPassword(rs.getString("password"));
//                memberDto.setPhoneNumber(rs.getString("phone_number"));
//                memberDto.setEmail(rs.getString("email"));
//                memberDto.setJoinDate(rs.getTimestamp("join_date").toLocalDateTime());
//                memberList.add(memberDto);
//            }
//        } finally {
//            DBUtil.getInstance().close(rs, pstmt, conn);
//        }
//        return memberList;
 // }
