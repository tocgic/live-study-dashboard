package com.tocgic.exam.live_study_dashboard.repo.github;

import com.tocgic.exam.live_study_dashboard.repo.Issue;
import com.tocgic.exam.live_study_dashboard.repo.Repository;
import org.kohsuke.github.GHIssue;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

import java.io.IOException;

public class GitHubRepository implements Repository {
    private GHRepository ghRepository;

    public GitHubRepository(String repoName) {
        try {
            // export GITHUB_OAUTH=~~~~~
            GitHub github = GitHubBuilder.fromEnvironment().build();
            ghRepository = github.getRepository(repoName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Issue getIssue(int issueNumber) {
        if (ghRepository != null) {
            try {
                GHIssue ghIssue = ghRepository.getIssue(issueNumber);
                if (ghIssue != null) {
                    return new GitHubIssue(ghIssue);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
