package pairmatching.domain;

import pairmatching.enums.Course;

public class Crew {
    private final Course course;
    private final String name;

    private Crew(final Course course, final String name) {
        this.course = course;
        this.name = name;
    }

    public static Crew of(final Course course, final String name) {
        return new Crew(course, name);
    }

    public boolean isMatch(final Crew crew) {
        return this.name.equals(crew.name);
    }
}
