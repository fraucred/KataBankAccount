package com.example.katabankaccount.provider;

import java.time.Instant;
import java.util.Date;

public class DateProvider {
    public Date nowDefaultDate() {
        return Date.from(Instant.now());
    }
}
