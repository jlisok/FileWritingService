package com.justinefactory.stats.calculators;

import com.justinefactory.stats.domain.Stats;
import com.justinefactory.stats.exceptions.StatsCalculatingException;
import com.justinefactory.writing.domain.ContentStorage;

public interface StatsCalculator<Content> {

    Stats<Content> calculateStats(ContentStorage<Content> content) throws StatsCalculatingException;

}
