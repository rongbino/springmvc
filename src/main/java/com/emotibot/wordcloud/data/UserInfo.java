package com.emotibot.wordcloud.data;

import java.util.ArrayList;
import java.util.List;

public class UserInfo
{
    String userid;
    String username;
    List<UserTag> taglist = new ArrayList<>();
    public String getUserid()
    {
        return userid;
    }
    public void setUserid(String userid)
    {
        this.userid = userid;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public List<UserTag> getTaglist()
    {
        return taglist;
    }
    public void setTaglist(List<UserTag> taglist)
    {
        this.taglist = taglist;
    }
    public void addTag(UserTag tag) {
        this.taglist.add(tag);
    }
}
