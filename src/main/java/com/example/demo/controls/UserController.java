package com.example.demo.controls;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/demo")
public class UserController{
    @RequestMapping("/handle1.do")
    public ModelAndView handleRequest() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("username","师姐");
        mv.setViewName("success");
        return mv;
    }
}
