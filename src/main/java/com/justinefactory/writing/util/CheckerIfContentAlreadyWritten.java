package com.justinefactory.writing.util;

import com.justinefactory.domain.PathData;
import com.justinefactory.writing.exceptions.ContentWritingException;

public interface CheckerIfContentAlreadyWritten {

    public void assureNotExist(PathData fileData) throws ContentWritingException;

}
