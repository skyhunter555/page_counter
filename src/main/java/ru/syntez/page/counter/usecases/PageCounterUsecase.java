package ru.syntez.page.counter.usecases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.syntez.page.counter.entities.FilePageCountResult;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Page counter main usecase
 *
 * @author Skyhunter
 * @date 12.12.2020
 */
@Service
public class PageCounterUsecase {

    private final FilePageCounterUsecase filePageCounterUsecase;
    private final FindFilesUsecase findFilesUsecase;

    private final String SYNC_LOCK = "FileProgressLock";
    private Set<String> filesInProgress = new HashSet<>();

    private static Logger LOG = LogManager.getLogger(PageCounterUsecase.class);

    public PageCounterUsecase(FilePageCounterUsecase filePageCounterUsecase, FindFilesUsecase findFilesUsecase) {
        this.filePageCounterUsecase = filePageCounterUsecase;
        this.findFilesUsecase = findFilesUsecase;
    }

    public List<FilePageCountResult> execute(String... args) throws IOException {

        List<FilePageCountResult> result = new ArrayList<>();

        List<File> docsForCounting = findFilesUsecase.execute(args);
        for (File document: docsForCounting) {
            synchronized (SYNC_LOCK) {
                String fileName = document.getAbsolutePath();
                if (!filesInProgress.contains(fileName)) {
                    CompletableFuture<FilePageCountResult> promise = filePageCounterUsecase.execute(fileName);
                    filesInProgress.add(fileName);
                    LOG.info(String.format("Added file %s to progress", fileName));
                    promise.handle((completedFile, ex) -> {
                        if (completedFile == null) {
                            LOG.warn("Problem", ex);
                            return "";
                        }
                        LOG.info(String.format("File %s was completed", completedFile.getFileName()));
                        synchronized(SYNC_LOCK) {
                            filesInProgress.remove(completedFile.getFileName());
                            LOG.info(String.format("Removed file %s from lock", completedFile.getFileName()));
                            result.add(completedFile);
                            return completedFile;
                        }
                    });
                }
            }
        }
        return result;
    }

}
