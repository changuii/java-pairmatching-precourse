package pairmatching.domain;

import static pairmatching.handler.ExceptionHandler.handleException;
import static pairmatching.handler.ExceptionHandler.handleExceptionSupplier;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import pairmatching.enums.Course;
import pairmatching.enums.ErrorMessage;
import pairmatching.enums.Level;
import pairmatching.enums.Mission;

public class PairMatchMachine {
    private static final int TRY_COUNT_MAX = 3;
    private final List<MatchingHistory> matchingHistories;
    private final List<Crew> backendCrews;
    private final List<Crew> frontendCrews;

    private PairMatchMachine(final List<Crew> backendCrews, final List<Crew> frontendCrews) {
        matchingHistories = new ArrayList<>();
        this.backendCrews = backendCrews;
        this.frontendCrews = frontendCrews;
    }

    public static PairMatchMachine of(final List<Crew> backendCrews, final List<Crew> frontendCrews) {
        return new PairMatchMachine(backendCrews, frontendCrews);
    }

    public MatchingHistory matchingPair(final Course course, final Level level, final Mission mission) {
        return IntStream.range(0, TRY_COUNT_MAX)
                .mapToObj(i -> makeMatching(course, level, mission))
                .filter(history -> isValidMatching(history, level))
                .findFirst()
                .orElseThrow(() -> handleExceptionSupplier(ErrorMessage.MATCHING_IMPOSSIBLE));
    }

    public List<Crew> getCrewsByCourse(final Course course) {
        if (Course.BACKEND == course) {
            return backendCrews;
        }
        return frontendCrews;
    }

    public boolean isExistsMatchingHistory(final Course course, final Level level, final Mission mission) {
        return matchingHistories.stream()
                .anyMatch(matchingHistory -> matchingHistory.matchBy(course, level, mission));
    }

    public MatchingHistory findMatchingHistory(final Course course, final Level level, final Mission mission) {
        return matchingHistories.stream()
                .filter(matchingHistory -> matchingHistory.matchBy(course, level, mission))
                .findAny()
                .orElseThrow(() -> handleExceptionSupplier(ErrorMessage.MATCHING_NOT_FOUND));
    }

    public void deleteMatchingHistoryBy(final Course course, final Level level, final Mission mission) {
        MatchingHistory matchingHistory = findMatchingHistory(course, level, mission);
        matchingHistories.remove(matchingHistory);
    }

    public void deleteAllMatchingHistory() {
        matchingHistories.clear();
    }

    private List<MatchingHistory> findMatchingHistory(final Level level) {
        return matchingHistories.stream()
                .filter(matchingHistory -> matchingHistory.matchBy(level))
                .collect(Collectors.toList());
    }

    private boolean isValidMatching(final MatchingHistory matchingResult, final Level level) {
        List<MatchingHistory> sameLevelHistory = findMatchingHistory(level);
        return sameLevelHistory.stream()
                .anyMatch(history -> history.isDuplicatePair(matchingResult));
    }

    private MatchingHistory makeMatching(final Course course, final Level level, final Mission mission) {
        List<Crew> crews = getCrewsByCourse(course);
        return MatchingHistory.of(course, level, mission, makePairs(shuffleCrews(crews)));
    }

    private List<Pair> makePairs(final List<Crew> crews) {
        Queue<Crew> crew = new ArrayDeque<>(crews);
        List<Pair> pairs = new ArrayList<>();
        while (!crew.isEmpty()) {
            pairs.add(makePair(crew));
        }
        return pairs;
    }

    private Pair makePair(final Queue<Crew> crews) {
        if (crews.size() == 3) {
            return Pair.of(crews.poll(), crews.poll(), crews.poll());
        }
        return Pair.of(crews.poll(), crews.poll());
    }

    private List<Crew> shuffleCrews(List<Crew> crews) {
        return Randoms.shuffle(crews);
    }
}
