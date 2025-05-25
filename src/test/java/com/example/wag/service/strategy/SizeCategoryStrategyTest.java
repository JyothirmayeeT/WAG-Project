package com.example.wag.service.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.example.wag.service.strategy.LargeSizeStrategy;
import com.example.wag.service.strategy.MediumSizeStrategy;
import com.example.wag.service.strategy.SmallSizeStrategy;

class SizeCategoryStrategyTest {

    @Test
    void testSmallSizeStrategy() {
        SmallSizeStrategy strategy = new SmallSizeStrategy();
        assertEquals("SMALL", strategy.determineSizeCategory(500_000));
        assertNull(strategy.determineSizeCategory(2_000_000));
    }

    @Test
    void testMediumSizeStrategy() {
        MediumSizeStrategy strategy = new MediumSizeStrategy();
        assertEquals("MEDIUM", strategy.determineSizeCategory(5_000_000));
        assertNull(strategy.determineSizeCategory(15_000_000));
    }

    @Test
    void testLargeSizeStrategy() {
        LargeSizeStrategy strategy = new LargeSizeStrategy();
        assertEquals("LARGE", strategy.determineSizeCategory(15_000_000));
        assertNull(strategy.determineSizeCategory(500_000));
    }
}
