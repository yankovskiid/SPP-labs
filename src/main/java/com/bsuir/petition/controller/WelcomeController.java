package com.bsuir.petition.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class WelcomeController {

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public ModelAndView helloWorld() {
        String temp = "<h1>Hello, World, finally</h1>";

        return new ModelAndView("hello", "message", temp);
    }
}
