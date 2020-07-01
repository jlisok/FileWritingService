package com.justinefactory.writing.util;

import com.justinefactory.writing.exceptions.ContentWritingException;

public interface CheckerIfContentAlreadyWritten<WritingInfo> {

    void assureNotExist(WritingInfo writingInfo) throws ContentWritingException;

}
