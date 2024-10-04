package test;

import com.ssafy.attraction.dao.AttractionDao;
import com.ssafy.attraction.dao.AttractionDaoImpl;
import com.ssafy.attraction.model.AttractionDto;
import com.ssafy.attraction.model.AttractionsDto;

public class AttractionDaoTest {
    static final AttractionDao attractionDao = AttractionDaoImpl.getInstance();
    static Integer tempAttractionId;

    public static void main(String[] args) {
        System.out.println("------------ F01 ~ F03. 관광지 DAO CRUD 테스트를 시작합니다. ------------");

        // 테스트 메서드 호출
        tempAttractionId = 관광지_생성하기();
        관광지_id로_가져오기(tempAttractionId);
        모든_관광지_가져오기();
        관광지_수정하기(tempAttractionId);
        관광지_삭제하기(tempAttractionId);
        관광지_contentType으로_가져오기(12);

        System.out.println("------------ F01 ~ F03. 관광지 DAO CRUD 테스트가 종료되었습니다. ------------");
    }

    // 관광지 생성 테스트 메서드
    public static Integer 관광지_생성하기() {
        AttractionDto attractionDto = new AttractionDto();
        attractionDto.setTitle("한라산 국립공원");
        attractionDto.setAddr("제주특별자치도 서귀포시");
        attractionDto.setType("12");
        attractionDto.setImgUrl("image_url_1.jpg");
        attractionDto.setLat(33.499621);
        attractionDto.setLng(126.531188);

        // DAO를 통해 관광지 생성 후 반환된 PK 값 저장
        Integer pk = attractionDao.createAttraction(attractionDto);
        System.out.println("관광지가 성공적으로 생성되었습니다. PK: " + pk);
        return pk;
    }

    // 관광지 ID로 조회 테스트 메서드
    public static void 관광지_id로_가져오기(Integer id) {
        AttractionDto attractionDto = attractionDao.searchAttractionById(id);
        if (attractionDto != null) {
            System.out.println("관광지를 성공적으로 가져왔습니다: " + attractionDto.getTitle());
        } else {
            System.out.println("관광지를 찾을 수 없습니다. ID: " + id);
        }
    }

    // 모든 관광지 조회 테스트 메서드
    public static void 모든_관광지_가져오기() {
        AttractionsDto attractions = attractionDao.searchAttractionsAll();
        if (attractions != null && !attractions.getAttractions().isEmpty()) {
            System.out.println("모든 관광지를 성공적으로 가져왔습니다. 총 " + attractions.getAttractions().size() + "개의 관광지가 있습니다.");
        } else {
            System.out.println("관광지를 찾을 수 없습니다.");
        }
    }

    // 관광지 수정 테스트 메서드
    public static void 관광지_수정하기(Integer id) {
        AttractionDto attractionDto = attractionDao.searchAttractionById(id);
        attractionDto.setTitle("수정된 한라산 국립공원");
        attractionDao.updateAttraction(attractionDto);
        System.out.println("관광지가 성공적으로 수정되었습니다: " + attractionDto.getTitle());

    }

    // 관광지 삭제 테스트 메서드
    public static void 관광지_삭제하기(Integer id) {
        attractionDao.deleteAttraction(id);
        System.out.println("관광지가 성공적으로 삭제되었습니다. ID: " + id);
    }

    // contentType ID로 관광지 조회 테스트 메서드
    public static void 관광지_contentType으로_가져오기(Integer contentTypeId) {
        AttractionsDto attractionsDto = attractionDao.searchAttractionsByContentTypeId(contentTypeId);
        System.out.println("Content Type ID에 해당하는 관광지를 성공적으로 가져왔습니다. 총 " + attractionsDto.getAttractions().size() + "개의 관광지가 있습니다.");

    }
}

