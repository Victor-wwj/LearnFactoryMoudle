package Understanding_Ioc.Factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BeanFactory {
    private static Properties properties;
    private static Map<String,Object> beans;
    static {
        try {
            properties=new Properties();
            InputStream is =BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            properties.load(is);
            beans = new HashMap<String, Object>();
            Enumeration<Object> keys = properties.keys();
            while (keys.hasMoreElements()){
                String key = keys.nextElement().toString();
                String beanPath = properties.getProperty(key);
                Object value = Class.forName(beanPath).newInstance();
                beans.put(key,value);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("InitializerError！");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public  static Object getBean(String beanName){
        Object bean =  beans.get(beanName);
        if (bean == null){
            String beanPath = properties.getProperty(beanName);
            try {
                bean = Class.forName(beanPath).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return bean;
        }
        return bean;
    }
//    public static Object getBean(String beanName){
//        Object bean.properties = null;
//        String beanPath = properties.getProperty(beanName);
//        try {
//            bean.properties = Class.forName(beanPath).newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return bean.properties;
//    }

}
