package com.burobo.springkintai.controllers;

import com.burobo.springkintai.ddd.domain.kintai.Kinmubi;
import com.burobo.springkintai.ddd.domain.kintai.ShukeiKekka;
import com.burobo.springkintai.ddd.domain.kintai.ShukkinJikoku;
import com.burobo.springkintai.ddd.domain.kintai.TaikinJikoku;
import com.burobo.springkintai.ddd.service.NyuryokuService;
import com.burobo.springkintai.ddd.service.ShukeiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Controller
public class KintaiController {
    private NyuryokuService nyuryokuService;
    private ShukeiService shukeiService;

    @Autowired
    public KintaiController(NyuryokuService nyuryokuService, ShukeiService shukeiService) {
        this.nyuryokuService = nyuryokuService;
        this.shukeiService = shukeiService;
    }

    @RequestMapping(path = "/kintais", method = RequestMethod.POST)
    public String nyuryoku(
            @RequestParam("kinmubi") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate reqKinmubi,
            @RequestParam("shukkin_jikoku") String reqShukkinJikoku,
            @RequestParam("taikin_jikoku") String reqTaikinJikoku
    ) {
        DateTimeFormatter shukkinJikokuFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter taikinJikokuFormatter = DateTimeFormatter.ofPattern("HH:mm");
        Kinmubi kinmubi = new Kinmubi(reqKinmubi);
        ShukkinJikoku shukkinJikoku = new ShukkinJikoku(LocalTime.parse(reqShukkinJikoku, shukkinJikokuFormatter));
        TaikinJikoku taikinJikoku = new TaikinJikoku(LocalTime.parse(reqTaikinJikoku, taikinJikokuFormatter));
        this.nyuryokuService.handle(kinmubi, shukkinJikoku, taikinJikoku);
        return "redirect:/kintais";
    }

    @RequestMapping(path = "/kintais", method = RequestMethod.GET)
    public String shukei(ModelMap modelMap) {
        ShukeiKekka shukeiKekka = this.shukeiService.handle();
        long shukeiKinmuJikanSeconds = shukeiKekka.getShukeiKinmuJikan().getKinmuJikan().getSeconds();
        long shukeiZangyoJikanSeconds = shukeiKekka.getShukeiZangyoJikan().getZangyoJikan().getSeconds();
        modelMap.addAttribute("shukeiKinmuJikanHH", shukeiKinmuJikanSeconds / 3600);
        modelMap.addAttribute("shukeiKinmuJikanMM", (shukeiKinmuJikanSeconds % 3600) / 60);
        modelMap.addAttribute("shukeiZangyoJikanHH",  shukeiZangyoJikanSeconds / 3600);
        modelMap.addAttribute("shukeiZangyoJikanMM", (shukeiZangyoJikanSeconds % 3600) / 60);
        return "kintais";
    }
}
