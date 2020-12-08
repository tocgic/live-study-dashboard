package com.tocgic.exam.live_study_dashboard;

import com.tocgic.exam.live_study_dashboard.repo.Comment;
import com.tocgic.exam.live_study_dashboard.repo.Issue;
import com.tocgic.exam.live_study_dashboard.repo.Repository;

import java.util.*;

public class DashBoard {
    private final int issueTotalCount;
    private final Repository repository;

    public DashBoard(int issueTotalCount, Repository repository) {
        this.issueTotalCount = issueTotalCount;
        this.repository = repository;
    }

    public List<Participant> getParticipantList() {
        Map<String, Participant> statusMap = new HashMap<>();
        for (int issueNumber = 1; issueNumber <= issueTotalCount; issueNumber++) {
            Issue issue = repository.getIssue(issueNumber);
            if (issue != null) {
                for (Comment comment : issue.getComments()) {
                    String userName = comment.getLoginName();
                    Participant participant = statusMap.get(userName);
                    if (participant == null) {
                        participant = new Participant(userName);
                        statusMap.put(userName, participant);
                    }
                    participant.checkIn(issueNumber);
                }
            }
        }
        List<Participant> list = new ArrayList<>(statusMap.values());
        list.sort(Comparator.comparing(Participant::getName));
        return list;
    }

    public String toMarkDown(List<Participant> participants) {
        StringBuilder builder = new StringBuilder();
        StringBuilder line = new StringBuilder();
        builder.append("|참여자");
        line.append("|---");
        for (int issueNumber = 1; issueNumber <= issueTotalCount; issueNumber++) {
            builder.append("|").append(issueNumber).append("주차");
            line.append("|---");
        }
        builder.append("|참석율").append("|\n");
        line.append("|---").append("|\n");
        builder.append(line);

        for (Participant participant : participants) {
            builder.append("|").append(participant.getName());

            for (int issueNumber = 1; issueNumber <= issueTotalCount; issueNumber++) {
                builder.append("|");
                if (participant.isCheckIn(issueNumber)) {
                    builder.append("O");
                }
            }
            builder.append("|")
                    .append(String.format("%.2f%%", participant.getAttendanceRate(issueTotalCount)))
                    .append("|\n");
        }
        return builder.toString();
    }
}
