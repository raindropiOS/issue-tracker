package com.example.BE.issue;

import com.example.BE.issue.dto.IssueLabelMap;
import com.example.BE.label.Label;
import com.example.BE.milestone.Milestone;
import com.example.BE.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class IssueRepositoryTest {

    @Autowired
    private IssueRepository issueRepository;

    @Test
    @DisplayName("IssueRepository 는 List<Labels> 를 채우지 않고 DB로 부터 데이터를 불러와 List<Issue> 목록을 반환한다.")
    void findAllIssuesWithoutLabels() {

        Issue simpleIssue1 = new Issue(1,
                "제목 1",
                "첫 번째 이슈 내용",
                true,
                LocalDateTime.of(2023, 5, 15, 11, 36, 45),
                new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                new User("1234", "codesquad", "BE"),
                new ArrayList<Label>());

        Issue simpleIssue2 = new Issue(2,
                "제목 2",
                "두 번째 이슈 내용",
                false,
                LocalDateTime.of(2023, 5, 15, 11, 36, 45),
                new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                new User("1234", "codesquad", "BE"),
                new ArrayList<Label>());

        Issue simpleIssue3 = new Issue(3,
                "제목 3",
                "세 번째 이슈 내용",
                true,
                LocalDateTime.of(2023, 5, 15, 11, 36, 45),
                new Milestone("BE STEP1", LocalDateTime.of(2023, 5, 20, 0, 0, 0), "BE 1주차 이슈들"),
                new User("1234", "codesquad", "BE"),
                new ArrayList<Label>());

        List<Issue> issuesWithoutLabels = issueRepository.findAllIssuesWithoutLabels();
        assertThat(issuesWithoutLabels).usingRecursiveFieldByFieldElementComparator().contains(simpleIssue1, simpleIssue2, simpleIssue3);
    }

    @Test
    @DisplayName("IssueRepository 는 Issue의 PK값과 대응되는 Label 객체를 가지는 List<IssueLabelMap> 목록을 반환한다.")
    void findAllIssueLabelMaps() {
        IssueLabelMap issueLabelMap1 = new IssueLabelMap(1, new Label("feature", "기능을 만들었슴둥", "#000000", "#004DE3"));
        IssueLabelMap issueLabelMap2 = new IssueLabelMap(1, new Label("fix", "버그를 고쳤음", "#123456", "#654321"));
        IssueLabelMap issueLabelMap3 = new IssueLabelMap(2, new Label("fix", "버그를 고쳤음", "#123456", "#654321"));

        List<IssueLabelMap> issueLabelMaps = issueRepository.findAllIssueLabelMap();
        assertThat(issueLabelMaps).usingRecursiveFieldByFieldElementComparator().contains(issueLabelMap1, issueLabelMap2, issueLabelMap3);
    }
}