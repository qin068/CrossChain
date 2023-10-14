package com.example.interchange.service;

import com.example.interchange.pojo.Order;
import com.example.interchange.pojo.Token;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2021-09-13 8:43 PM
 */


public interface OrderService {
    //    nodeport 指定在哪条链上执行

    // 根据 源代币 和 目标代币 创建订单簿
    List<String> createOrderPair(String sourceToken, String targetToken,int chain, String channel);

    // 发送售卖的订单
    List<String> sendSellOrder(String sourceToken, int sourceNum, String targetToken, int price,int chain, String channel);
    // 发送购买的订单
    List<String> sendBuyOrder(String sourceToken, int sourceNum, String targetToken, int price,int chain, String channel);

    // 取消售卖订单
    List<String> cancelSellOrder(String sourceToken, String targetToken, int orderId,int chain, String channel);
    // 取消购买订单
    List<String> cancelBuyOrder(String sourceToken, String targetToken, int orderId,int chain, String channel);

    // 查询售卖订单
    List<Order> listSellOrder(int chain);
    // 查询购买订单
    List<Order> listBuyOrder(int chain);

    // 查询账户余额
    List<Token> bankBalances(String address, int chain);

    void encourage(String address, int chain);
}
