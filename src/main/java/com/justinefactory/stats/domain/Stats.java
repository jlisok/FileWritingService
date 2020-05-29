package com.justinefactory.stats.domain;

import java.util.Objects;

public class Stats<MaxContent> {

    private final Integer count;
    private final Integer distinctCount;
    private final MaxContent max;


    public Stats(Integer count, Integer distinctCount, MaxContent max) {
        this.count = count;
        this.distinctCount = distinctCount;
        this.max = max;
    }

    public Integer getDistinctCount() {
        return distinctCount;
    }

    public MaxContent getMax() {
        return max;
    }

    public Integer getCount() {
        return count;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stats that = (Stats) o;
        return Objects.equals(count, that.count) &&
                Objects.equals(distinctCount, that.distinctCount) &&
                Objects.equals(max, that.max);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, distinctCount, max);
    }

}
