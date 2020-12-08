package com.tocgic.exam.live_study_dashboard;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

/**
 * 참여자
 */
public class Participant {
    private String name;
    private Map<Integer, Boolean> statusMap;

    public Participant(String name) {
        this.name = name;
        this.statusMap = new HashMap<>();
    }

    @Nonnull
    public String getName() {
        if (name == null) {
            return "";
        }
        return name;
    }

    public void checkIn(int issueNumber) {
        statusMap.put(issueNumber, true);
    }

    public boolean isCheckIn(int issueNumber) {
        return statusMap.get(issueNumber) != null ? statusMap.get(issueNumber) : false;
    }

    public float getAttendanceRate(int issueCount) {
        if (issueCount < 1) {
            return 0f;
        }
        int attendanceCount = statusMap.size();
        if (attendanceCount < 1) {
            return 0f;
        }
        return (float)(100 * attendanceCount) / issueCount;

    }
}
