package com.myzlab.k.sql.encoding;

import com.myzlab.k.KEncoding;

public class KWin1251 extends KEncoding {
    
    @Override
    public String toSql() {
        return "WIN1251";
    }
}
