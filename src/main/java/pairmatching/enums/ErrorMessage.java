package pairmatching.enums;

public enum ErrorMessage {

    INPUT_CHOICE_FUNCTION_INVALID("기능 입력의 형식이 올바르지 않습니다."),
    INPUT_PAIR_MATCHING_CHOICE_INVALID("페어 매칭 선택 입력 형식이 올바르지 않습니다."),
    COURSE_NOT_FOUND("코스가 존재하지 않습니다."),
    LEVEL_NOT_FOUND("레벨이 존재하지 않습니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
