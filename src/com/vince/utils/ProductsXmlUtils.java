package com.vince.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;
import com.vince.bean.Clothes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsXmlUtils {
    public static List<Clothes> parserProductFromXml(){
        List<Clothes> products = new ArrayList<>();
        XStream xstream = new XStream(new Xpp3Driver());
        xstream.alias("list",products.getClass());
        xstream.alias("clothes",Clothes.class);
        xstream.useAttributeFor(Clothes.class,"id");
        try {
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("products.xml"));
            products = (List<Clothes>) xstream.fromXML(inputStream);
            inputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return products;
    }

    public static void writeProductToXml(List<Clothes> products){
        XStream xstream = new XStream(new Xpp3Driver());
        xstream.alias("list",products.getClass());
        xstream.alias("clothes",Clothes.class);
        xstream.useAttributeFor(Clothes.class,"id");

        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream("products.xml"));
            bufferedOutputStream.write("<?xml version=\"1.0\" encoding=\"utf-8\" ?>".getBytes());
            xstream.toXML(products,bufferedOutputStream);
            bufferedOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
