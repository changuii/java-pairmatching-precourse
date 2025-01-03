package pairmatching.component;

import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.Choice;
import pairmatching.domain.Pair;
import pairmatching.dto.ChoiceDto;
import pairmatching.dto.PairDto;

public class DtoConverter {

    public List<PairDto> convertPairDtos(final List<Pair> pairs) {
        return pairs.stream()
                .map(this::convertPairDto)
                .collect(Collectors.toList());
    }

    private PairDto convertPairDto(final Pair pair) {
        return PairDto.from(pair.getPairCrewNames());
    }

    public Choice convertChoice(final ChoiceDto choiceDto) {
        return Choice.of(choiceDto.getCourse(), choiceDto.getLevel(), choiceDto.getMission());
    }
}
