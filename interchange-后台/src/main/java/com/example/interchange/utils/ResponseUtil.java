package com.example.interchange.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/**
 * @author 秃头小刘
 * @date 2021-09-16 4:54 PM
 */


public class ResponseUtil{

    public static Result resp(int code, String msg, Object data){
        Result<Object> result=new Result<Object>();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }

}
