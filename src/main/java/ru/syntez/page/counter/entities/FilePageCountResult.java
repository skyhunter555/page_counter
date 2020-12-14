package ru.syntez.page.counter.entities;

/**
 * File page count result
 *
 * @author Skyhunter
 * @date 12.12.2020
 */
public class FilePageCountResult {

    private final String fileName;
    private final Integer pageCount;

    public FilePageCountResult(String fileName, Integer pageCount) {
        this.fileName = fileName;
        this.pageCount = pageCount;
    }

    public String getFileName() {
        return fileName;
    }

    public Integer getPageCount() {
        return pageCount;
    }
}
