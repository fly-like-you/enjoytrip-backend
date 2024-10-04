package test;

import com.ssafy.tripattraction.dao.TripAttractionDao;
import com.ssafy.tripattraction.dao.TripAttractionDaoImpl;
import com.ssafy.tripattraction.model.TripAttractionDto;
import com.ssafy.tripattraction.model.TripAttractionsDto;
import java.sql.SQLException;
import util.TestUtil;

public class TripAttractionDaoTest {
    static final TripAttractionDao tripAttractionDao = TripAttractionDaoImpl.getInstance();
    static Integer tempUserId;
    static Integer tempTripId;
    static Integer tempAttractionId;
    public static void main(String[] args) throws SQLException {
        tempUserId = TestUtil.createTempUser();
        tempTripId = TestUtil.createTempTrip(tempUserId);
        tempAttractionId = TestUtil.createTempAttraction();
        if (tempUserId == -1 || tempTripId == -1 || tempAttractionId == -1) throw new SQLException("실패");
        System.out.println("------------ F15. 여행 계획 경로 설정 CRUD 테스트를 시작합니다. ------------");

        Integer tripAttractionId = 여행계획_생성하기();
        여행계획_여행id로_가져오기();
        여행계획_수정하기(tripAttractionId);
        여행계획_삭제하기(tripAttractionId);

        System.out.println("------------ F15. 여행 계획 경로 설정 CRUD 테스트가 종료되었습니다. ------------");

        TestUtil.deleteTempAttraction(tempAttractionId);
        TestUtil.deleteTempTrip(tempTripId);
        TestUtil.deleteTempUser(tempUserId);

    }

    private static Integer 여행계획_생성하기() throws SQLException {
        System.out.println("\n01. 여행 계획을 생성합니다.");
        TripAttractionDto tripAttractionDto = new TripAttractionDto(tempTripId, tempAttractionId);
        tripAttractionDto.setOrder(1);  // 순서는 임의로 1로 설정

        Integer id = tripAttractionDao.createTripAttraction(tripAttractionDto);

        if (id == -1) {
            throw new SQLException("실패");
        } else {
            System.out.println("여행 계획이 성공적으로 생성되었습니다. Trip ID: " + tempTripId + ", Attraction ID: " + tempAttractionId);
            return id;
        }
    }

    public static void 여행계획_여행id로_가져오기() {
        System.out.println("\n02. 여행 계획을 가져옵니다.");

        // Trip ID로 여행 계획 경로 조회
        TripAttractionsDto tripAttractionsDto = tripAttractionDao.searchTripAttractionsByTripId(tempTripId);

        System.out.println("여행 계획을 성공적으로 가져왔습니다. Trip ID: " + tempTripId);
        for (TripAttractionDto tripAttraction : tripAttractionsDto.getTripAttractions()) {
            System.out.println("Attraction ID: " + tripAttraction.getAttractionId());
            System.out.println("Attraction: " + tripAttraction.getAttraction());
            System.out.println("Trip ID: " + tripAttraction.getTripId());
            System.out.println("Trip: " + tripAttraction.getTrip());
            System.out.println("Order: " + tripAttraction.getOrder());

        }
    }


    public static void 여행계획_수정하기(Integer tripAttractionId) {
        System.out.println("\n03. 여행 계획을 수정합니다.");
        TripAttractionDto tripAttractionDto = tripAttractionDao.searchTripAttractionsById(tripAttractionId);
        System.out.println("수정 전 여행의 순서: " + tripAttractionDto.getOrder());
        tripAttractionDto.setOrder(2);
        // DAO에서 TripAttraction 수정
        tripAttractionDao.modifyTripAttraction(tripAttractionDto);
        System.out.println("수정 후 여행의 순서: " + tripAttractionDto.getOrder());

        System.out.println("여행 계획이 성공적으로 수정되었습니다. TripAttraction ID: " + tripAttractionId);
    }


    public static void 여행계획_삭제하기(Integer id) {
        System.out.println("\n04. 여행 계획을 삭제합니다.");
        tripAttractionDao.deleteTripAttraction(id);
        System.out.println("여행 계획이 성공적으로 삭제되었습니다. Trip ID: " + id);
    }

}
