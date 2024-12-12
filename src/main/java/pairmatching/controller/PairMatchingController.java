package pairmatching.controller;

import pairmatching.view.InputView;

public class PairMatchingController {

    private final InputView inputView;

    public PairMatchingController(final InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        inputView.readChoiceFunction();
    }
}
