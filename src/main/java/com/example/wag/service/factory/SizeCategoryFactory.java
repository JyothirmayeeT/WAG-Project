package com.example.wag.service.factory;

import java.util.List;
import java.util.Objects;

import com.example.wag.service.strategy.LargeSizeStrategy;
import com.example.wag.service.strategy.MediumSizeStrategy;
import com.example.wag.service.strategy.SizeCategoryStrategy;
import com.example.wag.service.strategy.SmallSizeStrategy;

public class SizeCategoryFactory {
	private static final List<SizeCategoryStrategy> strategies = List.of(
            new SmallSizeStrategy(),
            new MediumSizeStrategy(),
            new LargeSizeStrategy()
    );

    public static String getSizeCategory(long population) {
        return strategies.stream()
                .map(strategy -> strategy.determineSizeCategory(population))
                .filter(Objects::nonNull)
                .findFirst()
                .orElse("UNKNOWN");
    }

}
