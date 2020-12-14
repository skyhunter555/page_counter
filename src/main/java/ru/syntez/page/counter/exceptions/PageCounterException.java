package ru.syntez.page.counter.exceptions;
/**
 * Wrapper over RuntimeException. Includes additional options for formatting message text.
 *
 * @author Skyhunter
 * @date 05.11.2020
 */
public class PageCounterException extends RuntimeException {

    public PageCounterException(String message) {
	super(message);
    }

    public PageCounterException(String messageFormat, Object... messageArgs) {
	    super(String.format(messageFormat, messageArgs));
    }

    public PageCounterException(Throwable throwable) {
	super(throwable);
    }    
   
}
