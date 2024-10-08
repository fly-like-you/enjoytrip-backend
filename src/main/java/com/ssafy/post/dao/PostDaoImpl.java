package com.ssafy.post.dao;

import com.ssafy.post.model.PostDto;
import com.ssafy.post.model.PostSummariesDto;
import com.ssafy.post.model.PostSummaryDto;
import com.ssafy.post.model.PostsDto;
import java.sql.*;
import util.DBUtil;

public class PostDaoImpl implements PostDao {
    private static final PostDaoImpl postDao = new PostDaoImpl();

    private PostDaoImpl() {}

    public static PostDaoImpl getPostDao() {
        return postDao;
    }

    @Override
    public Integer createPost(PostDto postDto) {
        String sql = "INSERT INTO posts(title, content, created_at, member_id) VALUES (?, ?, ?, ?)";

        try (
            Connection conn = DBUtil.getInstance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            pstmt.setString(1, postDto.getTitle());
            pstmt.setString(2, postDto.getContent());
            pstmt.setTimestamp(3, postDto.getCreatedAt());
            pstmt.setInt(4, postDto.getMemberId());

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

        try (
            Connection conn = DBUtil.getInstance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("nickname");
                String content = rs.getString("content");
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp updatedAt = rs.getTimestamp("updated_at");

                posts.addPost(new PostDto(id, title, author, content, createdAt, updatedAt));
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
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, memberId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    String author = rs.getString("nickname");
                    Timestamp createdAt = rs.getTimestamp("created_at");
                    Timestamp updatedAt = rs.getTimestamp("updated_at");

                    posts.addPost(new PostDto(id, title, author, content, createdAt, updatedAt));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    @Override
    public PostDto findById(Integer postId) {
        String sql =
                  "SELECT p.id, title, p.member_id, content, created_at, updated_at, m.nickname\n"
                + "FROM posts p INNER JOIN members m\n"
                + "ON p.member_id = m.id\n"
                + "WHERE p.id = ?";

        try (
            Connection conn = DBUtil.getInstance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, postId);

            try (ResultSet rs = pstmt.executeQuery()) {
                PostDto postDto = new PostDto();
                if (rs.next()) {
                    postDto.setId(rs.getInt("id"));
                    postDto.setMemberId(rs.getInt("member_id"));
                    postDto.setTitle(rs.getString("title"));
                    postDto.setContent(rs.getString("content"));
                    postDto.setAuthor(rs.getString("nickname"));
                    postDto.setCreatedAt(rs.getTimestamp("created_at"));
                    postDto.setUpdatedAt(rs.getTimestamp("updated_at"));

                    return postDto;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void modifyPost(PostDto postDto) {
        String sql =
              "UPDATE posts\n"
            + "SET title = ?, content = ?, updated_at = ?\n"
            + "WHERE id = ?";

        try (
            Connection conn = DBUtil.getInstance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, postDto.getTitle());
            pstmt.setString(2, postDto.getContent());
            pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(4, postDto.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePost(Integer postId) {
        String sql = "DELETE FROM posts WHERE id = ?";

        try (
            Connection conn = DBUtil.getInstance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
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

        try (
            Connection conn = DBUtil.getInstance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()
        ) {
            while (rs.next()) {
                PostSummaryDto postSummary = new PostSummaryDto();
                postSummary.setId(rs.getString("id"));
                postSummary.setTitle(rs.getString("title"));
                postSummary.setNickname(rs.getString("nickname"));
                postSummary.setCreatedAt(null);

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

        try (
            Connection conn = DBUtil.getInstance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, postId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    PostSummaryDto postSummary = new PostSummaryDto();
                    postSummary.setId(rs.getString("id"));
                    postSummary.setTitle(rs.getString("title"));
                    postSummary.setNickname(rs.getString("nickname"));
                    postSummary.setCreatedAt(null);
                    return postSummary;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public PostSummariesDto findPostSummariesByMemberId(Integer memberId) {
        String sql = "SELECT p.id, p.title, m.nickname, p.created_at " +
                "FROM posts p " +
                "INNER JOIN members m ON p.member_id = m.id " +
                "WHERE m.id = ?";

        PostSummariesDto postSummaries = new PostSummariesDto();

        try (
            Connection conn = DBUtil.getInstance().getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, memberId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    PostSummaryDto postSummary = new PostSummaryDto();
                    postSummary.setId(rs.getString("id"));
                    postSummary.setTitle(rs.getString("title"));
                    postSummary.setNickname(rs.getString("nickname"));
                    postSummary.setCreatedAt(null);

                    postSummaries.addPostSummary(postSummary);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return postSummaries;
    }
}