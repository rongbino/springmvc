package com.trong.wordcloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class GeneralController
{
    @RequestMapping(value="/index.do")
    public void index_jsp(Model model) {
        model.addAttribute("str0121", "Hello world");
        System.out.println("index.jsp");
    }
    
    @RequestMapping(value="/demo.do")
    public void demo_jsp(Model model) {
//        model.addAttribute(arg0)
        System.out.println("demo.jsp");
    }
  
}
