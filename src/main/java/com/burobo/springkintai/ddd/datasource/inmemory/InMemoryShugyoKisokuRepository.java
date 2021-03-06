package com.burobo.springkintai.ddd.datasource.inmemory;

import com.burobo.springkintai.ddd.domain.kintai.Kinmubi;
import com.burobo.springkintai.ddd.domain.shugyo_kisoku.Shikobi;
import com.burobo.springkintai.ddd.domain.shugyo_kisoku.ShugyoKisoku;
import com.burobo.springkintai.ddd.domain.shugyo_kisoku.ShugyoKisokuRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Component
public class InMemoryShugyoKisokuRepository implements ShugyoKisokuRepository {
    private ArrayList<ShugyoKisoku> shugyoKisokus = new ArrayList<ShugyoKisoku>();

    public ShugyoKisoku shugyoKisokuOfKinmubi(Kinmubi kinmubi) {
        ArrayList<ShugyoKisoku> sortedShugyoKisokus = shugyoKisokus;
        
        for(int i = 0; i < sortedShugyoKisokus.size(); i++) {
            for (int j = 0; j < sortedShugyoKisokus.size() - 1 - i; j++) {
                if (sortedShugyoKisokus.get(j + 1).isShikobiEqualsOrBefore(sortedShugyoKisokus.get(j).getShikobi())) {
                    Collections.swap(sortedShugyoKisokus, j, j + 1);
                }
            }
        }

        ShugyoKisoku shugyoKisokuOfKinmubi = null;
        for(int i = 0; i < sortedShugyoKisokus.size(); i++) {
            if (sortedShugyoKisokus.get(i).isShikobiEqualsOrBefore(new Shikobi(kinmubi.getKinmubi()))) {
                shugyoKisokuOfKinmubi = sortedShugyoKisokus.get(i);
            }
        }

        if (shugyoKisokuOfKinmubi == null) {
            throw new RuntimeException("就業規則がありません。");
        }

        return shugyoKisokuOfKinmubi;
    }

    public void store(ShugyoKisoku shugyoKisoku) {
        this.shugyoKisokus.add(shugyoKisoku);
    }
}