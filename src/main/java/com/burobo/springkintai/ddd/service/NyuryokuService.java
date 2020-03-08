package com.burobo.springkintai.ddd.service;

import com.burobo.springkintai.ddd.domain.kintai.*;
import com.burobo.springkintai.ddd.domain.shugyo_kisoku.ShugyoKisoku;
import com.burobo.springkintai.ddd.domain.shugyo_kisoku.ShugyoKisokuRepository;
import com.burobo.springkintai.ddd.domain.util.Clock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NyuryokuService {
    private KintaiRepository kintaiRepository;
    private ShugyoKisokuRepository shugyoKisokuRepository;
    private Clock clock;

    @Autowired
    public NyuryokuService(KintaiRepository kintaiRepository, ShugyoKisokuRepository shugyoKisokuRepository,
                           Clock clock) {
        this.kintaiRepository = kintaiRepository;
        this.shugyoKisokuRepository = shugyoKisokuRepository;
        this.clock = clock;
    }

    /**
     * 通常
     * 
     * @param kinmubi
     * @param shukkinJikoku
     * @param taikinJikoku
     */
    public void handle(Kinmubi kinmubi, ShukkinJikoku shukkinJikoku, TaikinJikoku taikinJikoku) {
        ShugyoKisoku shugyoKisoku = this.shugyoKisokuRepository.shugyoKisokuOfKinmubi(kinmubi);
        Kintai kintai = Kintai.nyuryoku(kinmubi, shukkinJikoku, taikinJikoku,
                shugyoKisoku.calculateKinmuJikan(shukkinJikoku, taikinJikoku),
                shugyoKisoku.calculateZangyoJikan(shukkinJikoku, taikinJikoku), this.clock);
        this.kintaiRepository.store(kintai);
    }

    /**
     * 午前休
     * 
     * @param kinmubi
     * @param taikinJikoku
     */
    public void handle(Kinmubi kinmubi, TaikinJikoku taikinJikoku) {
        ShugyoKisoku shugyoKisoku = this.shugyoKisokuRepository.shugyoKisokuOfKinmubi(kinmubi);

        Kintai kintai = Kintai.nyuryoku(kinmubi, shugyoKisoku.shukkinJikokuOfYukyu(), taikinJikoku,
                shugyoKisoku.calculateKinmuJikanOfGozenkyu(taikinJikoku),
                shugyoKisoku.calculateZangyoJikanOfGozenkyu(taikinJikoku), this.clock);
        this.kintaiRepository.store(kintai);
    }

    /**
     * 午後休
     * 
     * @param kinmubi
     * @param shukkinJikoku
     */
    public void handle(Kinmubi kinmubi, ShukkinJikoku shukkinJikoku) {
        ShugyoKisoku shugyoKisoku = this.shugyoKisokuRepository.shugyoKisokuOfKinmubi(kinmubi);

        Kintai kintai = Kintai.nyuryoku(kinmubi, shukkinJikoku, shugyoKisoku.taikinJikokuOfYukyu(),
                shugyoKisoku.calculateKinmuJikanOfGogokyu(shukkinJikoku),
                shugyoKisoku.calculateZangyoJikanOfGogokyu(shukkinJikoku), this.clock);
        this.kintaiRepository.store(kintai);
    }

    /**
     * 休暇
     * 
     * @param kinmubi
     */
    public void handle(Kinmubi kinmubi) {
        ShugyoKisoku shugyoKisoku = this.shugyoKisokuRepository.shugyoKisokuOfKinmubi(kinmubi);

        Kintai kintai = Kintai.nyuryoku(kinmubi, shugyoKisoku.shukkinJikokuOfYukyu(), shugyoKisoku.taikinJikokuOfYukyu(),
                shugyoKisoku.calculateKinmuJikanOfKyuka(),
                shugyoKisoku.calculateZangyoJikanOfKyuka(), this.clock);
        this.kintaiRepository.store(kintai);
    }
}
