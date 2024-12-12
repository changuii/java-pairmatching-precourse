package pairmatching.enums;

public enum OutputMessage {

    CHOICE_FUNCTION_INTRODUCE(
            "기능을 선택하세요.\n"
                    + "1. 페어 매칭\n"
                    + "2. 페어 조회\n"
                    + "3. 페어 초기화\n"
                    + "Q. 종료"
    ),
    PAIR_MATCHING_INTRODUCE(
            "과정, 레벨, 미션을 선택하세요.\n"
                    + "ex) 백엔드, 레벨1, 자동차경주"
    ),
    ANSWER_INTRODUCE(
            "매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n"
                    + "네 | 아니오"
    ),
    PAIR_MATCHING_RESET("초기화 되었습니다.");


    private final String message;

    OutputMessage(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}

