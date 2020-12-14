package ru.syntez.page.counter.utils.counters;

import ru.syntez.page.counter.entities.FileExtensionEnum;
import ru.syntez.page.counter.exceptions.PageCounterException;
import ru.syntez.page.counter.utils.ExtensionFileUtils;

import java.util.Optional;

/**
 * Create page counter by file extensions
 *
 * @author Skyhunter
 * @date 12.12.2020
 */
public class PageCounterFactory {

    public IPageCounter buildParser(String fileName) throws PageCounterException {

        Optional<String> extensionOptional = ExtensionFileUtils.getExtensionFile(fileName);

        if (!extensionOptional.isPresent()) {
            throw new PageCounterException(String.format("Extension undefined for file %s", fileName));
        }

        FileExtensionEnum extension = FileExtensionEnum.parseCode(extensionOptional.get().toLowerCase());
        switch (extension) {
            case DOC:
                return new PageCounterDOC();
            case DOCX:
                return new PageCounterDOCX();
            case PDF:
                return new PageCounterPDF();
            case XLS:
                return new PageCounterXLS();
        }

        throw new PageCounterException(String.format("Counting not yet implemented for file with extension %s", extension.getCode()));
    }

}
