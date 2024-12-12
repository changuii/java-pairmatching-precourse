package pairmatching.handler;

import pairmatching.enums.ErrorMessage;

public abstract class ExceptionHandler {

    public static void handleException(final ErrorMessage errorMessage) {
        throw new IllegalArgumentException(errorMessage.getMessage());
    }

    public static IllegalArgumentException handleExceptionSupplier(final ErrorMessage errorMessage) {
        return new IllegalArgumentException(errorMessage.getMessage());
    }
}
