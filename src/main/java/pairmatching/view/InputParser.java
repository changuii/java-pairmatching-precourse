package pairmatching.view;

import pairmatching.dto.PairMatchingChoiceDto;
import pairmatching.enums.Course;
import pairmatching.enums.Level;

public class InputParser {
    private static final String PAIR_MATCHING_DELIMITER = ", ";

    public PairMatchingChoiceDto parsePairMatching(final String pairMatchingText) {
        String[] values = pairMatchingText.split(PAIR_MATCHING_DELIMITER);
        return PairMatchingChoiceDto.of(parseCourse(values[0]), parseLevel(values[1]), values[2]);
    }

    private Course parseCourse(final String courseText) {
        return Course.matchCourse(courseText);
    }

    private Level parseLevel(final String levelText) {
        return Level.matchLevel(levelText);
    }
}
