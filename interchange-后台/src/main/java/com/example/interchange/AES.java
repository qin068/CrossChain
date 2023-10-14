package com.example.interchange;


import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES{
    public static void main(String[] args) throws Exception {
        // 原文
        String input = "你好，我是CompanyA";
        // AES加密算法：密匙必须是16个字节

        String encrypt = encrypt(input, "12345678", "DES", "DES");
        System.out.println("加密:" + encrypt);
        String decrypt = dncrypt(encrypt, "12345678", "DES", "DES");
        System.out.println("解密:" + decrypt);
        String encrypt2 = encrypt(input, "1234567812345678", "AES", "AES");
        System.out.println("加密:" + encrypt2);
        String decrypt2 = dncrypt(encrypt2, "1234567812345678", "AES", "AES");
        System.out.println("解密:" + decrypt2);
    }



    /**
     * 加密数据
     */
    private static String encrypt(String input, String key, String transformation, String algorithm) throws Exception {
        // 获取加密对象
        Cipher cipher = Cipher.getInstance(transformation);
        // 指定秘钥规则：传入密钥的字节数组与指定的算法
        SecretKeySpec sks = new SecretKeySpec(key.getBytes(), algorithm);
        // 初始化加密对象: 指定加密模式与秘钥规则
        cipher.init(Cipher.ENCRYPT_MODE, sks);
        // 开始加密
        byte[] bytes = cipher.doFinal(input.getBytes());
        // 打印密文,由于bytes字节数字中存在负数，即ascii码有负数，解析不出来，将输出乱码
//        System.out.println("bytes = " + new String(bytes));
        // 对字节数字进行Base64编码
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * 解密数据
     */
    private static String dncrypt(String input, String key, String transformation, String algorithm) throws Exception {
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
}



