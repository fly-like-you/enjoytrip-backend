package com.ssafy.post.dao;

import com.ssafy.post.model.PostDto;
import com.ssafy.post.model.PostSummariesDto;
import com.ssafy.post.model.PostSummaryDto;
import com.ssafy.post.model.PostsDto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.DBUtil;

public class PostDaoImpl implements PostDao {
    private static final PostDaoImpl postDao = new PostDaoImpl();

    private PostDaoImpl() {}

    public static PostDaoImpl getPostDao() {
        return postDao;
    }

    @Override
    public Integer createPost(PostDto postDto) {
        String sql =
                  "INSERT INTO posts(title, content, created_at, updated_at, member_id) \n"
                + "VALUE (?, ?, ?, ?, ?);";
        try (
                Connection conn = DBUtil.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        sql,
                        Statement.RETURN_GENERATED_KEYS
                );
        ) {
            pstmt.setString(1, postDto.getTitle());
            pstmt.setString(2, postDto.getContent());
            pstmt.setDate(3, postDto.getCreatedAt());
            pstmt.setDate(4, postDto.getUpdatedAt());
            pstmt.setInt(5, postDto.getMemberId());

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

    @Override
    public PostsDto searchPostsAll() {
        String sql =
                "SELECT p.id, title, content, created_at, updated_at, m.nickname\n"
              + "FROM posts p INNER JOIN members m\n"
              + "ON p.member_id = m.id\n";

        PostsDto posts = new PostsDto();

        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("title");
                String author = rs.getString("nickname");
                String content = rs.getString("content");
                Date createdAt = rs.getDate("created_at");
                Date updatedAt = rs.getDate("updated_at");

                posts.addPost(new PostDto(id, name, author, content, createdAt, updatedAt));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    @Override
    public PostsDto findByMemberId(Integer memberId) {
        String sql =
                "SELECT p.id, title, content, created_at, updated_at, m.nickname\n"
                        + "FROM posts p INNER JOIN members m\n"
                        + "ON p.member_id = m.id\n"
                        + "WHERE m.id = ?";

        PostsDto posts = new PostsDto();

        try (
                Connection conn = DBUtil.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1,  memberId);

            try (
                    ResultSet rs = pstmt.executeQuery();
            ) {
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    String author = rs.getString("nickname");
                    Date createAt = rs.getDate("created_at");
                    Date updatedAt = rs.getDate("updated_at");

                    posts.addPost(new PostDto(id, title, author, content, createAt, updatedAt));
                }
                return posts;
            }

        }  catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public PostDto findById(Integer postId) {
        String sql =
                  "SELECT p.id, title, content, created_at, updated_at, m.nickname\n"
                + "FROM posts p INNER JOIN members m\n"
                + "ON p.member_id = m.id\n"
                + "WHERE p.id = ?";

        PostDto posts = new PostDto();

        try (
                Connection conn = DBUtil.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1,  postId);

            try (
                    ResultSet rs = pstmt.executeQuery();
            ) {
                if (rs.next()) {
                    Integer id = rs.getInt("id");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    String author = rs.getString("nickname");
                    Date createAt = rs.getDate("created_at");
                    Date updatedAt = rs.getDate("updated_at");

                    posts = new PostDto(id, title, author, content, createAt, updatedAt);
                }
                return posts;
            }

        }  catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void modifyPost(PostDto postDto) {
        String sql =
              "UPDATE posts\n"
            + "SET title = ?,\n"
            + "\tcontent = ?\n"
            + "WHERE id = ?";

        try (
                Connection conn = DBUtil.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, postDto.getTitle());
            pstmt.setString(2, postDto.getContent());
            pstmt.setInt(3, postDto.getId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePost(Integer postId) {
        String sql =
                "DELETE FROM posts\n"
                        + "WHERE id = ?;";

        try (
                Connection conn = DBUtil.getInstance().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setInt(1, postId);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PostSummariesDto searchPostSummariesAll() {
        String sql =
                "SELECT p.id, p.title, m.nickname, p.created_at " +
                "FROM posts p " +
                "INNER JOIN members m ON p.member_id = m.id";

        PostSummariesDto postSummaries = new PostSummariesDto();

        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                PostSummaryDto postSummary = new PostSummaryDto();
                postSummary.setId(rs.getString("id"));
                postSummary.setTitle(rs.getString("title"));
                postSummary.setNickname(rs.getString("nickname"));
                postSummary.setCreatedAt(rs.getDate("created_at"));

                postSummaries.addPostSummary(postSummary);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return postSummaries;
    }

    @Override
    public PostSummaryDto findPostSummaryById(Integer postId) {
        String sql = "SELECT p.id, p.title, m.nickname, p.created_at " +
                "FROM posts p " +
                "INNER JOIN members m ON p.member_id = m.id " +
                "WHERE p.id = ?";

        PostSummaryDto postSummary = null;

        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, postId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    postSummary = new PostSummaryDto();
                    postSummary.setId(rs.getString("id"));
                    postSummary.setTitle(rs.getString("title"));
                    postSummary.setNickname(rs.getString("nickname"));
                    postSummary.setCreatedAt(rs.getDate("created_at"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return postSummary;
    }

    @Override
    public PostSummariesDto findPostSummariesByMemberId(Integer memberId) {
        String sql = "SELECT p.id, p.title, m.nickname, p.created_at " +
                "FROM posts p " +
                "INNER JOIN members m ON p.member_id = m.id " +
                "WHERE m.id = ?";

        PostSummariesDto postSummaries = new PostSummariesDto();

        try (Connection conn = DBUtil.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, memberId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    PostSummaryDto postSummary = new PostSummaryDto();
                    postSummary.setId(rs.getString("id"));
                    postSummary.setTitle(rs.getString("title"));
                    postSummary.setNickname(rs.getString("nickname"));
                    postSummary.setCreatedAt(rs.getDate("created_at"));

                    postSummaries.addPostSummary(postSummary);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return postSummaries;
    }


}
