package com.justinefactory.writing.writers;

import com.justinefactory.domain.WritingInfo;
import com.justinefactory.writing.exceptions.ContentWritingException;

public interface ContentWriter<Content, WritingInfo> {

    void writeContent(Content content, WritingInfo info) throws ContentWritingException;

}
