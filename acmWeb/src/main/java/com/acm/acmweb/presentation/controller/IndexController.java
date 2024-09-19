package com.acm.acmweb.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping(value = "/holamundo",method = RequestMethod.GET)
    public String indexController(Model model){
        Map<String,Object> map = new HashMap<>();
        map.put("message","Hola mundo");
        model.addAttribute("message","Hello World");
        return "index";
    }


}
