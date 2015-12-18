package com.larrainvial.logviewer;

import com.larrainvial.logviewer.adaptador.QuickFixAdapter;
import com.larrainvial.logviewer.bo.Algo;
import java.text.SimpleDateFormat;
import java.util.*;

public class Repository {

    public static Map<String, Algo> strategy = Collections.synchronizedMap(new LinkedHashMap<String, Algo>());
    public static String year = new SimpleDateFormat("yyyy/MM/dd").format(new Date()).replace("/", "");
    public static String FIX44;
    public static QuickFixAdapter quickFixAdapter;

}
