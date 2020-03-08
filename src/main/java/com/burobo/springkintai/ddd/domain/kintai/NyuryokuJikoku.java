package com.burobo.springkintai.ddd.domain.kintai;

import com.burobo.springkintai.ddd.domain.util.Clock;

import java.time.LocalDateTime;

public class NyuryokuJikoku {
    private LocalDateTime nyuryokuJikoku;

    public NyuryokuJikoku(LocalDateTime nyuryokuJikoku) {
        this.setNyuryokuJikoku(nyuryokuJikoku);
    }

    public static NyuryokuJikoku now(Clock clock) {
        return new NyuryokuJikoku(clock.now());
    }

    public LocalDateTime getNyuryokuJikoku() {
        return this.nyuryokuJikoku;
    }

    private void setNyuryokuJikoku(LocalDateTime nyuryokuJikoku) {
        this.nyuryokuJikoku = nyuryokuJikoku;
    }
}