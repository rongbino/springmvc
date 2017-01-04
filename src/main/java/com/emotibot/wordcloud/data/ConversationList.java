package com.emotibot.wordcloud.data;

import java.util.ArrayList;
import java.util.List;

public class ConversationList {
    String userid;
    List<String> conList = new ArrayList<>();
    
    public ConversationList() {
        // TODO Auto-generated constructor stub
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public List<String> getConList() {
        return conList;
    }

    public void setConList(List<String> conList) {
        this.conList = conList;
    }
    
    public void addConversation(String conversation) {
        this.conList.add(conversation);
    }
}
