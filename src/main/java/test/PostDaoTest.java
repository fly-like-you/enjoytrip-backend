package test;

import com.mysql.cj.util.TestUtils;
import com.ssafy.post.dao.PostDao;
import com.ssafy.post.dao.PostDaoImpl;
import com.ssafy.post.model.PostDto;
import com.ssafy.post.model.PostSummariesDto;
import com.ssafy.post.model.PostSummaryDto;
import com.ssafy.post.model.PostsDto;
import java.sql.Date;
import java.util.List;
import util.TestUtil;

public class PostDaoTest {
    static Integer tempUserId;
    static Integer postId;
    static final PostDao postDao = PostDaoImpl.getPostDao();

    public static void main(String[] args) {
        tempUserId = TestUtil.createTempUser();

        System.out.println("------------ F10. 게시판 CRUD 테스트를 시작합니다. ------------");
        postId = 게시판_생성하기();
        게시판_모두_가져오기();
        게시판_pk로_가져오기();
        게시판_유저id로_가져오기();
        게시판_수정하기();

        요약게시판_모두_가져오기();
        요약게시판_pk로_가져오기();
        요약게시판_유저id로_가져오기();
        게시판_삭제하기();
        System.out.println("------------ F10. 게시판 테스트가 종료되었습니다. ------------");
        TestUtil.deleteTempUser(tempUserId);
    }

    private static Integer 게시판_생성하기() {
        PostDto post = new PostDto(
                tempUserId,
                "temp",
                "temp content",
                new Date(System.currentTimeMillis()),
                new Date(System.currentTimeMillis())
        );

        return postDao.createPost(post);
    }

    private static void 게시판_모두_가져오기() {
        System.out.println("\n게시글을 모두 조회합니다.");
        List<PostDto> posts = postDao.searchPostsAll().getPosts();
        posts.iterator().forEachRemaining(System.out::println);
    }

    private static void 게시판_pk로_가져오기() {
        System.out.println("\n게시글을 pk로 가져옵니다.");
        PostDto posts = postDao.findById(postId);
        System.out.println(posts);
    }

    private static void 게시판_유저id로_가져오기() {
        System.out.println("\n게시글을 유저id로 가져옵니다.");

        PostsDto posts = postDao.findByMemberId(tempUserId);
        posts.getPosts().iterator().forEachRemaining(System.out::println);
    }

    private static void 게시판_수정하기() {
        System.out.println("\n게시글을 수정합니다.");

        PostDto posts = postDao.findById(postId);

        System.out.println("수정 전 게시글 제목: " + posts.getTitle());

        posts.setTitle("수정된 게시글 제목");
        postDao.modifyPost(posts);

        PostDto modified = postDao.findById(postId);
        System.out.println("수정 후 게시글 제목: " + modified.getTitle());
    }

    private static void 게시판_삭제하기() {
        System.out.println("\n게시글을 삭제합니다.");
        postDao.deletePost(postId);
    }

    private static void 요약게시판_모두_가져오기() {
        System.out.println("\n요약게시글을 모두 조회합니다.");
        List<PostSummaryDto> summaries = postDao.searchPostSummariesAll().getSummaries();
        summaries.iterator().forEachRemaining(System.out::println);
    }
    private static void 요약게시판_pk로_가져오기() {
        System.out.println("\n요약게시글을 pk로 가져옵니다.");
        PostSummaryDto posts = postDao.findPostSummaryById(postId);
        System.out.println(posts);
    }
    private static void 요약게시판_유저id로_가져오기() {
        System.out.println("\n요약게시글을 유저id로 가져옵니다.");
        PostSummariesDto posts = postDao.findPostSummariesByMemberId(tempUserId);
        posts.getSummaries().iterator().forEachRemaining(System.out::println);
    }
}
