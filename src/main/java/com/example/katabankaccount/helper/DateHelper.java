package com.example.katabankaccount.helper;

import java.time.Instant;
import java.util.Date;

public class DateHelper {
    public Date now() {
        return Date.from(Instant.now());
    }
}
