package com.justinefactory.sending.domain;

import com.justinefactory.stats.domain.Stats;
import com.justinefactory.writing.domain.Content;

public class IntegerAndStats extends ContentAndStats<Integer> {


    public IntegerAndStats(Content<Integer> ct, Stats<Integer> st) throws IllegalArgumentException {
        super(ct, st);
    }

}
