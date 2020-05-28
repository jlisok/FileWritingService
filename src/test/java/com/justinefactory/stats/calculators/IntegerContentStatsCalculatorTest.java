package com.justinefactory.stats.calculators;

import com.justinefactory.stats.domain.Stats;
import com.justinefactory.stats.exceptions.StatsCalculatingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntegerContentStatsCalculatorTest {

    @Test
    void calculateStatsWhenContentEmpty() {
        IntegerContentStatsCalculator calculator = new IntegerContentStatsCalculator();
        assertThrows(StatsCalculatingException.class, () -> calculator.calculateStats(null));
    }


    @ParameterizedTest
    @MethodSource("calculateStatsWhenContentHas1ElementTestData")
    void calculateStatsWhenContentHas1Element(Collection<Integer> content, Stats<Integer> expectedContent) throws StatsCalculatingException {
        IntegerContentStatsCalculator calculator = new IntegerContentStatsCalculator();
        Stats<Integer> stats = calculator.calculateStats(content);
        assertEquals(stats, expectedContent);
    }

    static Stream<Arguments> calculateStatsWhenContentHas1ElementTestData() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(10), new Stats<>(1, 1, 10))
        );
    }

    @ParameterizedTest
    @MethodSource("calculateStatsWhenContentHasMoreElementsAndAllUniqueTestData")
    void calculateStatsWhenContentHasMoreElementsAndAllUnique(Collection<Integer> content, Stats<Integer> expectedContent) throws StatsCalculatingException {
        IntegerContentStatsCalculator calculator = new IntegerContentStatsCalculator();
        Stats<Integer> stats = calculator.calculateStats(content);
        assertEquals(stats, expectedContent);
    }

    static Stream<Arguments> calculateStatsWhenContentHasMoreElementsAndAllUniqueTestData() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(10, 50, 40, 20, 70, 130, 1), new Stats<>(7, 7, 130))
        );
    }

    @ParameterizedTest
    @MethodSource("calculateStatsWhenContentHasMoreNonUniqueElementsTestData")
    void calculateStatsWhenContentHasMoreNonUniqueElements(Collection<Integer> content, Stats<Integer> expectedContent) throws StatsCalculatingException {
        IntegerContentStatsCalculator calculator = new IntegerContentStatsCalculator();
        Stats<Integer> stats = calculator.calculateStats(content);
        assertEquals(stats, expectedContent);
    }

    static Stream<Arguments> calculateStatsWhenContentHasMoreNonUniqueElementsTestData() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(10, 20, 14, 20, 20, 12, 1), new Stats<>(7, 5, 20))
        );
    }

}