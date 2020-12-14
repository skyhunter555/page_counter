package ru.syntez.page.counter.usecases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.syntez.page.counter.utils.ExtensionFileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FindFilesUsecase {

    private static Logger LOG = LogManager.getLogger(FindFilesUsecase.class);

    public List<File> execute(String... pathList) throws IOException {

        List<File> result = new ArrayList<>();
        for (String arg : pathList) {
            Path filePath = Paths.get(arg);
            if (!Files.isDirectory(filePath)) {
                LOG.info(String.format("Path must be a directory! '%s'.", filePath));
                continue;
            }
            List<File> fileResults;
            try (Stream<Path> pathWalker = Files.walk(filePath)) {
                fileResults = pathWalker
                        .filter(Files::isReadable)      // read permission
                        .filter(Files::isRegularFile)   // is a file
                        .filter(p -> ExtensionFileUtils.isFileExtensionPresent(p.getFileName().toString()))
                        .map(Path::toFile)
                        .collect(Collectors.toList());
            }
            result.addAll(fileResults);
        }
        return result;
    }

}
