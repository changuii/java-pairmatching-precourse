package pairmatching.dto;

import java.util.List;

public class PairDto {
    private final List<String> crews;

    private PairDto(final List<String> crews) {
        this.crews = crews;
    }

    public static PairDto from(final List<String> crews) {
        return new PairDto(crews);
    }

    public List<String> getCrews() {
        return crews;
    }
}
