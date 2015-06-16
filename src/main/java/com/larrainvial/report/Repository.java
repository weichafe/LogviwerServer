package com.larrainvial.report;

import com.larrainvial.report.vo.MarketDataVO;
import com.larrainvial.report.vo.ReporteVO;
import com.larrainvial.report.vo.RoutingVO;

import java.util.LinkedHashMap;

public class Repository {


    public static LinkedHashMap<Long, ReporteVO> reportHashmap = new LinkedHashMap<Long, ReporteVO>();
    public static LinkedHashMap<Long, ReporteVO> reportHashmapAUX = new LinkedHashMap<Long, ReporteVO>();

    public static LinkedHashMap<Long, RoutingVO> reportHashmapRoutingLocal = new LinkedHashMap<Long, RoutingVO>();
    public static LinkedHashMap<Long, RoutingVO> reportHashmapRoutingAdr = new LinkedHashMap<Long, RoutingVO>();

    public static LinkedHashMap<Long, MarketDataVO> reportHashmapMkdDolarCad = new LinkedHashMap<Long, MarketDataVO>();
    public static LinkedHashMap<Long, MarketDataVO> reportHashmapMkdDolarCof = new LinkedHashMap<Long, MarketDataVO>();

    public static LinkedHashMap<Long, MarketDataVO> reportHashmapMkdADR = new LinkedHashMap<Long, MarketDataVO>();
    public static LinkedHashMap<Long, MarketDataVO> reportHashmapMkdLocal = new LinkedHashMap<Long, MarketDataVO>();

    public static boolean printer = false;

    public static Long timeFirs;
    public static Long timeLast;


}
