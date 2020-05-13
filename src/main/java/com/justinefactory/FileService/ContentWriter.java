package com.justinefactory.FileService;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

interface ContentWriter<T extends Serializable> {

    public void writeContent(Collection<T> content) throws IOException, CouldNotWrite2FileAlreadyExists;

}
