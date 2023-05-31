package com.example.be.oauth;

import com.example.be.oauth.dto.SessionConst;
import com.example.be.oauth.dto.UserInfoDTO;
import com.example.be.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@RestController
public class LoginController {

    @GetMapping("/")
    public UserInfoDTO login(HttpSession session) throws IOException {
        User sessionUser = (User) session.getAttribute(SessionConst.USER);

        return new UserInfoDTO(sessionUser.getId(), sessionUser.getNickname(), sessionUser.getImgUrl());
    }

    @GetMapping("/login")
    public Map<String, Object> login() {
        return Map.of("status", 200,
                "success", "success",
                "message", "로그인 페이지",
                "login_url", "http://localhost:8080/oauth2/authorization/github");
    }
}
