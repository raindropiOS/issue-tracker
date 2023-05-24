package com.example.be.milestone;

import com.example.be.milestone.dto.MilestoneCreateFormDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class MilestoneServiceTest {

    @Autowired
    private MilestoneService milestoneService;

    MilestoneCreateFormDTO milestoneCreateFormDTO;

    @BeforeEach
    private void beforeEach() {
        this.milestoneCreateFormDTO= new MilestoneCreateFormDTO();
        milestoneCreateFormDTO.setName("test1234");
    }

    @Test
    @DisplayName("마일스톤 엔티티를 파라미터로 받아, 마일스톤을 저장해야한다.")
    void createMilestoneTest() {
        // given
        Milestone expected = new Milestone(milestoneCreateFormDTO);

        // when
        milestoneService.createMilestone(milestoneCreateFormDTO);
        Optional<Milestone> optionalActual = milestoneService.findMilestoneBy("test1234");
        Milestone actual = optionalActual.get();

        // then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }




}