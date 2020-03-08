package com.burobo.springkintai.ddd.time.current;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Clock implements com.burobo.springkintai.ddd.domain.util.Clock {
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
