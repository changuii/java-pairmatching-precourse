package pairmatching.controller;

import java.util.List;
import pairmatching.component.CrewGenerator;
import pairmatching.component.DtoConverter;
import pairmatching.domain.MatchingHistory;
import pairmatching.domain.PairMatchMachine;
import pairmatching.dto.PairDto;
import pairmatching.dto.PairMatchingChoiceDto;
import pairmatching.enums.ErrorMessage;
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
        PairMatchingChoiceDto choiceDto =
                retryHandler.retryUntilNotException(inputView::readPairMatchingChoice, outputView::printErrorMessage);
        if (pairMatchMachine
                .isExistsMatchingHistory(choiceDto.getCourse(), choiceDto.getLevel(), choiceDto.getMission())) {
            return retryPairMatching(choiceDto);
        }
        pairMatchMachine.matchingPair(choiceDto.getCourse(), choiceDto.getLevel(), choiceDto.getMission());
        return true;
    }

    private boolean retryPairMatching(final PairMatchingChoiceDto pairMatchingChoiceDto) {
        outputView.printAnswerIntroduce();
        boolean answer = retryHandler.retryUntilNotException(inputView::readAnswer, outputView::printErrorMessage);
        if (answer) {
            pairMatchMachine.matchingPair(pairMatchingChoiceDto.getCourse(), pairMatchingChoiceDto.getLevel(),
                    pairMatchingChoiceDto.getMission());
            pairMatchMachine.deleteMatchingHistoryBy(pairMatchingChoiceDto.getCourse(),
                    pairMatchingChoiceDto.getLevel(),
                    pairMatchingChoiceDto.getMission());
        }
        return answer;
    }

    private void pairMatchingQuery() {
        outputView.printPairMatchingIntroduce();
        PairMatchingChoiceDto pairMatchingChoiceDto =
                retryHandler.retryUntilNotException(inputView::readPairMatchingChoice, outputView::printErrorMessage);
        if (pairMatchMachine.isExistsMatchingHistory(pairMatchingChoiceDto.getCourse(),
                pairMatchingChoiceDto.getLevel(), pairMatchingChoiceDto.getMission())) {
            MatchingHistory matchingHistory = pairMatchMachine.findMatchingHistory(pairMatchingChoiceDto.getCourse(),
                    pairMatchingChoiceDto.getLevel(), pairMatchingChoiceDto.getMission());
            List<PairDto> pairDtos = dtoConverter.convertPairDtos(matchingHistory.getPairs());
            outputView.printPairMatchingQuery(pairDtos);
            return;
        }
        throw new IllegalArgumentException(ErrorMessage.MATCHING_NOT_FOUND.getMessage());
    }

    public void pairMatchingReset() {

    }
}
