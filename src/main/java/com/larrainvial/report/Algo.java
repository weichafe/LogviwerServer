package com.larrainvial.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;


public class Algo implements Serializable {

    private static final long serialVersionUID = 2635519613775362759L;

    public File file_mkd_dolar;
    public File file_mkd_local;
    public File file_mkd_adr;
    public File file_routing_local;
    public File file_routing_adr;

    public FileInputStream inputStream_mkd_dolar;
    public FileInputStream inputStream_mkd_local;
    public FileInputStream inputStream_mkd_adr;
    public FileInputStream inputStream_routing_local;
    public FileInputStream inputStream_routing_adr;

}
