package com.burobo.springkintai.ddd.domain.shugyo_kisoku;

import com.burobo.springkintai.ddd.domain.kintai.Kinmubi;

public interface ShugyoKisokuRepository {
    public ShugyoKisoku shugyoKisokuOfKinmubi(Kinmubi kinmubi);
    
    public void store(ShugyoKisoku shugyoKisoku);
}