package test;

import com.ssafy.member.dao.MemberDao;
import com.ssafy.member.dao.MemberDaoImpl;
import com.ssafy.member.model.MemberDto;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class MemberDaoTest {
    static String tempMemberId;
    static final MemberDao memberDao = MemberDaoImpl.getMemberDao();
    static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("------------ 회원 CRU 테스트를 시작합니다. ------------");
        tempMemberId = 회원_생성하기();
        회원_모두_가져오기();
        회원_탈퇴하기("younghee92");
        회원_수정하기("gildong123");
        
        System.out.println("------------ 회원 테스트가 종료되었습니다. ------------");
        scanner.close();
    }

    private static String 회원_생성하기() {
        MemberDto member = new MemberDto(
                "testuser",
                "Test User",
                "tester",
                "password123",
                1234567890,
                "test@example.com",
                new Date(System.currentTimeMillis())
        );
        Integer result = memberDao.createMember(member);
        System.out.println("생성된 회원 ID: " + result);
        return member.getMember_id();
    }

    private static void 회원_모두_가져오기() {
        System.out.println("모든 회원 정보를 조회합니다.");
        List<MemberDto> members = memberDao.searchMemberAll();
        members.forEach(member -> System.out.println("회원 정보: " + member));
    }
    
    private static void 회원_탈퇴하기(String memberId) {
        MemberDto member = memberDao.findById(memberId);
        if (member != null) {
            System.out.println(member.getName() + " 회원을 탈퇴시킵니다.");
            boolean isDeleted = memberDao.deleteMember(memberId);
            if (isDeleted) {
                System.out.println(member.getName() + " 회원이 성공적으로 탈퇴되었습니다.");
            } else {
                System.out.println("회원 탈퇴에 실패했습니다. 회원 정보를 확인해주세요.");
            }
        } else {
            System.out.println("해당 ID의 회원을 찾을 수 없습니다: " + memberId);
        }
    }

    // ... 기존의 회원_생성하기, 회원_모두_가져오기, 회원_탈퇴하기 메서드는 그대로 유지 ...

    private static void 회원_수정하기(String memberId) {
        MemberDto memberToUpdate = memberDao.findById(memberId);
        
        if (memberToUpdate != null) {
            System.out.println("수정 전 회원 정보: " + memberToUpdate);
            
            // 미리 정의된 값으로 회원 정보 수정
            memberToUpdate.setName("김동현");
            memberToUpdate.setNickname("부산남자김동현");
            memberToUpdate.setPassword("newpassword123");
            memberToUpdate.setPhone_number(1098765432);
            memberToUpdate.setEmail("edwin.kim@gmail.com");

            memberDao.updateMember(memberToUpdate);

            MemberDto updatedMember = memberDao.findById(memberId);
            if (updatedMember != null) {
                System.out.println("수정된 회원 정보: " + updatedMember);
            } else {
                System.out.println("업데이트된 회원을 찾을 수 없습니다.");
            }
        } else {
            System.out.println("해당 member_id를 가진 회원을 찾을 수 없습니다: " + memberId);
        }
    }
   
    
    
}