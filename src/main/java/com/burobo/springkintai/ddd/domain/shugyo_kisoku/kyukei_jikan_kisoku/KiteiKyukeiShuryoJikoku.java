package com.burobo.springkintai.ddd.domain.shugyo_kisoku.kyukei_jikan_kisoku;

import com.burobo.springkintai.ddd.domain.kintai.KyukeiShuryoJikoku;
import com.burobo.springkintai.ddd.domain.kintai.TaikinJikoku;

import java.time.LocalTime;

public class KiteiKyukeiShuryoJikoku {
    private LocalTime kiteiKyukeiShuryoJikoku;

    public KiteiKyukeiShuryoJikoku(LocalTime kiteiKyukeiShuryoJikoku) {
        this.setKiteiKyukeiShuryoJikoku(kiteiKyukeiShuryoJikoku);
    }

    public LocalTime getKiteiKyukeiShuryoJikoku() {
        return this.kiteiKyukeiShuryoJikoku;
    }

    private void setKiteiKyukeiShuryoJikoku(LocalTime kiteiKyukeiShuryoJikoku) {
        this.kiteiKyukeiShuryoJikoku = kiteiKyukeiShuryoJikoku;
    }

    public boolean isBefore(TaikinJikoku taikinJikoku) {
        return this.kiteiKyukeiShuryoJikoku.isBefore(taikinJikoku.getTaikinJikoku());
    }

    public KyukeiShuryoJikoku asKyukeShuryoJikoku() {
        return new KyukeiShuryoJikoku(this.kiteiKyukeiShuryoJikoku);
    }
}