package com.tocgic.exam.live_study_dashboard.repo.github;

import com.tocgic.exam.live_study_dashboard.repo.Comment;
import org.kohsuke.github.GHIssueComment;
import org.kohsuke.github.GHUser;

import java.io.IOException;

public class GitHubComment implements Comment {
    GHIssueComment comment;

    public GitHubComment(GHIssueComment comment) {
        this.comment = comment;
    }

    @Override
    public String getLoginName() {
        try {
            GHUser user = comment.getUser();
            if (user != null) {
                return user.getLogin();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getMessage() {
        return comment.getBody();
    }
}
