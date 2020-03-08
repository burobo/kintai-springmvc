package com.burobo.springkintai.config;

import com.burobo.springkintai.ddd.domain.shugyo_kisoku.Shikobi;
import com.burobo.springkintai.ddd.domain.shugyo_kisoku.ShugyoKisoku;
import com.burobo.springkintai.ddd.domain.shugyo_kisoku.ShugyoKisokuRepository;
import com.burobo.springkintai.ddd.domain.shugyo_kisoku.kinmu_jikan_kisoku.KinmuJikanKisoku;
import com.burobo.springkintai.ddd.domain.shugyo_kisoku.kinmu_jikan_kisoku.KiteiKinmuJikan;
import com.burobo.springkintai.ddd.domain.shugyo_kisoku.kinmu_jikan_kisoku.ShigyoJikoku;
import com.burobo.springkintai.ddd.domain.shugyo_kisoku.kinmu_jikan_kisoku.ShugyoJikoku;
import com.burobo.springkintai.ddd.domain.shugyo_kisoku.kyukei_jikan_kisoku.KiteiKyukeiJikan;
import com.burobo.springkintai.ddd.domain.shugyo_kisoku.kyukei_jikan_kisoku.KiteiKyukeiKaishiJikoku;
import com.burobo.springkintai.ddd.domain.shugyo_kisoku.kyukei_jikan_kisoku.KiteiKyukeiShuryoJikoku;
import com.burobo.springkintai.ddd.domain.shugyo_kisoku.kyukei_jikan_kisoku.KyukeiJikanKisoku;
import com.burobo.springkintai.ddd.domain.util.Clock;
import jdk.vm.ci.meta.Local;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

@Component
public class DIConfig implements InitializingBean {
    private ShugyoKisokuRepository shugyoKisokuRepository;

    @Override
    public void afterPropertiesSet() throws java.lang.Exception {
    }

    @Autowired
    public void setShugyoKisokuRepository(ShugyoKisokuRepository shugyoKisokuRepository) {
        this.shugyoKisokuRepository = shugyoKisokuRepository;
        this.storeInitialShugyoKisoku();
        this.storeShugyoKisokuFrom20200315();
    }

    private void storeInitialShugyoKisoku() {
        ArrayList<KiteiKyukeiJikan> kiteiKyukeiJikanList = new ArrayList<KiteiKyukeiJikan>();
        kiteiKyukeiJikanList.add(
                new KiteiKyukeiJikan(
                        new KiteiKyukeiKaishiJikoku(LocalTime.of(12, 00)),
                        new KiteiKyukeiShuryoJikoku(LocalTime.of(13, 00))
                )
        );
        kiteiKyukeiJikanList.add(
                new KiteiKyukeiJikan(
                        new KiteiKyukeiKaishiJikoku(LocalTime.of(18, 00)),
                        new KiteiKyukeiShuryoJikoku(LocalTime.of(19, 00))
                )
        );
        kiteiKyukeiJikanList.add(
                new KiteiKyukeiJikan(
                        new KiteiKyukeiKaishiJikoku(LocalTime.of(21, 00)),
                        new KiteiKyukeiShuryoJikoku(LocalTime.of(22, 00))
                )
        );
        KyukeiJikanKisoku kyukeiJikanKisoku = new KyukeiJikanKisoku(kiteiKyukeiJikanList);
        KinmuJikanKisoku kinmuJikanKisoku = new KinmuJikanKisoku(new KiteiKinmuJikan(new ShigyoJikoku(LocalTime.of(9, 00)), new ShugyoJikoku(LocalTime.of(18, 00))));
        Shikobi lShikobi = new Shikobi(LocalDate.of(2000, 1, 1));
        ShugyoKisoku lShugyoKisoku = new ShugyoKisoku(kyukeiJikanKisoku, kinmuJikanKisoku, lShikobi);
        this.shugyoKisokuRepository.store(lShugyoKisoku);
    }

    private void storeShugyoKisokuFrom20200315() {
        ArrayList<KiteiKyukeiJikan> kiteiKyukeiJikanList = new ArrayList<KiteiKyukeiJikan>();
        kiteiKyukeiJikanList.add(
                new KiteiKyukeiJikan(
                        new KiteiKyukeiKaishiJikoku(LocalTime.of(12, 00)),
                        new KiteiKyukeiShuryoJikoku(LocalTime.of(13, 00))
                )
        );
        kiteiKyukeiJikanList.add(
                new KiteiKyukeiJikan(
                        new KiteiKyukeiKaishiJikoku(LocalTime.of(15, 00)),
                        new KiteiKyukeiShuryoJikoku(LocalTime.of(16, 00))
                )
        );
        kiteiKyukeiJikanList.add(
                new KiteiKyukeiJikan(
                        new KiteiKyukeiKaishiJikoku(LocalTime.of(18, 00)),
                        new KiteiKyukeiShuryoJikoku(LocalTime.of(19, 00))
                )
        );
        kiteiKyukeiJikanList.add(
                new KiteiKyukeiJikan(
                        new KiteiKyukeiKaishiJikoku(LocalTime.of(21, 00)),
                        new KiteiKyukeiShuryoJikoku(LocalTime.of(22, 00))
                )
        );
        KyukeiJikanKisoku kyukeiJikanKisoku = new KyukeiJikanKisoku(kiteiKyukeiJikanList);
        KinmuJikanKisoku kinmuJikanKisoku = new KinmuJikanKisoku(new KiteiKinmuJikan(new ShigyoJikoku(LocalTime.of(9, 00)), new ShugyoJikoku(LocalTime.of(18, 00))));
        // 新規則は施行日2030-03-15とする。
        Shikobi shikobi = new Shikobi(LocalDate.of(2030, 3, 15));
        ShugyoKisoku lShugyoKisoku = new ShugyoKisoku(kyukeiJikanKisoku, kinmuJikanKisoku, shikobi);
        this.shugyoKisokuRepository.store(lShugyoKisoku);
    }
}
