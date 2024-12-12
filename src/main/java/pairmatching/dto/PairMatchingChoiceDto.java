package pairmatching.dto;

import pairmatching.enums.Course;
import pairmatching.enums.Level;

public class PairMatchingChoiceDto {
    private final Course course;
    private final Level level;
    private final String mission;

    private PairMatchingChoiceDto(final Course course, final Level level, final String mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public static PairMatchingChoiceDto of(final Course course, final Level level, final String mission) {
        return new PairMatchingChoiceDto(course, level, mission);
    }
}
