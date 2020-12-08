package com.tocgic.exam.live_study_dashboard.repo;

import java.util.List;

public interface Issue {
    int getCommentCount();
    List<Comment> getComments();
}
