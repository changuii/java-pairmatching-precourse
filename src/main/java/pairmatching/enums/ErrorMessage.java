package pairmatching.enums;

public enum ErrorMessage {

    FILE_INVALID("파일이 존재하지 않거나 유효하지 않습니다."),
    INPUT_CHOICE_FUNCTION_INVALID("기능 입력의 형식이 올바르지 않습니다."),
    INPUT_PAIR_MATCHING_CHOICE_INVALID("페어 매칭 선택 입력 형식이 올바르지 않습니다."),
    INPUT_ANSWER_INVALID("사용자의 응답이 올바르지 않습니다."),
    COURSE_NOT_FOUND("코스가 존재하지 않습니다."),
    LEVEL_NOT_FOUND("레벨이 존재하지 않습니다."),
    MISSION_NOT_FOUND("미션이 존재하지 않습니다."),
    MATCHING_NOT_FOUND("매칭 이력이 없습니다."),
    MATCHING_IMPOSSIBLE("매칭에 실패했거나 매칭이 불가능합니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
