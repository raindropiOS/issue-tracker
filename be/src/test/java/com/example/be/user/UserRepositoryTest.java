package com.example.be.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("담당자 이름들을 파라미터로 받아, 해당하는 유저 목록을 반환한다.")
    void findByNames() {
        // given
        User user1 = new User("cire", "codesquad2", "cire", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/img.jpg");
        User user2 = new User("ghkdgus29", "codesquad1", "hyun", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cute_cat.jpg");

        // when
        userRepository.save(user1);
        userRepository.save(user2);

        // then
        List<String> assignees1 = userRepository.validateNames(List.of("cire", "ghkdgus29"));
        assertThat(assignees1).containsExactlyInAnyOrder("cire", "ghkdgus29");

        List<String> assignees2 = userRepository.validateNames(List.of("cir", "ghkdgus29"));
        assertThat(assignees2).containsExactlyInAnyOrder("ghkdgus29");
    }

    @Test
    @DisplayName("특정 id의 유저를 업데이트 한다.")
    void updateUser() {
        // given
        User user = new User("cire", "codesquad2", "cire", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/img.jpg");
        User changeUser = new User("cire", "codesquad2", "cird", "change.jpg");

        userRepository.save(user);

        // when
        User savedUser = userRepository.save(changeUser.createEntityForUpdate());

        // then
        assertThat(savedUser.getNickname()).isEqualTo(changeUser.getNickname());
        assertThat(savedUser.getImgUrl()).isEqualTo(changeUser.getImgUrl());
    }
}
