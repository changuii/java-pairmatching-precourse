package pairmatching.config;

import pairmatching.controller.PairMatchingController;
import pairmatching.view.InputValidator;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public abstract class PairMatchingConfig {
    public static PairMatchingController createController() {
        return new PairMatchingController(createInputView(), new OutputView());
    }

    private static InputView createInputView() {
        return new InputView(new InputValidator());
    }
}
