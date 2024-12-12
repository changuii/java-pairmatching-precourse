package pairmatching.dto;

import java.util.List;

public class PairMatchingDto {
    private final List<PairDto> pairs;

    private PairMatchingDto(final List<PairDto> pairs) {
        this.pairs = pairs;
    }

    public static PairMatchingDto from(final List<PairDto> pairs) {
        return new PairMatchingDto(pairs);
    }
}
