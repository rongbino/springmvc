package com.emotibot.wordcloud.restfull;


import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emotibot.wordcloud.data.ConversationList;
import com.emotibot.wordcloud.data.TopANode;
import com.emotibot.wordcloud.data.UserConversationData;
import com.emotibot.wordcloud.data.UserInfo;
import com.emotibot.wordcloud.data.UserTag;
import com.emotibot.wordcloud.data.UserTopKData;
import com.emotibot.wordcloud.data.UsernameToId;
import com.emotibot.wordcloud.data.UsertagData;
import com.google.common.base.Strings;

@Controller
public class RestfullController
{
    private static int maxTag = 50;
    
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
    
    @RequestMapping(value="/rest/con/{username}/{tag}", method=RequestMethod.GET)
    public @ResponseBody ConversationList get_date_conList(@PathVariable String username, @PathVariable String tag) {
        System.out.println("I am looking for the conversation evidence for user: " + username + " tag: " + tag);
        
        ConversationList list = new ConversationList();
        if (Strings.isNullOrEmpty(username)) {
            System.out.println("The username is null or empty~");
            return list;
        }
        
        String userid = UsernameToId.getIDbyName(username);
        if(Strings.isNullOrEmpty(userid)) {
            System.out.println("The userid is null or empty~");
            return list;
        }
        System.out.println("The userid is :" + userid);
        list.setUserid(userid);
        
        list.setConList(UserConversationData.searchConTag(userid, tag));
        return list;
    }
    
    @RequestMapping(value="/rest/username/{username}", method=RequestMethod.GET)
    public @ResponseBody UserInfo get_data_username(@PathVariable String username) {
        System.out.println("I am looking data by username~");
        System.out.println("The username is " + username);
        
        UserInfo info = new UserInfo();
        if (Strings.isNullOrEmpty(username)) {
            System.out.println("The username is null or empty~");
            return info;
        }
        String userid = UsernameToId.getIDbyName(username);
        System.out.println("The userid is " + userid);
        info.setUsername(username);
        info.setUserid(userid);
        if (Strings.isNullOrEmpty(userid)) {
            System.out.println("The userid is null or empty~");
            return info;
        } else {
            List<UserTag> list = UsertagData.getTopAListByUserID(userid);
            if (list == null) {
                info.setUserid(userid);
                return info;
            } else {
                Collections.sort(list);
                Collections.reverse(list);
                for (int i = 0; i < list.size(); i++) {
                    info.addTag(list.get(i));
                    if (i >= 49) {
                        break;
                    }
                }
                System.out.println("The tag list size :" + info.getTaglist().size());
                return info;
            }
        }
    }

}
