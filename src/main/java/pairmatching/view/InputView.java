package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.dto.ChoiceDto;

public class InputView {
    private final InputValidator inputValidator;
    private final InputParser inputParser;

    public InputView(final InputValidator inputValidator, final InputParser inputParser) {
        this.inputValidator = inputValidator;
        this.inputParser = inputParser;
    }

    public String readChoiceFunction() {
        String input = Console.readLine();
        inputValidator.validateChoiceFunction(input);
        return input;
    }

    public ChoiceDto readPairMatchingChoice() {
        String input = Console.readLine();
        inputValidator.validatePairMatchingChoiceText(input);
        return inputParser.parsePairMatching(input);
    }

    public boolean readAnswer() {
        String input = Console.readLine();
        inputValidator.validateAnswerText(input);
        return inputParser.parseAnser(input);
    }

}
