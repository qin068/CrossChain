package com.example.interchange.service;


import com.example.interchange.info.ChainConfigInfo;
import com.example.interchange.pojo.Blog;

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
public class BlogServiceIml implements BlogService{
    String[][] chainInfo = ChainConfigInfo.ChainPortInfo;
//    String commandEnv = ChainConfigInfo.commandEnv;
//    @Override
    public List<String> sendMessage(String title, String body, String chain) {
        int cnt = Integer.parseInt(chain);
        String command = "interchanged tx message create-post " + title + " " + body + " --from alice -y " + chainInfo[cnt - 1][0] + " " + chainInfo[cnt - 1][1] + " " + chainInfo[cnt - 1][2];
//        String[] commands = { commandEnv, "-c", command};
        List<String> processList = runCommands(command);

//        for(String line : processList) System.out.println(line);

        return processList;
    }


    @Override
    public List<Blog> listBlog(String chain) {
        int cnt = Integer.parseInt(chain);
        String command = "interchanged q message list-post " + chainInfo[cnt - 1][2];
        List<String> processList = runCommands(command);
        return getMessage(processList);
    }

    private List<Blog> getMessage(List<String> list){
        List<Blog> res = new ArrayList<>();
        String id = "",creator = "",title = "",body = "";
        for (String str : list) {
            if(str.contains("creator")){
                creator = str.substring(str.indexOf(":") + 1);
            }else if(str.contains("id")){
                id = str.substring(str.indexOf(":") + 3,str.length() - 1);
            }else if(str.contains("body")){
                body = str.substring(str.indexOf(":") + 1);
            }else if(str.contains("title")){
                title = str.substring(str.indexOf(":") + 1);
                Blog blog = new Blog(id,title,body,creator);
                res.add(blog);
            }
        }
        res.sort(new Comparator<Blog>() {
            @Override
            public int compare(Blog o1, Blog o2) {
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
//    // 调用需要交互的command 例如 需要进一步输入 y 等指令
//    private List<String> runCommands(String[] commands){
//
//        Process process = null;
//
//        List<String> processList = new ArrayList<String>();
//
//        try {
//            process = Runtime.getRuntime().exec(commands);
//
//            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//            String line = "";
//            while ((line = input.readLine()) != null) {
//                System.out.println(line);
//                processList.add(line);
//            }
//            input.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return processList;
//
//    }

}
