package com.justinefactory.FileService;

import java.io.Serializable;

interface ContentToCsvLine<T extends Serializable> {

    public String[] varsToCsvLine();

}
