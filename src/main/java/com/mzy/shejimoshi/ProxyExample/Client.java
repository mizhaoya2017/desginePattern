package com.mzy.shejimoshi.ProxyExample;

import com.mzy.shejimoshi.util.XMLUtil;

public class Client {
    public static void main(String[] args) {
        Searcher searcher;
        searcher = (Searcher)XMLUtil.getBean3();
        String result = searcher.doSearch("杨过", "玉女心经");
    }
}
