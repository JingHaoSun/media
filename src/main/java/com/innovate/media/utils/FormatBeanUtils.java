package com.innovate.media.utils;


import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : zhashuing
 * @Date :2018-08-12 13:10
 * @Description :
 */
public class  FormatBeanUtils<T> {

    public static String underscoreName(String camelCaseName) {
        StringBuilder result = new StringBuilder();
        if (camelCaseName != null && camelCaseName.length() > 0) {
            result.append(camelCaseName.substring(0, 1).toLowerCase());
            for (int i = 1; i < camelCaseName.length(); i++) {
                char ch = camelCaseName.charAt(i);
                if (Character.isUpperCase(ch)) {
                    result.append("_");
                    result.append(Character.toLowerCase(ch));
                } else {
                    result.append(ch);
                }
            }
        }
        return result.toString();
    }

    public  static Map   formatBean(Object object)  {
        Map<String,Object> map = new LinkedHashMap();

        Field [] fields = object.getClass().getDeclaredFields();
        for (Field field :fields){
           field.setAccessible(true);
            String fieldName = underscoreName(field.getName());
            try {
                map.put(fieldName,field.get(object));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
