package com.example.be.label;

import org.assertj.core.api.Assertions;
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
        Label label1 = new Label("feature", "기능을 만들었슴둥", "#000000", "#004DE3");
        Label label2 = new Label("fix", "버그를 고쳤음", "#123456", "#654321");

        List<Label> labels = labelRepository.findByNames(List.of("feature", "fix"));

        assertThat(labels).usingRecursiveFieldByFieldElementComparator().containsExactly(label1, label2);
    }
}
