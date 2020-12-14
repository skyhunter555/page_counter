package ru.syntez.page.counter.utils.counters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hwpf.HWPFDocument;

import java.io.*;

/**
 * Page counter for DOC file
 *
 * @author Skyhunter
 * @date 12.12.2020
 */
public class PageCounterDOC implements IPageCounter {

    private static Logger LOG = LogManager.getLogger(PageCounterDOC.class);

    @Override
    public Integer calculatePages(String fileName) throws IOException {

        LOG.info(String.format("Try to calculate DOC page count '%s'.", fileName));

        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            HWPFDocument docFile = new HWPFDocument(fileInputStream);
            return docFile.getSummaryInformation().getPageCount();
        }
    }
}
