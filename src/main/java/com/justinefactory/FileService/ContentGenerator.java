package com.justinefactory.FileService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

public interface ContentGenerator<T extends Serializable> {

    Collection<T> generateContent(int nLines) throws IOException;


}
