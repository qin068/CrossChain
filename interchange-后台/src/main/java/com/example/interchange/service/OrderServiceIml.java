package com.example.interchange.service;


import com.example.interchange.info.ChainConfigInfo;
import com.example.interchange.pojo.Order;
import com.example.interchange.pojo.Token;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
/**
 * @author 秃头小刘
 * @date 2021-09-13 8:46 PM
 */

@SuppressWarnings("all")
public class OrderServiceIml implements OrderService{
    // chain-id | -home |  -node
    String[][] chainInfo = ChainConfigInfo.ChainPortInfo;
    String commandEnv = ChainConfigInfo.commandEnv;

    @Override
    public List<String> createOrderPair(String sourceToken, String targetToken,int chain, String channel) {
        String command = "echo  \"y\" | interchanged tx ibcdex send-create-pair ibcdex " + channel + " " + sourceToken + " " + targetToken + " --from alice " + chainInfo[chain - 1][0] + " " + chainInfo[chain - 1][1] + " " + chainInfo[chain - 1][2];
        String[] commands = {commandEnv, "-c", command};
        List<String> processList = runCommands(commands);
        return processList;
    }

    @Override
    public List<String> sendSellOrder(String sourceToken, int sourceNum, String targetToken, int price,int chain, String channel) {
        String command = "echo  \"y\" | interchanged tx ibcdex send-sell-order ibcdex " + channel + " " + sourceToken + " " + sourceNum + " "+ targetToken+ " " + price + " --from alice "+ chainInfo[chain - 1][0] + " " + chainInfo[chain - 1][1] + " " + chainInfo[chain - 1][2];
        String[] commands = { commandEnv, "-c", command};
        List<String> processList = runCommands(commands);
        return processList;
    }

    @Override
    public List<String> sendBuyOrder(String sourceToken, int sourceNum, String targetToken, int price,int chain, String channel) {
        String command = "echo  \"y\" | interchanged tx ibcdex send-buy-order ibcdex " + channel + " " + sourceToken + " " + sourceNum + " "+ targetToken+ " " + price + " --from alice "+ chainInfo[chain - 1][0] + " " + chainInfo[chain - 1][1] + " " + chainInfo[chain - 1][2];
        String[] commands = { commandEnv, "-c", command};
        List<String> processList = runCommands(commands);
        return processList;
    }

    @Override
    public List<String> cancelSellOrder(String sourceToken, String targetToken, int orderId,int chain, String channel) {
        String command = "echo  \"y\" | interchanged tx ibcdex cancel-sell-order ibcdex " + channel + " " + sourceToken + " " + targetToken + " " + orderId +" --from alice "+ chainInfo[chain - 1][0] + " " + chainInfo[chain - 1][1] + " " + chainInfo[chain - 1][2];
        String[] commands = { commandEnv, "-c", command};
        List<String> processList = runCommands(commands);
        return processList;
    }

    @Override
    public List<String> cancelBuyOrder(String sourceToken, String targetToken, int orderId,int chain, String channel) {
        String command = "echo  \"y\" | interchanged tx ibcdex cancel-buy-order ibcdex " + channel + " " + sourceToken + " " + targetToken + " " + orderId +" --from alice "+ chainInfo[chain - 1][0] + " " + chainInfo[chain - 1][1] + " " + chainInfo[chain - 1][2];
        String[] commands = { commandEnv, "-c", command};
        List<String> processList = runCommands(commands);
        return processList;
    }

    @Override
    public List<Order> listSellOrder(int chain) {
        String command = "interchanged q ibcdex list-sell-order-book " + chainInfo[chain - 1][2];
        List<String> processList = runCommands(command);
        return getOrder(processList);
    }

    @Override
    public List<Order> listBuyOrder(int chain) {
        String command = "interchanged q ibcdex list-buy-order-book " + chainInfo[chain - 1][2];
        List<String> processList = runCommands(command);
        return getOrder(processList);
    }

    @Override
    public List<Token> bankBalances(String address, int chain) {
        String command = "interchanged q bank balances " + address + " " + chainInfo[chain - 1][2];
        List<String> processList = runCommands(command);
        return getToken(processList);
    }

    @Override
    public void encourage(String address, int chain) {

        JSONObject param = new JSONObject();
        param.put("address", address);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add("1token");
        param.put("coins", jsonArray);
        //定义接收数据
        JSONObject result = new JSONObject();

        String url = chainInfo[chain - 1][3];
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        //请求参数转JOSN字符串
        StringEntity entity = new StringEntity(param.toString(), "UTF-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        try {
            HttpResponse response = client.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                result = JSON.parseObject(EntityUtils.toString(response.getEntity(), "UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
            result.put("error", "连接错误！");
        }

    }

    private List<Order> getOrder(List<String> list){
        List<Order> res = new ArrayList<>();
        String sourceToken = "",targetToken = "";
        int sourceNum = 0,price = 0,orderId = -1;
        for(int i = 0; i < list.size(); ++i){
            String str = list.get(i);
            if(str.contains("amountDenom")){
                sourceToken = str.substring(str.indexOf(':') + 1);
                for(int j = i + 1; j < list.size(); ++j){
                    String temp = list.get(j);
                    if(temp.contains("priceDenom")){
                        targetToken = temp.substring(str.indexOf(':') + 1);
                        break;
                    }
                }
            }else if(str.contains("amount")){
                sourceNum = Integer.parseInt(str.substring(str.indexOf(':') + 2));
            }else if(str.contains("id")){
                orderId = Integer.parseInt(str.substring(str.indexOf(':') + 2));
            }else if(!str.contains("priceDenom") && str.contains("price")){
                price = Integer.parseInt(str.substring(str.indexOf(':') + 2));
                Order order = new Order();
                order.setSourceToken(sourceToken);
                order.setTargetToken(targetToken);
                order.setOrderId(orderId);
                order.setSourceNum(sourceNum);
                order.setPrice(price);
                res.add(order);
            }

        }
        res.sort(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.getOrderId() - o2.getOrderId();
            }
        });
        return res;
    }
    private List<Token> getToken(List<String> list){
        List<Token> res = new LinkedList<>();
        String tokenName = "";
        int tokenNum = 0;
        for(String str : list){
            if(str.contains("amount")){
                tokenNum = Integer.parseInt(str.substring(str.indexOf(":") + 3,str.length() - 1));
            }else if(str.contains("denom")){
                tokenName = str.substring(str.indexOf(":") + 1);
                Token token = new Token();
                token.setTokenName(tokenName);
                token.setTokenNum(tokenNum);
                res.add(token);
            }
        }
        return res;
    }

    // 调用不需要交互的command
    private List<String> runCommands(String command){

        System.out.println(command);

        Process process = null;
        List<String> processList = new ArrayList<String>();

        try {
            process = Runtime.getRuntime().exec(command);

            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = "";
            while ((line = input.readLine()) != null) {
//                System.out.println(line);
                processList.add(line);
            }
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return processList;
    }
    // 调用需要交互的command 例如 需要进一步输入 y 等指令
    private List<String> runCommands(String[] commands){

        System.out.println(commands[2]);


        Process process = null;

        List<String> processList = new ArrayList<String>();

        try {
            process = Runtime.getRuntime().exec(commands);

            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));


            String line = "";
            while ((line = input.readLine()) != null) {
                System.out.println(line);
                processList.add(line);
            }
            input.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return processList;

    }
}
