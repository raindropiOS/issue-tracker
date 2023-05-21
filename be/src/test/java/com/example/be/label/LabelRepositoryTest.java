package com.example.be.label;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LabelRepositoryTest {

    @Autowired
    private LabelRepository labelRepository;

    @Test
    @DisplayName("라벨 이름들을 파라미터로 받아, 해당하는 라벨 목록들을 반환한다.")
    void getLabelsByNames() {
        List<String> labelNames1 = labelRepository.validateNames(List.of("feature", "fix"));
        assertThat(labelNames1).containsExactly("feature", "fix");

        List<String> labelNames2 = labelRepository.validateNames(List.of("feat", "fix"));
        assertThat(labelNames2).containsExactly("fix");
    }
}
