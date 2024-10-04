package com.ssafy.post.model;

import java.sql.Date;

public class PostSummaryDto {
    // 작성자, 제목, 생성된 시간만 알 수 있으면 댐
    private String id;
    private String nickname;
    private String title;
    private Date createdAt;

    public PostSummaryDto(String id, String nickname, String title, Date createdAt) {
        this.id = id;
        this.nickname = nickname;
        this.title = title;
        this.createdAt = createdAt;
    }

    public PostSummaryDto(String nickname, String title, Date createdAt) {
        this.nickname = nickname;
        this.title = title;
        this.createdAt = createdAt;
    }

    public PostSummaryDto() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "PostSummary(요약된 게시글)[" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", title='" + title + '\'' +
                ", createdAt=" + createdAt +
                ']';
    }
}
