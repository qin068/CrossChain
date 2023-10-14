package com.example.interchange.service;


import com.example.interchange.info.ChainConfigInfo;
import com.example.interchange.pojo.Post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author 秃头小刘
 * @date 2021-09-23 4:06 PM
 */

@SuppressWarnings("all")
public class PostServiceIml implements PostService {
    String[][] chainInfo = ChainConfigInfo.ChainPortInfo;
    String commandEnv = ChainConfigInfo.commandEnv;
    @Override
    public List<String> sendPost(String title, String content, String chain, String channel) {
        int cnt = Integer.parseInt(chain);
        String command = "interchanged tx blog send-ibc-post blog "+ channel + " " + title + " " + content + " --from alice -y " + chainInfo[cnt - 1][0] + " " + chainInfo[cnt - 1][1] + " " + chainInfo[cnt - 1][2];
//        String[] commands = { commandEnv, "-c", command};
        List<String> processList = runCommands(command);

//        for(String line : processList) System.out.println(line);

        return processList;
    }

    @Override
    public List<Post> listPost(String chain) {
        int cnt = Integer.parseInt(chain);
        String command = "interchanged q blog list-post " + chainInfo[cnt - 1][2];
        List<String> processList = runCommands(command);
        return getPost(processList);
    }

    @Override
    public List<Post> listSendPost(String chain) {
        int cnt = Integer.parseInt(chain);
        String command = "interchanged q blog list-sent-post " + chainInfo[cnt - 1][2];
        List<String> processList = runCommands(command);
        return getPost(processList);
    }
    private List<Post> getPost(List<String> list){
        List<Post> res = new ArrayList<>();
        String postId = "",creator = "",title = "",content = "";
        for (String str : list) {
            if(str.contains("creator")){
                creator = str.substring(str.indexOf(":") + 1);
            }else if(str.contains("id")){
                postId = str.substring(str.indexOf(":") + 3,str.length() - 1);
            }else if(str.contains("content")){
                content = str.substring(str.indexOf(":") + 1);
            }else if(str.contains("title")){
                title = str.substring(str.indexOf(":") + 1);
                Post post = new Post(postId,title,content,creator);
                res.add(post);
            }
        }
        res.sort(new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                return Integer.parseInt(o1.getId()) - Integer.parseInt(o2.getId());
            }
        });
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
