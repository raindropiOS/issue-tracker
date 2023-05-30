package com.example.be.oauth;

import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@RestController
public class LoginController {

    @GetMapping("/")
    public Map<String, String> login(@AuthenticationPrincipal OAuth2User oAuth2User, HttpSession session) throws IOException {
        GitHub gitHub = new GitHubBuilder()
                .withOAuthToken(session.getAttribute("oAuthToken").toString(), oAuth2User.getName()).build();

        GHUser user = gitHub.getUser(oAuth2User.getName());

        return Map.of("userId", user.getLogin(),
                "nickName", user.getName(),
                "avatarUrl", user.getAvatarUrl());
    }

    @GetMapping("/login")
    public Map<String, Object> login() {
        return Map.of("status", 200,
                "success", "success",
                "message", "로그인 페이지",
                "login_url", "http://localhost:8080/oauth2/authorization/github");
    }
}
