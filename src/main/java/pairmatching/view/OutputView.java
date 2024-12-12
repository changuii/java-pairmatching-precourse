package pairmatching.view;

import pairmatching.enums.OutputMessage;

public class OutputView {


    public void printChoiceFunctionIntroduce() {
        print(OutputMessage.CHOICE_FUNCTION_INTRODUCE);
    }

    private void print(final Object message, final Object... values) {
        System.out.println(formatMessage(message.toString(), values));
    }

    private String formatMessage(final Object formatMessage, final Object... values) {
        return String.format(formatMessage.toString(), values);
    }
}
