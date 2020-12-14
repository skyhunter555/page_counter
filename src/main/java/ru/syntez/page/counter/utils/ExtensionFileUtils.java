package ru.syntez.page.counter.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import ru.syntez.page.counter.entities.FileExtensionEnum;
import java.util.Optional;

/**
 * Extension file utils
 *
 * @author Skyhunter
 * @date 12.12.2020
 */
public class ExtensionFileUtils {

    public static Optional<String> getExtensionFile(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public static boolean isFileExtensionPresent(String fileName) {
        for (FileExtensionEnum extension: FileExtensionEnum.values()) {
            if (ExtensionFileUtils.getExtensionFile(fileName).isPresent()) {
                return true;
            }
        }
        return false;
    }

}
