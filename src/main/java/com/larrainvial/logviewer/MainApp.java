package com.larrainvial.logviewer;

import com.larrainvial.logviewer.adaptador.QuickFixAdapter;
import com.larrainvial.logviewer.bo.Algo;
import com.larrainvial.trading.utils.PropertiesFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class MainApp {

    public static PropertiesFile propertiesFile;

    public static void main(String args[]) {

        try {

            propertiesFile = new PropertiesFile(args[0]);

            File xmlFile = new File(propertiesFile.getPropertiesString("strategy"));
            Repository.FIX44 = propertiesFile.getPropertiesString("FIX44");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);

            NodeList nodeList = document.getDocumentElement().getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    new Algo((Element) node);
                }
            }


            Repository.quickFixAdapter = new QuickFixAdapter(propertiesFile.getPropertiesString("qFixFile"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
