package pairmatching.enums;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    public static Course matchCourse(final String courseText) {
        return Arrays.stream(values())
                .filter(course -> course.name.equals(courseText))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.COURSE_NOT_FOUND.getMessage()));
    }
}
