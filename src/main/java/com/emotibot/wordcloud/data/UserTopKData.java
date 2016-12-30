package com.emotibot.wordcloud.data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class UserTopKData
{
    // 保存用户的TapA 数据结果
    private static Hashtable<String, List<TopANode>> userTopAList = new Hashtable<>();
    
    /**
     * 如果数据Table为空，则load TopA数据
     */
    private static void initData() {
        // load the final.txt into memory
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String finalResult = path + "/" + Constant.result_file;
        try {
            FileInputStream fileInputStream = new FileInputStream(finalResult);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            
            String line = null;
            String userid = null;
            boolean start = false;
            List<TopANode> match3 = new ArrayList<TopANode>();
            while((line = reader.readLine()) != null) {
                if (line.startsWith("#") || line.trim().equals("")) {
                    continue;
                } else {
                    // load the data into list
                    if (line.startsWith(Constant.wordStatistic_userid)) {
                        userid = line.replace(Constant.wordStatistic_userid, "").trim();
//                        System.out.println(line);
                        match3 = new ArrayList<>(); // 为一个用户重新记录
                        continue;
                    } else if (line.startsWith(Constant.topA_match3)) {
                        start = true; //开始写入
                        continue;
                    } else if (line.startsWith(Constant.topA_match2)) {
                        start = false;
//                        System.out.println("Top3->size :" + match3.size());
                        // 保存到Map
                        userTopAList.put(userid, match3);
                        continue;
                    }
                    
                    if (start && line.startsWith(Constant.topA)) { //需要记录的Top3
//                        System.out.println(line);
                         String[] topA = line.split(":");
                         if (topA != null && topA.length == 2) {
                             String temp = topA[1].trim();
                             String[] values = temp.split(",");
                             if (values.length == 5) {
                                 TopANode node = new TopANode();
                                 node.setNode(values[0].trim());
                                 node.setTextRankValue(Double.valueOf(values[1].trim()));
                                 node.setTFIDFValue(Double.valueOf(values[2].trim()));
                                 node.setNLPKeywordsValue(Double.valueOf(values[3].trim()));
                                 node.setTotalValue(Double.valueOf(values[4].trim()));
                                 
                                 match3.add(node);
                             }
                             
                         }
                    }
                }
            }
            
            // close stream
            reader.close();
            inputStreamReader.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
            System.out.println("Load the final.txt result fail~~~");
        }
    }

    /**
     * 查询用户TopA List
     * @param userid
     * @return
     */
    public static List<TopANode> getTopAListByUserID(String userid) {
        if (userTopAList.size() == 0) {
            UserTopKData.initData();
        }
        
        return userTopAList.get(userid);
    }
    
    public static void main(String[] args) {
        UserTopKData.initData();
    }
}
