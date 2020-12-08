package com.tocgic.exam.live_study_dashboard;

import com.tocgic.exam.live_study_dashboard.repo.github.GitHubRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DashBoardTest {
    static DashBoard dashBoard;
    @BeforeAll
    @Test
    static void createDashBoard() {
        dashBoard = new DashBoard(5, new GitHubRepository("tocgic/live-study-dashboard"));
        assertNotNull(dashBoard);
    }

    @Test
    void getParticipantListTest() {
        List<Participant> list = dashBoard.getParticipantList();
        assertNotNull(list);
        assertEquals(list.size(), 1);
        Participant participant = list.get(0);
        assertEquals(participant.getName(), "tocgic");
        assertEquals(participant.getAttendanceRate(5), 20f);
    }

    @Test
    @DisplayName("더미 데이터를 통한, 마크다운코드 테스트")
    void toMarkDownTest() {
        List<Participant> arrayList = new ArrayList<>();
        Participant participant;
        participant = new Participant("user5");
        participant.checkIn(1);
        participant.checkIn(2);
        participant.checkIn(3);
        participant.checkIn(4);
        participant.checkIn(5);
        arrayList.add(participant);
        participant = new Participant("user4");
        participant.checkIn(1);
        participant.checkIn(2);
        participant.checkIn(3);
        participant.checkIn(4);
        arrayList.add(participant);
        participant = new Participant("user3");
        participant.checkIn(1);
        participant.checkIn(2);
        participant.checkIn(3);
        arrayList.add(participant);
        participant = new Participant("user2");
        participant.checkIn(1);
        participant.checkIn(2);
        arrayList.add(participant);
        participant = new Participant("user1");
        participant.checkIn(1);
        arrayList.add(participant);
        String markDown = dashBoard.toMarkDown(arrayList);
        System.out.println(markDown);
    }
}