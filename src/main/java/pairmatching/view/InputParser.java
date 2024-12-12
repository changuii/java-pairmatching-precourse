package pairmatching.view;

import pairmatching.dto.ChoiceDto;
import pairmatching.enums.Course;
import pairmatching.enums.ErrorMessage;
import pairmatching.enums.Level;
import pairmatching.enums.Mission;

public class InputParser {
    private static final String PAIR_MATCHING_DELIMITER = ", ";
    private static final String USER_ANSWER_YES = "네";
    private static final String USER_ANSWER_NO = "아니오";

    public ChoiceDto parsePairMatching(final String pairMatchingText) {
        String[] values = pairMatchingText.split(PAIR_MATCHING_DELIMITER);
        return ChoiceDto.of(
                parseCourse(values[0]),
                parseLevel(values[1]),
                parseMission(values[2], parseLevel(values[1]))
        );
    }

    private Course parseCourse(final String courseText) {
        return Course.matchCourse(courseText);
    }

    private Level parseLevel(final String levelText) {
        return Level.matchLevel(levelText);
    }

    private Mission parseMission(final String missionText, Level level) {
        return Mission.matchMission(missionText, level);
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
