package pairmatching.component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import pairmatching.enums.ErrorMessage;

public class MarkdownFileParser implements FileParser {
    @Override
    public List<String> parse(final String path) {
        try {
            return Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            throw new IllegalArgumentException(ErrorMessage.FILE_INVALID.getMessage());
        }
    }
}
