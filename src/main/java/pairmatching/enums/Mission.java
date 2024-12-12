package pairmatching.enums;


import java.util.Arrays;

public enum Mission {
    CAR_RACING("자동차경주", Level.LEVEL1),
    LOTTO("로또", Level.LEVEL1),
    BASEBALL_GAME("숫자야구게임", Level.LEVEL1),

    SHOPPING_BAG("장바구니", Level.LEVEL2),
    PAYMENT("결제", Level.LEVEL2),
    SUBWAY_ROAD_MAP("지하철노선도", Level.LEVEL2),

    IMPROVE_PERFORMANCE("성능개선", Level.LEVEL4),
    DEPLOYMENT("배포", Level.LEVEL4);

    private String name;
    private Level level;

    Mission(final String name, final Level level) {
        this.name = name;
        this.level = level;
    }

    public static Mission matchMission(final String missionText, final Level level) {
        return Arrays.stream(values())
                .filter(mission -> mission.name.equals(missionText))
                .filter(mission -> mission.level == level)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.MISSION_NOT_FOUND.getMessage()));
    }


}
