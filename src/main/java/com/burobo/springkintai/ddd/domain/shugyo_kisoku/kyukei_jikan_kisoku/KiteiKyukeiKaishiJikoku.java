package com.burobo.springkintai.ddd.domain.shugyo_kisoku.kyukei_jikan_kisoku;

import com.burobo.springkintai.ddd.domain.kintai.KyukeiKaishiJikoku;
import com.burobo.springkintai.ddd.domain.kintai.TaikinJikoku;

import java.time.LocalTime;

public class KiteiKyukeiKaishiJikoku {
    private LocalTime kiteiKyukeiKaishiJikoku;

    public KiteiKyukeiKaishiJikoku(LocalTime kiteiKyukeiKaishiJikoku) {
        this.setKiteiKyukeiKaishiJikoku(kiteiKyukeiKaishiJikoku);
    }

    public LocalTime getKiteiKyukeiKaishiJikoku() {
        return this.kiteiKyukeiKaishiJikoku;
    }

    private void setKiteiKyukeiKaishiJikoku(LocalTime kiteiKyukeiKaishiJikoku) {
        this.kiteiKyukeiKaishiJikoku = kiteiKyukeiKaishiJikoku;
    }

    public boolean isBefore(TaikinJikoku taikinJikoku) {
        return this.kiteiKyukeiKaishiJikoku.isBefore(taikinJikoku.getTaikinJikoku());
    }

    public KyukeiKaishiJikoku asKyukeiKaishiJikoku() {
        return new KyukeiKaishiJikoku(this.kiteiKyukeiKaishiJikoku);
    }
}