package com.emotibot.wordcloud.restfull;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emotibot.wordcloud.data.Shop;

@Controller
public class RestfullController
{
    
    @RequestMapping(value="/rest/data", method=RequestMethod.GET)
    public @ResponseBody Shop get_data() {
        System.out.println("I am in");
        
        Shop shop = new Shop();
        shop.setName("aaa");
        shop.setStaffName(new String[] { "mkyong1", "mkyong2" });

        return shop;
    }

}
