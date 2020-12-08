package com.tocgic.exam.live_study_dashboard.repo.github;

import com.tocgic.exam.live_study_dashboard.repo.Comment;
import com.tocgic.exam.live_study_dashboard.repo.Issue;
import com.tocgic.exam.live_study_dashboard.repo.Repository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GitHubRepositoryTest {
    static Repository repository;

    @BeforeAll
    static void create() {
        repository = new GitHubRepository("tocgic/live-study-dashboard");
        assertNotNull(repository);
    }

    @Test
    @DisplayName("live-study-dashboard 의 이슈 1번 확인")
    void getIssueTest() {
        Issue issue = repository.getIssue(1);
        assertNotNull(issue);
    }

    @Test
    @DisplayName("live-study-dashboard 의 이슈 1번 코멘트 3개 확인")
    void issue1CommentTest() {
        Issue issue = repository.getIssue(1);
        assertNotNull(issue);
        List<Comment> commentList = issue.getComments();
        assertEquals(commentList.size(), 3);
        for (Comment comment : commentList) {
            assertEquals(comment.getLoginName(), "tocgic");
        }
    }
}