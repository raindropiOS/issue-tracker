package com.example.BE.issue;

import com.example.BE.issue.dto.Count;
import com.example.BE.issue.dto.FeIssueResponse;
import com.example.BE.issue.dto.IssueLabelMap;
import com.example.BE.issue.dto.IssueSearchCondition;
import com.example.BE.label.Label;
import com.example.BE.milestone.Milestone;
import com.example.BE.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FeIssueServiceTest {

    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private FeIssueService issueService;

    @Test
    @DisplayName("labels 를 채우지 않은 Issue 목록과 IssueLabelMap 목록을 가지고 조립하여 완전한 Issue 목록을 만들고, 여러 통계를 나타내는 Count 클래스와 wrapping 하여 FE용 DTO객체를 반환한다.")
    void makeFeIssueResponse() {

        Milestone milestone = new Milestone("마일1", LocalDateTime.now(), "마일1내용");
        User user = new User("hyun", "1234", "hyun", "/cat.jpg");
        Issue issueWithoutLabels1 = new Issue(1, "제목1", "내용1", true, LocalDateTime.now(), milestone, user);
        Issue issueWithoutLabels2 = new Issue(2, "제목2", "내용2", true, LocalDateTime.now(), milestone, user);
        Issue issueWithoutLabels3 = new Issue(3, "제목3", "내용3", false, LocalDateTime.now(), milestone, user);

        BDDMockito.given(issueRepository.findAllIssuesWithoutLabelsBy(ArgumentMatchers.any()))
                .willReturn(List.of(issueWithoutLabels1, issueWithoutLabels2, issueWithoutLabels3));

        Label label1 = new Label("라벨1", "라벨내용1", "#000000", "#000000");
        Label label2 = new Label("라벨2", "라벨내용2", "#111111", "#111111");
        IssueLabelMap issueLabelMap1 = new IssueLabelMap(1, label1);
        IssueLabelMap issueLabelMap2 = new IssueLabelMap(1, label2);
        IssueLabelMap issueLabelMap3 = new IssueLabelMap(2, label1);


        BDDMockito.given(issueRepository.findAllIssueLabelMap(Set.of(1, 2, 3)))
                .willReturn(List.of(issueLabelMap1, issueLabelMap2, issueLabelMap3));

        Count count = new Count(2, 1, 2, 1);

        BDDMockito.given(issueRepository.countEntities())
                .willReturn(count);

        FeIssueResponse feIssueResponse = issueService.makeFeIssueResponse(ArgumentMatchers.any());

        issueWithoutLabels1.add(issueLabelMap1);
        issueWithoutLabels1.add(issueLabelMap2);
        issueWithoutLabels2.add(issueLabelMap3);

        Collection<Issue> issues = Map.of(1, issueWithoutLabels1, 2, issueWithoutLabels2, 3, issueWithoutLabels3).values();
        FeIssueResponse expectedFeIssueResponse = new FeIssueResponse(issues, count);


        assertThat(feIssueResponse).usingRecursiveComparison().isEqualTo(expectedFeIssueResponse);
    }
}
