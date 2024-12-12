package pairmatching;

import pairmatching.config.PairMatchingConfig;
import pairmatching.controller.PairMatchingController;

public class Application {
    public static void main(String[] args) {
        PairMatchingController controller = PairMatchingConfig.createController();
        controller.run();
    }
}
