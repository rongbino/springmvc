package com.emotibot.wordcloud.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class UserConversationData {
    
    public static Hashtable<String, List<String>> conList = new Hashtable<>();
    
    private static void initData() {
        // load the final.txt into memory
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String conFolder = path + "/eachUser";
        
        File eachUserFolder = new File(conFolder);
        File[] files = eachUserFolder.listFiles();
        int fileNumber = 1;
        try {
            for (File fl : files)
            {
                FileInputStream fileInputStream = new FileInputStream(fl);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                System.out.println("Already fixed file number :" + fileNumber);
                System.out.println("Already fixed file name is : " + fl.getName());
                
                String id = fl.getName();
                if (!conList.contains(id)) {
                    conList.put(id, new ArrayList<String>());
                }
                String line = null;
                while((line = bufferedReader.readLine()) != null) {
                    if (line.startsWith("#") || line.trim().equals("")) {
                        continue;
                    }
                    
                    String[] split = line.split("&&");
                    if (split != null && split.length == 3) {
                        String convsersation = split[2] + "  " + split[1];
                        conList.get(id).add(convsersation); // time and conversation
                        System.out.println(convsersation);
                    }
                }
                
                bufferedReader.close();
                inputStreamReader.close();
                fileInputStream.close();
                fileNumber++;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    
    public static List<String> searchConTag(String userid, String tag) {
        if (UserConversationData.conList.size() == 0) {
            UserConversationData.initData();
        }
        List<String> result = new ArrayList<>();
        for (String temp : UserConversationData.conList.get(userid)) {
            if (temp.indexOf(tag) != -1) {
                System.out.println(temp);
                result.add(temp);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        UserConversationData data = new UserConversationData();
        data.initData();
    }

}
