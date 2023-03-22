package com.example.retailer.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetRewardRequestTests {
    private final static String name = "tester";
    private final static Integer month = 1;

    @Test
    public void Tests() {
        GetRewardRequest request = GetRewardRequest.builder()
                .name(name)
                .month(month)
                .build();
        assertEquals(request.getName(), name);
        assertEquals(request.getMonth(), month);
    }
}
