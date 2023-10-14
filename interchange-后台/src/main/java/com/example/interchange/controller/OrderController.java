package com.example.interchange.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.interchange.pojo.Order;
import com.example.interchange.pojo.Token;
import com.example.interchange.service.OrderService;
import com.example.interchange.service.OrderServiceIml;
import com.example.interchange.utils.ResponseUtil;
import com.example.interchange.utils.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 秃头小刘
 * @date 2021-09-15 4:24 PM
 */

@RestController
public class OrderController {
    OrderService orderService = new OrderServiceIml();

    @GetMapping("/createOrderPair")
    public Result CreateOrderPair(String sourceToken, String targetToken, String chain, String channel){
        if(!checkForPara(chain)) return ResponseUtil.resp(400,"请求参数有误","");

        try {
            List<String> strings = orderService.createOrderPair(sourceToken,targetToken,Integer.parseInt(chain),channel);
            String data = checkTrans(strings);
            if(data.equals("")){
                return ResponseUtil.resp(202,"发送消息失败","跨链通信失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.resp(202,"创建订单簿失败","");
        }
        return ResponseUtil.resp(200,"成功创建订单簿","");
    }

    @GetMapping("/sendSellOrder")
    public Result sendSellOrder(String sourceToken, String sourceNum, String targetToken, String price,String chain, String channel){
        if(!checkForPara(sourceNum,price,chain)) return ResponseUtil.resp(400,"请求参数有误","");

        try {
            List<String> strings = orderService.sendSellOrder(sourceToken,Integer.parseInt(sourceNum),targetToken,Integer.parseInt(price),Integer.parseInt(chain),channel);
            String data = checkTrans(strings);
            if(data.equals("")){
                return ResponseUtil.resp(202,"发送消息失败","跨链通信失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.resp(202,"发送售卖订单失败","");
        }
        return ResponseUtil.resp(200,"成功发送售卖订单","");
    }

    @GetMapping("/sendBuyOrder")
    public Result sendBuyOrder(String sourceToken, String sourceNum, String targetToken, String price,String chain, String channel){
        if(!checkForPara(sourceNum,price,chain)) return ResponseUtil.resp(400,"请求参数有误","");

        try {
            List<String> strings = orderService.sendBuyOrder(sourceToken,Integer.parseInt(sourceNum),targetToken,Integer.parseInt(price),Integer.parseInt(chain),channel);
            String data = checkTrans(strings);
            if(data.equals("")){
                return ResponseUtil.resp(202,"发送消息失败","跨链通信失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.resp(202,"发送购买订单失败","");
        }
        return ResponseUtil.resp(200,"成功发送购买订单","");
    }

    @GetMapping("/cancelSellOrder")
    public Result cancelSellOrder(String sourceToken, String targetToken, String orderId, String chain, String channel){
        if(!checkForPara(orderId,chain)) return ResponseUtil.resp(400,"请求参数有误","");
        try {
            List<String> strings = orderService.cancelSellOrder(sourceToken,targetToken,Integer.parseInt(orderId),Integer.parseInt(chain),channel);
            String data = checkTrans(strings);
            if(data.equals("")){
                return ResponseUtil.resp(202,"发送消息失败","跨链通信失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.resp(202,"取消售卖订单失败","");
        }
        return ResponseUtil.resp(200,"成功取消售卖订单","");
    }

    @GetMapping("/cancelBuyOrder")
    public Result cancelBuyOrder(String sourceToken, String targetToken, String orderId,String chain, String channel){
        if(!checkForPara(orderId,chain)) return ResponseUtil.resp(400,"请求参数有误","");

        try {
            List<String> strings = orderService.cancelBuyOrder(sourceToken,targetToken,Integer.parseInt(orderId),Integer.parseInt(chain),channel);
            String data = checkTrans(strings);
            if(data.equals("")){
                return ResponseUtil.resp(202,"发送消息失败","跨链通信失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.resp(202,"取消购买订单失败","");
        }
        return ResponseUtil.resp(200,"成功取消购买订单","");
    }

    @GetMapping("/listSellOrder")
    public Result listSellOrder(String chain){
        if(!checkForPara(chain)) return ResponseUtil.resp(400,"请求参数有误","");

        List<Order> res = null;
        try {
            res = orderService.listSellOrder(Integer.parseInt(chain));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.resp(202,"售卖订单刷新失败","");
        }
        return ResponseUtil.resp(200,"刷新售卖订单成功",res);
    }

    @GetMapping("/listBuyOrder")
    public Result listBuyOrder(String chain){
        if(!checkForPara(chain)) return ResponseUtil.resp(400,"请求参数有误","");

        List<Order> res = null;
        try {
            res = orderService.listBuyOrder(Integer.parseInt(chain));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.resp(202,"购买订单刷新失败","");
        }
        return ResponseUtil.resp(200,"刷新购买订单成功",res);
    }

    @GetMapping("/bankBalances")
    public Result bankBalances(String address,String chain){
        if(!checkForPara(chain)) return ResponseUtil.resp(400,"请求参数有误","");

        List<Token> res = null;
        try {
            res = orderService.bankBalances(address,Integer.parseInt(chain));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.resp(202,"查询余额失败","");
        }
        return ResponseUtil.resp(200,"查询余额成功",res);
    }

    @GetMapping("/encourage")
    public Result encourage(String address,String chain){
        if(!checkForPara(chain)) return ResponseUtil.resp(400,"请求参数有误","");

        try {
            orderService.encourage(address,Integer.parseInt(chain));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseUtil.resp(202,"获得激励失败","");
        }
        return ResponseUtil.resp(200,"获得激励成功","");
    }

    private boolean checkForPara(String sourceNumStr, String priceStr, String chainStr){
        int chain,sourceNum,price;
        try{
            chain = Integer.parseInt(chainStr);
            sourceNum = Integer.parseInt(sourceNumStr);
            price = Integer.parseInt(priceStr);
        } catch (NumberFormatException e) {
            return false;
        }
        return chain > 0 && chain < 4;
    }
    private boolean checkForPara(String orderIdStr, String chainStr){
        int chain,orderId;
        try{
            chain = Integer.parseInt(chainStr);
            orderId = Integer.parseInt(orderIdStr);
        } catch (NumberFormatException e) {
            return false;
        }
        return chain > 0 && chain < 4;
    }
    private boolean checkForPara(String chainStr){
        int chain;
        try{
            chain = Integer.parseInt(chainStr);
        } catch (NumberFormatException e) {
            return false;
        }
        return chain > 0 && chain < 4;
    }
    public String checkTrans(List<String> strings){
        String line = strings.get(0);
        JSONObject lineJson = JSONObject.parseObject(line);
        String resultData = lineJson.getString("data");
        return resultData;
    }
}
