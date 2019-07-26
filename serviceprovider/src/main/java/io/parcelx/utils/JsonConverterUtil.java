package io.parcelx.utils;

import com.alibaba.fastjson.JSON;

/**
 * @author xinfeng
 * @version 1.0
 * @Description
 * @date 2019/7/25 16:21
 */
public class JsonConverterUtil {

    /**
     * 将Java对象转换为Json字符串并输出
     * @param obj Java对象
     * @return 返回Json字符串
     * @date 2019/7/25 16:22
     */
    public static void getJsonString(Object obj){
        if(obj != null){
            String jsonString = JSON.toJSONString(obj);
            System.out.println(jsonString);
        }else{
            System.out.println("Object is null");
        }
    }
}
