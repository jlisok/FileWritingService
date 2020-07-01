package com.justinefactory.sending.domain;

import com.justinefactory.domain.ThreeElemContent;
import com.justinefactory.stats.domain.Stats;
import com.justinefactory.writing.domain.Content;

public class ThreeElementContentAndStats extends ContentAndStats<ThreeElemContent> {

    public ThreeElementContentAndStats(Content<ThreeElemContent> ct, Stats<ThreeElemContent> st) throws IllegalArgumentException {
        super(ct, st);
    }
}
