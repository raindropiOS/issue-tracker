package com.example.be.oauth;

import com.example.be.oauth.dto.SessionConst;
import com.example.be.user.User;
import com.example.be.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Service
@Transactional
public class CustomOAuth2UserService implements OAuth2UserService {

    private static final String ID = "login";
    private static final String NICKNAME = "name";
    private static final String IMG_URL = "avatar_url";

    private final UserRepository userRepository;
    private final HttpSession session;

    @Autowired
    public CustomOAuth2UserService(UserRepository userRepository, HttpSession session) {
        this.userRepository = userRepository;
        this.session = session;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        DefaultOAuth2UserService service = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = service.loadUser(userRequest);

        User user = saveOrUpdate(oAuth2User);
        session.setAttribute(SessionConst.USER, user);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getId())),
                oAuth2User.getAttributes(), ID);
    }

    private User saveOrUpdate(OAuth2User oAuth2User) {
        User user = new User(oAuth2User.getAttribute(ID),
                oAuth2User.getAttribute(NICKNAME),
                oAuth2User.getAttribute(IMG_URL));

        if (userRepository.existsById(user.getId())) {
            return userRepository.save(user.createEntityForUpdate());
        }

        return userRepository.save(user.createEntityForInsert());
    }

}
