package com.ja.finalproject.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class TestController {

    @RequestMapping("test1")
    public String test1(HttpSession session, Model model) {

        String message = "출력해야할 메세지";

        // session.setAttribute("qwer", message);
        // request.setAttribute("qwer", message);
        model.addAttribute("qwer", message);

        return "test/test";

    }
}