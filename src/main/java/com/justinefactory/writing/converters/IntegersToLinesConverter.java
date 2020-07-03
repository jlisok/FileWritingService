package com.justinefactory.writing.converters;

import com.justinefactory.writing.domain.ContentStorage;
import com.justinefactory.writing.domain.ContentReadyForPlainWriter;
import com.justinefactory.writing.exceptions.ContentConversion2ReadyToWriteException;

public class IntegersToLinesConverter implements ContentConverter<ContentStorage<Integer>, ContentReadyForPlainWriter> {

    @Override
    public ContentReadyForPlainWriter convertContent(ContentStorage<Integer> content) throws ContentConversion2ReadyToWriteException {
        checkIfContentNull(content);
        ContentReadyForPlainWriter readyToWriteContent = new ContentReadyForPlainWriter();
        for (Integer item : content.getContent()) {
            readyToWriteContent.addContent(item.toString());
        }
        return readyToWriteContent;
    }

    private void checkIfContentNull(ContentStorage<Integer> content) throws ContentConversion2ReadyToWriteException {
        if (content == null || content.isEmpty()) {
            throw new ContentConversion2ReadyToWriteException("Converting content " + content + " into csvFile line - failed. Content is empty or null.");
        }
    }

}
