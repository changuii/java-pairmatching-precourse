package pairmatching.view;

import java.util.List;
import java.util.stream.Collectors;
import pairmatching.dto.PairDto;
import pairmatching.enums.OutputMessage;

public class OutputView {
    public void printChoiceFunctionIntroduce() {
        print(OutputMessage.CHOICE_FUNCTION_INTRODUCE);
    }

    public void printPairMatchingIntroduce() {
        print(OutputMessage.PAIR_MATCHING_INTRODUCE);
    }

    public void printAnswerIntroduce() {
        print(OutputMessage.ANSWER_INTRODUCE);
    }

    public void printPairMatchingQuery(final List<PairDto> pairDtos) {
        print(OutputMessage.PAIR_MATCHING_QUERY);
        pairDtos.forEach(this::printPairDto);
    }

    private void printPairDto(final PairDto pairDto) {
        print(pairDto.getCrews().stream()
                .collect(Collectors.joining(OutputMessage.PAIR_MATCHING_DELIMITER.toString())));
    }

    public void printPairMatchingReset() {
        print(OutputMessage.PAIR_MATCHING_RESET);
    }

    public void printErrorMessage(final IllegalArgumentException customException) {
        print(customException.getMessage());
    }

    private void print(final Object message, final Object... values) {
        System.out.println(formatMessage(message.toString(), values));
    }

    private String formatMessage(final Object formatMessage, final Object... values) {
        return String.format(formatMessage.toString(), values);
    }
}
