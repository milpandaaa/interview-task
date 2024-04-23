package com.mycompany.interviewtask.utils.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Status {
    SILVER("silver"),GOLD("gold"), PLUTINUM("plutinum");
    private final String name;

    Status(String name) {
        this.name = name;
    }

    public static Status part(String part) {
        return Optional.ofNullable(part).map(Status::value).orElse(null);
    }

    private static Status value( String value) {
        return Arrays.stream(values())
                .filter(responsePart -> responsePart.name().equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }

    public String getName() {
        return name;
    }
}
