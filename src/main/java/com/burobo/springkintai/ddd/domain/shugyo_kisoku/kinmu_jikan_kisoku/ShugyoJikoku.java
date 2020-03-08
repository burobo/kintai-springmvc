package com.burobo.springkintai.ddd.domain.shugyo_kisoku.kinmu_jikan_kisoku;

import com.burobo.springkintai.ddd.domain.kintai.TaikinJikoku;

import java.time.LocalTime;

public class ShugyoJikoku {
    private LocalTime shugyoJikoku;

    public ShugyoJikoku(LocalTime shugyoJikoku) {
        this.setShugyoJikoku(shugyoJikoku);
    }

    public LocalTime getShugyoJikoku() {
        return this.shugyoJikoku;
    }

    private void setShugyoJikoku(LocalTime shugyoJikoku) {
        this.shugyoJikoku = shugyoJikoku;
    }

    public TaikinJikoku toTaikinJikoku() {
        return new TaikinJikoku(this.shugyoJikoku);
    }
}