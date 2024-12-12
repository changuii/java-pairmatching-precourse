package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private final InputValidator inputValidator;

    public InputView(final InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public String readChoiceFunction() {
        String input = Console.readLine();
        inputValidator.validateChoiceFunction(input);
        return input;
    }
}
