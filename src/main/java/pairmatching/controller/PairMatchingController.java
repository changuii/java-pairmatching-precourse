package pairmatching.controller;

import pairmatching.handler.RetryHandler;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {
    private static final String PAIR_MATCHING_FUNCTION = "1";
    private static final String PAIR_MATCHING_QUERY = "2";
    private static final String PAIR_MATCHING_RESET = "3";
    private static final String QUIT = "Q";

    private final InputView inputView;
    private final OutputView outputView;
    private final RetryHandler retryHandler;

    public PairMatchingController(final InputView inputView, final OutputView outputView,
                                  final RetryHandler retryHandler) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.retryHandler = retryHandler;
    }

    public void run() {
        retryHandler.retryUntilTrue(this::choiceFunction);
    }

    private boolean choiceFunction() {
        outputView.printChoiceFunctionIntroduce();
        String userChoice = retryHandler.retryUntilNotException(inputView::readChoiceFunction,
                outputView::printErrorMessage);
        if (PAIR_MATCHING_FUNCTION.equals(userChoice)) {

        } else if (PAIR_MATCHING_QUERY.equals(userChoice)) {

        } else if (PAIR_MATCHING_RESET.equals(userChoice)) {

        }
        return QUIT.equals(userChoice);
    }
}
