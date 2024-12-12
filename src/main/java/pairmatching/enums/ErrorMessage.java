package pairmatching.enums;

public enum ErrorMessage {

    INPUT_CHOICE_FUNCTION_INVALID("기능 입력의 형식이 올바르지 않습니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
