package com.vince.framework;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 创建bean的factory类
 */
public class BeanFactory {

    private Iterator<Element> iterator = null;
    public static BeanFactory beanFactory = null;


    private Map<String,String> map1 = new HashMap<>();



    public static BeanFactory init (){
        if(beanFactory == null){
            synchronized (BeanFactory.class){
                if(beanFactory == null){
                    beanFactory = new BeanFactory("bean.xml");
                }
            }
        }
        return beanFactory;
    }

    private BeanFactory(String xml){
        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(xml);
        //1创建dom4j的解析器对象
        SAXReader reader = new SAXReader();

        try {
            Document document = reader.read(is);
            Element rootElement = document.getRootElement();
            iterator = rootElement.elementIterator();
            is.close();

            while(iterator.hasNext()){
                Element bean = iterator.next();
                String sid = bean.attributeValue("id");
                String ClassName = bean.attributeValue("class");
                map1.put(sid,ClassName);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Object getBean(String id){

        try {
            return Class.forName(map1.get(id)).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;

//        init();
//        while(iterator.hasNext()){
//            Element bean = iterator.next();
//            String sid = bean.attributeValue("id");
//
//            if(sid.equals(id)){
//                String className = bean.attributeValue("class");
//                try {
//                    return Class.forName(className).newInstance();
//                } catch (InstantiationException e) {
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//        return null;
    }

}
