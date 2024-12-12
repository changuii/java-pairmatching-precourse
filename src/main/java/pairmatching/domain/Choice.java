package pairmatching.domain;

import pairmatching.enums.Course;
import pairmatching.enums.Level;
import pairmatching.enums.Mission;

public class Choice {
    private final Course course;
    private final Level level;
    private final Mission mission;

    private Choice(final Course course, final Level level, final Mission mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public static Choice of(final Course course, final Level level, final Mission mission) {
        return new Choice(course, level, mission);
    }

    public boolean existsMatchingHistory(final PairMatchMachine pairMatchMachine) {
        return pairMatchMachine.existsMatchingHistory(course, level, mission);
    }

    public void matchingPair(final PairMatchMachine pairMatchMachine) {
        pairMatchMachine.matchingPair(course, level, mission);
    }

    public void deleteMatchingHistory(final PairMatchMachine pairMatchMachine) {
        pairMatchMachine.deleteMatchingHistoryBy(course, level, mission);
    }

    public MatchingHistory findMatching(final PairMatchMachine pairMatchMachine) {
        return pairMatchMachine.findMatchingHistory(course, level, mission);
    }
}
