package com.ssafy.post.model;

import java.util.ArrayList;
import java.util.List;

public class PostSummariesDto {
    List<PostSummaryDto> summaries = new ArrayList<>();

    public PostSummariesDto(List<PostSummaryDto> summariesDto) {
        this.summaries = summariesDto;
    }

    public PostSummariesDto() {
    }

    public List<PostSummaryDto> getSummaries() {
        return summaries;
    }

    public void setSummaries(List<PostSummaryDto> summaries) {
        this.summaries = summaries;
    }

    public void addPostSummary(PostSummaryDto summary) {
        summaries.add(summary);
    }
}
