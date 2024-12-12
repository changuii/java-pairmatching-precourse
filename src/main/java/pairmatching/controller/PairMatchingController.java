package pairmatching.controller;

import java.util.List;
import pairmatching.component.CrewGenerator;
import pairmatching.component.DtoConverter;
import pairmatching.domain.Choice;
import pairmatching.domain.MatchingHistory;
import pairmatching.domain.PairMatchMachine;
import pairmatching.dto.PairDto;
import pairmatching.dto.ChoiceDto;
import pairmatching.enums.ErrorMessage;
import pairmatching.handler.ExceptionHandler;
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
    private final PairMatchMachine pairMatchMachine;
    private final DtoConverter dtoConverter;

    public PairMatchingController(final InputView inputView, final OutputView outputView,
                                  final RetryHandler retryHandler, final CrewGenerator crewGenerator,
                                  final DtoConverter dtoConverter) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.retryHandler = retryHandler;
        this.pairMatchMachine = PairMatchMachine.of(crewGenerator.generateBackEndCrew(),
                crewGenerator.generateFrontEndCrew());
        this.dtoConverter = dtoConverter;
    }

    public void run() {
        retryHandler.retryUntilNotExceptionAndTrue(this::choiceFunction, outputView::printErrorMessage);
    }

    private boolean choiceFunction() {
        outputView.printChoiceFunctionIntroduce();
        String userChoice = retryHandler.retryUntilNotException(inputView::readChoiceFunction,
                outputView::printErrorMessage);
        if (PAIR_MATCHING_FUNCTION.equals(userChoice)) {
            retryHandler.retryUntilTrue(this::pairMatching);
        } else if (PAIR_MATCHING_QUERY.equals(userChoice)) {
            pairMatchingQuery();
        } else if (PAIR_MATCHING_RESET.equals(userChoice)) {
            pairMatchingReset();
        }
        return QUIT.equals(userChoice);
    }

    private boolean pairMatching() {
        outputView.printPairMatchingIntroduce();
        Choice choice = inputChoice();
        if (choice.existsMatchingHistory(pairMatchMachine)) {
            return retryPairMatching(choice);
        }
        choice.matchingPair(pairMatchMachine);
        return true;
    }

    private boolean retryPairMatching(final Choice choice) {
        outputView.printAnswerIntroduce();
        boolean answer = retryHandler.retryUntilNotException(inputView::readAnswer, outputView::printErrorMessage);
        if (answer) {
            choice.matchingPair(pairMatchMachine);
            choice.deleteMatchingHistory(pairMatchMachine);
        }
        return answer;
    }

    private void pairMatchingQuery() {
        outputView.printPairMatchingIntroduce();
        Choice choice = inputChoice();
        if (choice.existsMatchingHistory(pairMatchMachine)) {
            MatchingHistory matchingHistory = choice.findMatching(pairMatchMachine);
            List<PairDto> pairDtos = dtoConverter.convertPairDtos(matchingHistory.getPairs());
            outputView.printPairMatchingQuery(pairDtos);
            return;
        }
        ExceptionHandler.handleException(ErrorMessage.MATCHING_NOT_FOUND);
    }

    private Choice inputChoice() {
        ChoiceDto choiceDto = retryHandler.retryUntilNotException(inputView::readPairMatchingChoice,
                outputView::printErrorMessage);
        return dtoConverter.convertChoice(choiceDto);
    }

    private void pairMatchingReset() {
        pairMatchMachine.deleteAllMatchingHistory();
        outputView.printPairMatchingReset();
    }
}
