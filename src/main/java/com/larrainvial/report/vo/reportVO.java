package com.larrainvial.report.vo;

import java.util.Date;

public class ReportVO {

    //HORA
    public Date date;

    //MARKETDATA
    public String symbol;
    public String hour;
    public String year;
    public String messageByType;
    public Double buyPx;
    public Double buyQty;
    public Double sellPx;
    public Double sellQty;
    public Double closePx;

    //ROUTING
    public String execType;
    public Double lastQty;
    public Double lastPx;
    public Double leavesQty;


}
