package pairmatching.component;

import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.Crew;
import pairmatching.enums.Course;

public class CrewGenerator {
    private static final String BACKEND_FILE_PATH = "src/main/resources/backend-crew.md";
    private static final String FRONTEND_FILE_PATH = "src/main/resources/frontend-crew.md";
    private final FileParser fileParser;

    public CrewGenerator(final FileParser fileParser) {
        this.fileParser = fileParser;
    }

    public List<Crew> generateBackEndCrew() {
        return fileParser.parse(BACKEND_FILE_PATH).stream()
                .map(crewName -> parseCrew(Course.BACKEND, crewName))
                .collect(Collectors.toList());
    }

    public List<Crew> generateFrontEndCrew() {
        return fileParser.parse(FRONTEND_FILE_PATH).stream()
                .map(crewName -> parseCrew(Course.FRONTEND, crewName))
                .collect(Collectors.toList());
    }

    private Crew parseCrew(final Course course, final String crewName) {
        return Crew.of(course, crewName);
    }
}
