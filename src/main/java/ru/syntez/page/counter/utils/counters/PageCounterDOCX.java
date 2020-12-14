package ru.syntez.page.counter.utils.counters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import ru.syntez.page.counter.exceptions.PageCounterException;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Page counter for DOCX file
 *
 * @author Skyhunter
 * @date 12.12.2020
 */
public class PageCounterDOCX implements IPageCounter {

    private static Logger LOG = LogManager.getLogger(PageCounterDOCX.class);

    @Override
    public Integer calculatePages(String fileName) throws IOException {

        LOG.info(String.format("Try to calculate DOCX page count '%s'.", fileName));

        try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
            XWPFDocument docxFile = new XWPFDocument(OPCPackage.open(fileInputStream));
            return docxFile.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
        } catch (InvalidFormatException e) {
            throw new PageCounterException("InvalidFormatException on calculate DOCX page count", e);
        }
    }
}
