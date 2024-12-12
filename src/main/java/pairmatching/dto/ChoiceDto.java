package pairmatching.dto;

import pairmatching.enums.Course;
import pairmatching.enums.Level;
import pairmatching.enums.Mission;

public class ChoiceDto {
    private final Course course;
    private final Level level;
    private final Mission mission;

    private ChoiceDto(final Course course, final Level level, final Mission mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public static ChoiceDto of(final Course course, final Level level, final Mission mission) {
        return new ChoiceDto(course, level, mission);
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return level;
    }

    public Mission getMission() {
        return mission;
    }
}
