package ru.syntez.page.counter.entities;

/**
 * File extensions used for counting
 *
 * @author Skyhunter
 * @date 12.12.2020
 */
public enum FileExtensionEnum {
    
    DOC("doc"),
    DOCX("docx"),
    PDF("pdf"),
    XLSX("xlsx"),   //Not implemented yet
    XLS("xls"),     //Not implemented yet
    XLTX("xltx"),   //Not implemented yet
    PPT("ppt"),     //Not implemented yet
    PPTX("pptx");   //Not implemented yet

    private final String code;

    FileExtensionEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static FileExtensionEnum parseCode(String code) {
        for (FileExtensionEnum each : values()) {
            if (each.code.equals(code)) {
                return each;
            }
        }
        throw new ru.syntez.page.counter.exceptions.PageCounterException(String.format("Extension %s not found.", code));
    }
}
