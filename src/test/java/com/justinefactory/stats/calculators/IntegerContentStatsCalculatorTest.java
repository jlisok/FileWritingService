package com.justinefactory.stats.calculators;

import com.justinefactory.stats.domain.Stats;
import com.justinefactory.stats.exceptions.StatsCalculatingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntegerContentStatsCalculatorTest {

    @Test
    void calculateStatsWhenContentIsNull() {
        //when
        IntegerContentStatsCalculator calculator = new IntegerContentStatsCalculator();

        //then
        assertThrows(StatsCalculatingException.class, () -> calculator.calculateStats(null));
    }

    @Test
    void calculateStatsWhenContentEmpty() {
        //given
        List<Integer> content = new ArrayList<>();

        //when
        IntegerContentStatsCalculator calculator = new IntegerContentStatsCalculator();

        //then
        assertThrows(StatsCalculatingException.class, () -> calculator.calculateStats(content));
    }


    @Test
    void calculateStatsWhenContentHas1Element() throws StatsCalculatingException {
        //given
        List<Integer> content = List.of(10);
        Stats<Integer> expectedStats = new Stats<>(1, 1, 10);
        IntegerContentStatsCalculator calculator = new IntegerContentStatsCalculator();

        //when
        Stats<Integer> stats = calculator.calculateStats(content);

        //then
        assertEquals(expectedStats, stats);
    }


    @Test
    void calculateStatsWhenContentHasMoreElementsAndAllUnique() throws StatsCalculatingException {
        //given
        List<Integer> content = List.of(10, 50, 40, 20, 70, 130, 1);
        Stats<Integer> expectedStats = new Stats<>(7, 7, 130);
        IntegerContentStatsCalculator calculator = new IntegerContentStatsCalculator();

        //when
        Stats<Integer> stats = calculator.calculateStats(content);

        //then
        assertEquals(expectedStats, stats);
    }


    @Test
    void calculateStatsWhenContentHasMoreNonUniqueElements() throws StatsCalculatingException {
        //given
        List<Integer> content = List.of(10, 20, 14, 20, 20, 12, 1);
        Stats<Integer> expectedStats = new Stats<>(7, 5, 20);
        IntegerContentStatsCalculator calculator = new IntegerContentStatsCalculator();

        //when
        Stats<Integer> stats = calculator.calculateStats(content);

        //then
        assertEquals(expectedStats, stats);
    }


}