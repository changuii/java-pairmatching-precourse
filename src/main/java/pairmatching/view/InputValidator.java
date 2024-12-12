package pairmatching.view;

import java.util.regex.Pattern;
import pairmatching.enums.ErrorMessage;
import pairmatching.enums.OutputMessage;

public class InputValidator {

    private static final String CHOICE_FUNCTION_REGEX = "^[1|2|3|Q]$";
    private static final String PAIR_MATCHING_CHOICE_REGEX = "^([가-힣]*, )([가-힣]{2}[0-9]*, )([가-힣]*)$";
    private static final String ANSWER_REGEX = "^[가-힣]{1,3}$";
    private static final Pattern CHOICE_FUNCTION_FORMAT = Pattern.compile(CHOICE_FUNCTION_REGEX);
    private static final Pattern PAIR_MATCHING_CHOICE_FORMAT = Pattern.compile(PAIR_MATCHING_CHOICE_REGEX);
    private static final Pattern ANSWER_FORMAT = Pattern.compile(ANSWER_REGEX);

    public void validateChoiceFunction(final String choiceFunction) {
        if (!CHOICE_FUNCTION_FORMAT.matcher(choiceFunction).matches()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_CHOICE_FUNCTION_INVALID.getMessage());
        }
    }

    public void validatePairMatchingChoiceText(final String pairMatchingChoiceText) {
        if (!PAIR_MATCHING_CHOICE_FORMAT.matcher(pairMatchingChoiceText).matches()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_PAIR_MATCHING_CHOICE_INVALID.getMessage());
        }
    }

    public void validateAnswerText(final String answerText) {
        if (!ANSWER_FORMAT.matcher(answerText).matches()) {
            throw new IllegalArgumentException(ErrorMessage.INPUT_ANSWER_INVALID.getMessage());
        }
    }
}
