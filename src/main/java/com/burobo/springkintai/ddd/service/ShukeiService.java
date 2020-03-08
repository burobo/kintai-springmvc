package com.burobo.springkintai.ddd.service;

import com.burobo.springkintai.ddd.domain.kintai.*;
import com.burobo.springkintai.ddd.domain.util.Clock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ShukeiService {
    private KintaiRepository kintaiRepository;
    private Clock clock;

    @Autowired
    public ShukeiService(KintaiRepository kintaiRepository, Clock clock) {
        this.kintaiRepository = kintaiRepository;
        this.clock = clock;
    }

    public ShukeiKekka handle() {
        KintaiShukeiService kintaiShukeiService = new KintaiShukeiService(this.kintaiRepository);
        LocalDate today = LocalDate.of(this.clock.now().getYear(), this.clock.now().getMonth(), this.clock.now().getDayOfMonth());
        Getsudo getsudo = Getsudo.getsudoOfTheDate(today);

        KinmuJikan shukeiKinmuJikan = kintaiShukeiService.shukeiKinmuJikan(getsudo);
        ZangyoJikan shukeiZangyoJikan = kintaiShukeiService.shukeiZangyoJikan(getsudo);

        return new ShukeiKekka(shukeiKinmuJikan, shukeiZangyoJikan);
    }
}