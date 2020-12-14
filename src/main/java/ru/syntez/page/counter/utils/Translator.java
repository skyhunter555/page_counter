package ru.syntez.page.counter.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class Translator {

    private final MessageSource messagesSource;

    public Translator(MessageSource messagesSource) {
        this.messagesSource = messagesSource;
    }

    public String translate(String label) {
        try {
            return messagesSource.getMessage(label, null, LocaleContextHolder.getLocale());
        } catch (Exception ex) {
            return label;
        }
    }

    public String translate(String label, Object... args) {
        try {
            return messagesSource.getMessage(label, args, LocaleContextHolder.getLocale());
        } catch (Exception ex) {
            return label;
        }
    }

}
