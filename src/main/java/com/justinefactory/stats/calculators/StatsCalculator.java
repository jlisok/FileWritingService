package com.justinefactory.stats.calculators;

import com.justinefactory.stats.domain.Stats;
import com.justinefactory.stats.exceptions.StatsCalculatingException;
import com.justinefactory.writing.domain.Content;

public interface StatsCalculator<ContentType> {

    Stats<ContentType> calculateStats(Content<ContentType> content) throws StatsCalculatingException;

}
