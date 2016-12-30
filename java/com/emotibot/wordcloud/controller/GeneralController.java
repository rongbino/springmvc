package com.emotibot.wordcloud.controller;

import java.util.Hashtable;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class GeneralController
{
    @RequestMapping(value="index.do")
    public void index_jsp(Model model) {
        model.addAttribute("str0121", "Hello world");
        System.out.println("index.jsp");
    }
    
    @RequestMapping(value="demo.do")
    public void demo_jsp(Model model) {
//        model.addAttribute(arg0)
        System.out.println("demo.jsp");
    }
  
}
