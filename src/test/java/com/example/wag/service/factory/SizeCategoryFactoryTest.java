package com.example.wag.service.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.wag.service.factory.SizeCategoryFactory;

class SizeCategoryFactoryTest {

    @Test
    void testGetSizeCategory() {
        assertEquals("SMALL", SizeCategoryFactory.getSizeCategory(500_000));
        assertEquals("MEDIUM", SizeCategoryFactory.getSizeCategory(5_000_000));
        assertEquals("LARGE", SizeCategoryFactory.getSizeCategory(15_000_000));
        assertEquals("UNKNOWN", SizeCategoryFactory.getSizeCategory(-1));
    }
}

