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
                  "INSERT INTO members(member_id, name, nickname, password, email, joined_at, phone_number) \n"
                + "VALUE (?, ?, ?, ?, ?, ?, ?);";
        try (
                Connection conn = DBUtil.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        sql,
                        Statement.RETURN_GENERATED_KEYS
                );
        ) {
            pstmt.setString(1, memberDto.getUserId());
            pstmt.setString(2, memberDto.getName());
            pstmt.setString(3, memberDto.getNickname());
            pstmt.setString(4, memberDto.getPassword());
            pstmt.setString(5, memberDto.getEmail());
            pstmt.setString(6, memberDto.getJoinDate());
            pstmt.setString(7, memberDto.getPhone());

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
    			  "SELECT id, member_id, name, nickname, password,phone_number,email, joined_at "
    			+ "FROM members";
    	List<MemberDto> list = new ArrayList<>();
    	
    	try (Connection conn = DBUtil.getInstance().getConnection();
   	         PreparedStatement pstmt = conn.prepareStatement(sql);
   	         ResultSet rs = pstmt.executeQuery()) {
   	        
   	        while (rs.next()) {
   	        	MemberDto member = new MemberDto();
   	        	member.setId(rs.getInt("id"));
   	        	member.setUserId(rs.getString("member_id"));
   	            member.setName(rs.getString("name"));
   	            member.setNickname(rs.getString("nickname"));
   	            member.setPassword(rs.getString("password"));
   	            member.setEmail(rs.getString("email"));
   	            member.setJoinDate(rs.getString("joined_at"));
   	            member.setPhone(rs.getString("phone_number"));
   	            list.add(member);
   	        }
   	        
   	    } catch (SQLException e) {
   	        e.printStackTrace();
   	    }

   	    return list;
    	
    }
    	
    	
    @Override
    public void updateMember(MemberDto memberDto) {
        String sql = "UPDATE members SET member_id = ?, name = ?, password = ?, nickname = ?, phone_number = ?, email = ? WHERE id = ?";
        
        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, memberDto.getUserId());
            pstmt.setString(2, memberDto.getName());
            pstmt.setString(3, memberDto.getPassword());
            pstmt.setString(4, memberDto.getNickname());
            pstmt.setInt(5, Integer.parseInt(memberDto.getPhone()));
            pstmt.setString(6, memberDto.getEmail());
            pstmt.setInt(7, memberDto.getId());

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
                    MemberDto memberDto = new MemberDto();
                    memberDto.setId(rs.getInt("id"));
                    memberDto.setUserId(rs.getString("member_id"));
                    memberDto.setName(rs.getString("name"));
                    memberDto.setNickname(rs.getString("nickname"));
                    memberDto.setPassword(rs.getString("password"));
                    memberDto.setEmail(rs.getString("email"));
                    memberDto.setJoinDate(rs.getString("joined_at"));
                    memberDto.setPhone(String.valueOf(rs.getInt("phone_number")));
                    return memberDto;
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

	@Override
	public int idCheck(String userId) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberDto loginMember(String userId, String userPwd) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
    
    	
}

