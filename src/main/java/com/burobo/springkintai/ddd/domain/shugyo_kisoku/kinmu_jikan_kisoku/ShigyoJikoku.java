package com.burobo.springkintai.ddd.domain.shugyo_kisoku.kinmu_jikan_kisoku;

import com.burobo.springkintai.ddd.domain.kintai.ShukkinJikoku;

import java.time.LocalTime;

public class ShigyoJikoku {
    private LocalTime shigyoJikoku;

    public ShigyoJikoku(LocalTime shigyoJikoku) {
        this.setShigyoJikoku(shigyoJikoku);
    }

    public LocalTime getShigyoJikoku() {
        return this.shigyoJikoku;
    }

    private void setShigyoJikoku(LocalTime shigyoJikoku) {
        this.shigyoJikoku = shigyoJikoku;
    }

    public ShukkinJikoku toShukkinJikoku() {
        return new ShukkinJikoku(this.shigyoJikoku);
    }
}