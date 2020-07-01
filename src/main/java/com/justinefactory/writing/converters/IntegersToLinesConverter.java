package com.justinefactory.writing.converters;

import com.justinefactory.writing.domain.Content;
import com.justinefactory.writing.domain.PlainContent;
import com.justinefactory.writing.exceptions.ContentConversion2ReadyToWriteException;

public class IntegersToLinesConverter implements ContentConverter<Content<Integer>, PlainContent> {

    @Override
    public PlainContent convertContent(Content<Integer> content) throws ContentConversion2ReadyToWriteException {
        checkIfContentNull(content);
        PlainContent readyToWriteContent = new PlainContent();
        for (Integer item : content.getContent()) {
            readyToWriteContent.addContent(item.toString());
        }
        return readyToWriteContent;
    }

    private void checkIfContentNull(Content<Integer> content) throws ContentConversion2ReadyToWriteException {
        if (content == null || content.isEmpty()) {
            throw new ContentConversion2ReadyToWriteException("Converting content " + content + " into csvFile line - failed. Content is empty or null.");
        }
    }

}
