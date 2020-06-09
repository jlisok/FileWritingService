package com.justinefactory.writing.converters;

import com.justinefactory.writing.domain.ContentStorage;

public interface ContentToCsvLinesConverter<Content> extends ContentConverter<Content, ContentStorage<String[]>> {

}
