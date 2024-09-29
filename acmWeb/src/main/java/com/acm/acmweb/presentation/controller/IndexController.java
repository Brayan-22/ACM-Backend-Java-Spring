package com.acm.acmweb.presentation.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
//@RequestMapping(path = "/acm")
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);
    @RequestMapping(path = "/holamundo", method = RequestMethod.GET)
    public String indexController(Model model) {
        Map<String, Object> map = new HashMap<>();
        //map.put("message", "Hola mundo");
        //model.addAttribute("message", "Hello World");
        return "index";
    }

    @GetMapping("/envioinfo")
    public String enviarInfo(@RequestParam String titulo, Model model) {
        model.addAttribute("titulo", titulo);
        logger.info(titulo);
        return "pelicula";
    }
}
