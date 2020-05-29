package com.justinefactory.stats.calculators;

import com.justinefactory.stats.domain.Stats;
import com.justinefactory.stats.exceptions.StatsCalculatingException;

import java.util.Collection;

interface StatsCalculator<OutContent> {

    Stats<OutContent> calculateStats(Collection<OutContent> content) throws StatsCalculatingException;

}
