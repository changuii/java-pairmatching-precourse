package pairmatching.config;

import pairmatching.component.CrewGenerator;
import pairmatching.component.MarkdownFileParser;
import pairmatching.controller.PairMatchingController;
import pairmatching.handler.RetryHandler;
import pairmatching.view.InputParser;
import pairmatching.view.InputValidator;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public abstract class PairMatchingConfig {
    public static PairMatchingController createController() {
        return new PairMatchingController(createInputView(), new OutputView(), new RetryHandler(),
                createCrewGenerator());
    }

    private static InputView createInputView() {
        return new InputView(new InputValidator(), new InputParser());
    }

    private static CrewGenerator createCrewGenerator() {
        return new CrewGenerator(new MarkdownFileParser());
    }
}
