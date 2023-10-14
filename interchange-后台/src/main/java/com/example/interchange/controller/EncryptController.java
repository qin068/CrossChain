package com.example.interchange.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.interchange.service.*;
import com.example.interchange.utils.ResponseUtil;
import com.example.interchange.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@RestController
public class EncryptController {
    PostService postService = new PostServiceIml();

    @Autowired
    TitleService titleService;

    @GetMapping("/encrypt")
    public Result encrypt(String algorithm, String password, String content){
        String result = null;
        try {
            if(algorithm.equals("DES")){
                result = DAESencrypt(content,password,"DES","DES");
            }else if(algorithm.equals("AES")){
                result = DAESencrypt(content, password,"AES","AES");
            }else if(algorithm.equals("PBE")){
                byte[] salt = {66,124,94,70,-11,-96,84,121};
                result = PBEencrypt(content, password, salt);
            }
            System.out.println(result);
            if(result.equals(null)){
                System.out.println("空");
                return ResponseUtil.resp(202,"加密数据失败","加密数据失败");
            }

        } catch (Exception e) {
            return ResponseUtil.resp(202,"加密数据失败","");
        }
        return ResponseUtil.resp(200,"加密数据失败",result);
    }


    @GetMapping("/sendInterMessage")
    public Result SendInterBlog(String title, String content, String chain, String channel){
        if(!checkParam(chain)) return ResponseUtil.resp(400,"请求参数有误","");
        try {
            List<String> strings = postService.sendPost(title, content, chain, channel);
            String data = getReturnData(strings);
            if(data.equals("")){
                return ResponseUtil.resp(202,"发送加密数据失败","");
            }
        } catch (Exception e) {
            return ResponseUtil.resp(202,"发送加密数据失败","");
        }
        return ResponseUtil.resp(200,"发送加密数据成功","");
    }

    @GetMapping("/decrypt")
    public Result decrypt(String algorithm, String password, String content){
        String result = null;
        try {
            if(algorithm.equals("DES")){
                result = DAESdecrypt(content,password,"DES","DES");
            } else if(algorithm.equals("AES")){
                result = DAESdecrypt(content, password, "AES", "AES");
            } else if(algorithm.equals("PBE")){
                byte[] salt = {66,124,94,70,-11,-96,84,121};
                result = PBEdecrypt(content, password, salt);
            }
            System.out.println(result);
            if(result.equals(null)){
                System.out.println("空");
                return ResponseUtil.resp(202,"解密失败","解密失败");
            }
        } catch (Exception e) {
            return ResponseUtil.resp(202,"解密失败","");
        }
        return ResponseUtil.resp(200,"解密成功",result);
    }


    private static String DAESencrypt(String input, String key, String transformation, String algorithm) throws Exception {
        // 获取加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 指定秘钥规则：传入密钥的字节数组与指定的算法
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        // 初始化加密对象: 指定加密模式与秘钥规则
        cipher.init(Cipher.ENCRYPT_MODE, sks);
        // 开始加密RSA
        byte[] bytes = cipher.doFinal(input.getBytes());
        // 对字节数字进行Base64编码
        return Base64.getEncoder().encodeToString(bytes);
    }

    private static String DAESdecrypt(String input, String key, String transformation, String algorithm) throws Exception {
        // 获取Cipher对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 指定秘钥规则：传入密钥的字节数组与指定的算法
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        // 初始化解密对象: 指定解密模式与秘钥规则
        cipher.init(Cipher.DECRYPT_MODE, sks);
        // 解密
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(input));
        return new String(bytes);
    }

    public static final String ALGORITHM = "PBEWITHMD5andDES";

    public static final int ITERATION_COUNT = 100;


    public static byte[] initSalt() throws Exception{
        //实例化安全随机数
        SecureRandom random = new SecureRandom();
        return random.generateSeed(8);
    }

    /***
     * 转换密钥
     * @param password 密码
     * @return 密钥
     * @throws Exception
     */
    private static Key toKey(String password) throws Exception{
        //密钥材料
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        //实例化
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        //生成密钥
        return factory.generateSecret(keySpec);
    }

    /***
     * 加密
     * @param data 待加密数据
     * @param password 密钥
     * @param salt
     * @return
     * @throws Exception
     */
    public static String PBEencrypt(String data, String password, byte[] salt) throws Exception{
        //转换密钥
        Key key = toKey(password);
        //实例化PBE参数材料
        PBEParameterSpec spec = new PBEParameterSpec(salt, ITERATION_COUNT);
        //实例化
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        //初始化
        cipher.init(Cipher.ENCRYPT_MODE, key, spec);
        byte[] bytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String PBEdecrypt(String data, String password, byte[] salt) throws Exception{
        //转换密钥
        Key key = toKey(password);
        //实例化PBE参数材料
        PBEParameterSpec spec = new PBEParameterSpec(salt, ITERATION_COUNT);
        //实例化
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        //初始化
        cipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(data));
        //执行操作
        return new String(bytes);
    }

    private boolean checkParam(String chain){
        int cnt = 0;
        try {
            cnt = Integer.parseInt(chain);
        } catch (NumberFormatException e) {
            return false;
        }
        return  cnt > 0 && cnt < 4;//限制只开3条链
    }

    public String getReturnData(List<String> strings){
        String line = strings.get(0);
        JSONObject lineJson = JSONObject.parseObject(line);
        String resultData = lineJson.getString("data");
        return resultData;
    }


}
