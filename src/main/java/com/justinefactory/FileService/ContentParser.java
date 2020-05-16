package com.justinefactory.FileService;

import java.io.Serializable;

interface ContentParser<T extends Serializable> {

    public String parseVars();

}
