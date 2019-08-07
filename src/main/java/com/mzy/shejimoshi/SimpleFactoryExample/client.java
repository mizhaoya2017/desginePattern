package com.mzy.shejimoshi.SimpleFactoryExample;

import com.mzy.shejimoshi.util.XMLUtil;

public class client {
    public static void main(String[] args) {
        Chart chart;
        String type = XMLUtil.getChartType();
        chart = ChartFactory.getChart(type);
        chart.display();
    }
}
