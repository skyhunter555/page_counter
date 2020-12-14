package ru.syntez.page.counter.utils.counters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

/**
 * Page counter for PDF file
 *
 * @author Skyhunter
 * @date 12.12.2020
 */
public class PageCounterPDF implements IPageCounter {

    private static Logger LOG = LogManager.getLogger(PageCounterPDF.class);

    @Override
    public Integer calculatePages(String fileName) throws IOException {

        LOG.info(String.format("Try to calculate PDF page count '%s'.", fileName));

        try (PDDocument document = PDDocument.load(new File(fileName))) {
            return document.getPages().getCount();
        }
    }
}
