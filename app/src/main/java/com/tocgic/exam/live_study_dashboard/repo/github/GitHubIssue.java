package com.tocgic.exam.live_study_dashboard.repo.github;

import com.tocgic.exam.live_study_dashboard.repo.Comment;
import com.tocgic.exam.live_study_dashboard.repo.Issue;
import org.kohsuke.github.GHIssue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GitHubIssue implements Issue {
    private GHIssue issue;

    public GitHubIssue(GHIssue issue) {
        this.issue = issue;
    }

    @Override
    public int getCommentCount() {
        return issue.getCommentsCount();
    }

    @Override
    public List<Comment> getComments() {
        List<Comment> comments = new ArrayList<>();
        try {
            issue.getComments().forEach(ghIssueComment -> comments.add(new GitHubComment(ghIssueComment)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return comments;
    }
}
