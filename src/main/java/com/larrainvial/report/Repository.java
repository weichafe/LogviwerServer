package com.larrainvial.report;

import com.larrainvial.report.vo.MarketDataVO;
import com.larrainvial.report.vo.RoutingVO;

import java.util.LinkedHashMap;

public class Repository {


    public static LinkedHashMap<Long, RoutingVO> reportHashmapRouting = new LinkedHashMap<Long, RoutingVO>();
    public static LinkedHashMap<Long, MarketDataVO> reportHashmapMkdDolar = new LinkedHashMap<Long, MarketDataVO>();
    public static LinkedHashMap<Long, MarketDataVO> reportHashmapMkdADR = new LinkedHashMap<Long, MarketDataVO>();
    public static LinkedHashMap<Long, MarketDataVO> reportHashmapMkdLocal = new LinkedHashMap<Long, MarketDataVO>();

}
