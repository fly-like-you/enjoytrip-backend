package test;

import com.ssafy.login.dao.LoginDao;
import com.ssafy.login.dao.LoginDaoImpl;

public class LoginDaoTest {
    static final LoginDao loginDao = LoginDaoImpl.getLoginDao();

    public static void main(String[] args) {
        System.out.println("------------ 로그인 테스트를 시작합니다. ------------");
        testLogin();
        testLogout();
        testFindPassword();
        System.out.println("------------ 로그인 테스트가 종료되었습니다. ------------");
    }

    private static void testLogin() {
        // 데이터베이스에 실제로 존재하는 사용자 정보
        String validUsername = "chulsoo01";
        String validPassword = "password123";

        // 올바른 정보로 로그인 테스트
        boolean loginResult = loginDao.login(validUsername, validPassword);
        System.out.println("유효한 사용자 로그인 결과: " + (loginResult ? "성공" : "실패"));

        // 잘못된 정보로 로그인 테스트
        String invalidUsername = "nonexistentuser";
        String invalidPassword = "wrongpassword";
        loginResult = loginDao.login(invalidUsername, invalidPassword);
        System.out.println("유효하지 않은 사용자 로그인 결과: " + (loginResult ? "성공" : "실패"));
    }
    private static void testLogout() {
        String username = "testuser";
        loginDao.logout(username);
        System.out.println(username + " 사용자가 로그아웃되었습니다.");
    }

    private static void testFindPassword() {
        String email = "chulsoo@example.com";
        String result = loginDao.findPassword(email);
        System.out.println("이메일 : "+email+", " + result);
    }
}