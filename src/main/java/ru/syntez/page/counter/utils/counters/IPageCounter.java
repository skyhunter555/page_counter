package ru.syntez.page.counter.utils.counters;

import ru.syntez.page.counter.exceptions.PageCounterException;

import java.io.IOException;
/**
 * Page counter methods
 *
 * @author Skyhunter
 * @date 12.12.2020
 */
public interface IPageCounter {

    Integer calculatePages(String fileName) throws IOException, PageCounterException;

}
