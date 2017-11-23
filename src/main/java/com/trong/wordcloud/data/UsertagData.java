package com.trong.wordcloud.data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class UsertagData
{
 // 保存用户的Tag数据结果
    private static Hashtable<String, List<UserTag>> userTagList = new Hashtable<>();
    
    /**
     * 如果数据Table为空，则load TopA数据
     */
    private static void initData() {
        // load the final.txt into memory
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String finalResult = path + "/" + Constant.tag_file;
        try {
            FileInputStream fileInputStream = new FileInputStream(finalResult);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            
            String line = null;
            String userid = null;
            boolean start = false;
            List<UserTag> match3 = new ArrayList<UserTag>();
            while((line = reader.readLine()) != null) {
                if (line.startsWith("#") || line.trim().equals("")) {
                    continue;
                } else {
                    // load the data into list
                    if (line.startsWith(Constant.wordStatistic_userid)) {
                        if (match3.size() > 0) {
                            userTagList.put(userid, match3); // 记录并且为下个用户创建新的List
                            if (userid.equals("W346")) {
                                System.out.println("size :" + match3.size());
                            }
                            match3 = new ArrayList<>();
                        }
                        // 开始记录下一个用户的ID
                        userid = line.replace(Constant.wordStatistic_userid, "").trim();
                        continue;
                    } else {
                        String[] values = line.split(":");
                        if (values != null && values.length == 2) {
                            UserTag tag = new UserTag();
                            tag.setName(values[0].trim());
                            tag.setValue(Double.valueOf(values[1].trim()));
                            match3.add(tag);
                        }
                    }
                }
            }
            // 记录最后一个用户的数据
            userTagList.put(userid, match3);
            
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
    public static List<UserTag> getTopAListByUserID(String userid) {
        if (userTagList.size() == 0) {
            UsertagData.initData();
        }
        
        return userTagList.get(userid);
    }

    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        UsertagData.initData();
    }

}
