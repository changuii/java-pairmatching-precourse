package pairmatching.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Pair {
    private static final int PAIR_COUNT_MIN = 2;
    private static final int PAIR_DUPLICATE_COUNT = 2;
    private final List<Crew> pairCrew;

    private Pair(final Crew... crews) {
        validate(crews);
        this.pairCrew = Arrays.asList(crews);
    }

    public static Pair of(final Crew... crews) {
        return new Pair(crews);
    }

    private void validate(final Crew... crews) {
        if (crews.length < PAIR_COUNT_MIN) {
            throw new IllegalArgumentException();
        }
    }

    public boolean isMatch(final Pair pair) {
        return pair.pairCrew.stream()
                .filter(crew -> this.isContains(crew))
                .count() >= PAIR_DUPLICATE_COUNT;
    }

    private boolean isContains(final Crew target) {
        return pairCrew.stream()
                .anyMatch(crew -> crew.isMatch(target));
    }

    public List<String> getPairCrewNames() {
        return pairCrew.stream()
                .map(Crew::getName)
                .collect(Collectors.toList());
    }
}
