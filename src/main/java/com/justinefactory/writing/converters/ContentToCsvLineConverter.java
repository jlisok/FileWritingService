package com.justinefactory.writing.converters;

import com.justinefactory.writing.exceptions.ContentConvertingToLineException;

public interface ContentToCsvLineConverter<T> {

    public String[] varsToCsvLine(T content) throws ContentConvertingToLineException;

}
