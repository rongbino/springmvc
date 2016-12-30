package com.emotibot.wordcloud.restfull;


import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emotibot.wordcloud.data.TopANode;
import com.emotibot.wordcloud.data.UserInfo;
import com.emotibot.wordcloud.data.UserTag;
import com.emotibot.wordcloud.data.UserTopKData;
import com.emotibot.wordcloud.data.UsernameToId;

@Controller
public class RestfullController
{
    
    @RequestMapping(value="/rest/userid/{userid}", method=RequestMethod.GET)
    public @ResponseBody UserInfo get_data_userid(@PathVariable String userid) {
        System.out.println("I am looking data by userid~");
        System.out.println("The userid is " + userid);
        
        UserInfo info = new UserInfo();
        info.setUserid(userid);
        if (userid == null || "".equals(userid)) {
            return info;
        } else {
            List<TopANode> list = UserTopKData.getTopAListByUserID(userid);
            if (list == null) {
                
                return info;
            } else {
                for (TopANode node: list) {
                    UserTag tag = new UserTag();
                    tag.setName(node.getNode());
                    tag.setValue(node.getTotalValue());
                    
                    info.getTaglist().add(tag);
                }
                return info;
            }
        }
    }
    
    @RequestMapping(value="/rest/username/{username}", method=RequestMethod.GET)
    public @ResponseBody UserInfo get_data_username(@PathVariable String username) {
        System.out.println("I am looking data by username~");
        System.out.println("The username is " + username);
        
        UserInfo info = new UserInfo();
        if (username == null || "".equals(username)) {
            System.out.println("The username is null or empty~");
            return info;
        }
        String userid = UsernameToId.getIDbyName(username);
        info.setUsername(username);
        info.setUserid(userid);
        if (userid == null || "".equals(userid)) {
            System.out.println("The userid is null or empty~");
            return info;
        } else {
            List<TopANode> list = UserTopKData.getTopAListByUserID(userid);
            if (list == null) {
                info.setUserid(userid);
                return info;
            } else {
                for (TopANode node: list) {
                    UserTag tag = new UserTag();
                    tag.setName(node.getNode());
                    tag.setValue(node.getTotalValue());
                    
                    info.getTaglist().add(tag);
                }
                return info;
            }
        }
    }

}
