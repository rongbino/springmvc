package com.trong.wordcloud.data;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class UsernameToId
{
    
    private static Hashtable<String, String> usertoID = new Hashtable<>();

    private static void initData() {
        
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String inputfile = path + "/" + "username_id.txt";
        System.out.println("The username path :" + inputfile);
        try {
            FileInputStream fileInputStream = new FileInputStream(inputfile);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            
            String line = null;
            while((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.startsWith("#") || line.trim().equals("")) {
                    continue;
                } else {
                    String[] values = line.split(",");
                    if (values.length == 2) {
                        usertoID.put(values[0].trim().toLowerCase(), values[1].trim());
                    }
                }
            }
            
            reader.close();
            inputStreamReader.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Load username to ID fail~");
        }
    }
    
    public static String getIDbyName(String username) {
        if (usertoID.size() == 0) {
            UsernameToId.initData();
        }
        
        return usertoID.get(username.trim().toLowerCase());
    }
    
    public static void main(String[] args)
    {
        UsernameToId.initData();
        System.out.println(usertoID.toString());
    }

}
