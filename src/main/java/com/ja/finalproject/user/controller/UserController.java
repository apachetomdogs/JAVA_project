package com.ja.finalproject.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ja.finalproject.dto.UserDto;
import com.ja.finalproject.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;



    @RequestMapping("loginPage")
    public String loginPage() {
        return "user/loginPage"; // html 위치
    }

    @RequestMapping("registerPage")
    public String registerPage() { // 메서드명은 동작에 중요X, But 맞춰주는 것이 좋음

        return "user/registerPage";
    }

    @RequestMapping("registerProcess")
    public String registerProcess(UserDto params) {
        
        // System.out.println(params);
        userService.register(params);

        return "user/registerComplete";
    }

    @RequestMapping("loginProcess")
    public String loginProcess(HttpSession session, UserDto params) {

        UserDto sessionUserInfo = userService.getUserByUserIdAndPassword(params);

        if(sessionUserInfo == null) {
            return "user/loginFail";
        }

        // 로그인 성공
        session.setAttribute("sessionUserInfo", sessionUserInfo);

        return "redirect:/board/mainPage";

    }

    @RequestMapping("logoutProcess")
    public String logoutProcess(HttpSession session) {
        session.invalidate();
        return "redirect:/board/mainPage";
    }

}


