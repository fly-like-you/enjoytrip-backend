package test;

import com.ssafy.trip.dao.TripDao;
import com.ssafy.trip.dao.TripDaoImpl;
import com.ssafy.trip.model.TripDto;
import com.ssafy.trip.model.TripsDto;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import util.TestUtil;

public class TripDaoTest {
    static final TripDao tripDao = TripDaoImpl.getTripDao();
    static Integer tempUserId;
    static Integer tempTripId;

    public static void main(String[] args) throws SQLException {
        tempUserId = TestUtil.createTempUser();

        System.out.println("------------ F04. 여행 계획 CRUD 테스트를 시작합니다. ------------");
        tempTripId = 여행계획_생성하기();
        if (tempUserId == -1 || tempTripId == -1) throw new SQLException("실패");

        여행계획_모두_가져오기();
        여행계획_pk로_가져오기();
        여행계획_유저id로_가져오기();
        여행계획_수정하기();
         여행계획_제거하기();

        System.out.println("------------ F04. 여행 계획 테스트가 종료되었습니다. ------------");
        TestUtil.deleteTempUser(tempUserId);

    }

    private static Integer 여행계획_생성하기() {
        System.out.println("여행 계획을 생성합니다.");
        TripDto trip = new TripDto("temp", tempUserId,
                Date.valueOf("2024-09-10"),
                Date.valueOf("2024-09-14"),
                new Date(System.currentTimeMillis()));

        return tripDao.createTrip(trip);
    }

    private static void 여행계획_모두_가져오기() {
        System.out.println("\n모든 여행 계획을 가져옵니다.");
        List<TripDto> tripsDto = tripDao.searchTripsAll().getTrips();
        tripsDto.iterator().forEachRemaining(System.out::println);
    }

    private static void 여행계획_pk로_가져오기() {
        System.out.println("\n여행 계획을 pk로 불러옵니다. pk: " + tempTripId);
        System.out.println();
        TripDto tripDto = tripDao.findById(tempTripId);
        System.out.println("\n여행 계획 불러오기에 성공하였습니다.");
        System.out.println("불러온 TripDto: " + tripDto);
    }

    private static void 여행계획_유저id로_가져오기() {
        System.out.println("\n여행 계획을 유저 id로 불러옵니다. userId: " + tempUserId);
        TripsDto tripsDto = tripDao.findByMemberId(tempUserId);

        System.out.println("\n여행 계획 불러오기에 성공하였습니다.");
        System.out.println("----- 불러온 tripDto 목록 -----");
        tripsDto.getTrips().iterator().forEachRemaining(System.out::println);

    }



    private static void 여행계획_수정하기() {
        System.out.println("\n여행 계획을 수정합니다.");
        TripDto tripDto = tripDao.findById(tempTripId);

        System.out.println("수정 전 여행 이름: " + tripDto.getName());

        tripDto.setName("수정된 여행계획 1");
        tripDao.modifyTrip(tripDto);

        TripDto modified = tripDao.findById(tempTripId);
        System.out.println("수정 후 여행 이름: " + modified.getName());
    }
    private static void 여행계획_제거하기() {
        System.out.println("\n생성된 여행계획을 제거합니다.");
        tripDao.deleteTrip(tempTripId);
        System.out.println("여행 계획 제거에 성공하였습니다.");

    }

}
