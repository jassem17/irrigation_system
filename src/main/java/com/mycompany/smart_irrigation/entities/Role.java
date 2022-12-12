package com.mycompany.smart_irrigation.entities;

import java.util.Set;
import java.util.function.Supplier;

public enum Role implements Supplier<String> {
    ADMIN, USER;

    @Override
    public String get() {
        return this.name();
    }
}
