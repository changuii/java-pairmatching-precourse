package pairmatching.view;

import pairmatching.dto.PairMatchingChoiceDto;
import pairmatching.enums.Course;
import pairmatching.enums.ErrorMessage;
import pairmatching.enums.Level;

public class InputParser {
    private static final String PAIR_MATCHING_DELIMITER = ", ";
    private static final String USER_ANSWER_YES = "네";
    private static final String USER_ANSWER_NO = "아니오";

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

    public boolean parseAnser(final String answerText) {
        if (USER_ANSWER_YES.equals(answerText)) {
            return true;
        } else if (USER_ANSWER_NO.equals(answerText)) {
            return false;
        }
        throw new IllegalArgumentException(ErrorMessage.INPUT_ANSWER_INVALID.getMessage());
    }
}
