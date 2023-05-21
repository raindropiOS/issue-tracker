package com.example.be.milestone;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MilestonRepositoryTest {

    @Autowired
    private MilestonRepository milestonRepository;

    @Test
    @DisplayName("마일스톤 이름을 파라미터로 받아, 해당하는 마일스톤을 반환한다.")
    void findByName() {
        Milestone milestone = new Milestone("BE STEP1", LocalDateTime.of(2023,5,20,0,0,0), "BE 1주차 이슈들");

        Milestone findMilestone = milestonRepository.findByName("BE STEP1");

        assertThat(findMilestone).usingRecursiveComparison().isEqualTo(milestone);
    }
}
