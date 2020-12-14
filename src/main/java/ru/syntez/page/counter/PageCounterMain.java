package ru.syntez.page.counter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import ru.syntez.page.counter.entities.FilePageCountResult;
import ru.syntez.page.counter.exceptions.PageCounterException;
import ru.syntez.page.counter.usecases.PageCounterUsecase;
import ru.syntez.page.counter.utils.Translator;

import java.util.*;
import static java.lang.System.exit;
import java.io.IOException;

/**
 * Main class for console running
 *
 * @author Skyhunter
 * @date 12.12.2020
 */
@SpringBootApplication
public class PageCounterMain implements CommandLineRunner {

    private final Translator translator;
    private final PageCounterUsecase pageCounterUsecase;
    private static Logger LOG = LogManager.getLogger(PageCounterMain.class);

    public PageCounterMain(Translator translator, PageCounterUsecase pageCounterUsecase) {
        this.translator = translator;
        this.pageCounterUsecase = pageCounterUsecase;
    }

    public static void main(String[] args) {
        SpringApplication.run(PageCounterMain.class, args);
    }

    @Override
    public void run(String... args) {
        LOG.info("Start process calculate pages...");
        try {
            List<FilePageCountResult> result = pageCounterUsecase.execute(args);

            System.out.println(String.format("Documents: %s", result.size()));
            System.out.println(String.format("Pages: %s", result.stream().mapToInt(FilePageCountResult::getPageCount).sum()));

        } catch (PageCounterException | IOException pe) {
            System.out.println(translator.translate("error.counting", pe.getMessage()));
            LOG.error(String.format("Error page counting: %s", pe.getMessage()));
        }

        LOG.info("Finish process calculate pages.");
        exit(0);
    }

}