package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import pairmatching.enums.Course;
import pairmatching.enums.Level;
import pairmatching.enums.Mission;

public class MatchingHistory {
    private final Course course;
    private final Level level;
    private final Mission mission;
    private final List<Pair> pairs;

    private MatchingHistory(final Course course, final Level level, final Mission mission, final List<Pair> pairs) {
        this.course = course;
        this.level = level;
        this.mission = mission;
        this.pairs = pairs;
    }

    public static MatchingHistory of(final Course course, final Level level, final Mission mission,
                                     final List<Pair> pairs) {
        return new MatchingHistory(course, level, mission, pairs);
    }

    public boolean matchBy(final Course course, final Level level, final Mission mission) {
        return this.course == course && this.level == level && this.mission == mission;
    }

    public boolean matchBy(final Level level) {
        return this.level == level;
    }

    public boolean isDuplicatePair(final MatchingHistory matchingHistory) {
        return matchingHistory.pairs.stream()
                .anyMatch(pair -> this.isContains(pair));
    }

    private boolean isContains(final Pair target) {
        return pairs.stream()
                .anyMatch(pair -> pair.isMatch(target));
    }

    public List<Pair> getPairs() {
        return pairs;
    }
}
