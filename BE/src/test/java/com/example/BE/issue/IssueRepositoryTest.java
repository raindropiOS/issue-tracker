package com.example.BE.issue;

import com.example.BE.issue.dto.IssueLabelMap;
import com.example.BE.label.Label;
import com.example.BE.milestone.Milestone;
import com.example.BE.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class IssueRepositoryTest {

    @Autowired
    public IssueRepository issueRepository;

    @Test
    public void findAllIssuesWithoutLabels() throws ParseException {
        String strCreatedDate = "2023-05-15 18:24:05";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsedDate = dateFormat.parse(strCreatedDate);
        Timestamp createdDate = new Timestamp(parsedDate.getTime());
        LocalDateTime localDateTime = createdDate.toLocalDateTime();

        String strScheduledCompletionDate = "2023-05-20 00:00:00";
        parsedDate = dateFormat.parse(strScheduledCompletionDate);
        Timestamp scheduledCompletionDate = new Timestamp(parsedDate.getTime());

        // given
        Issue issue1 = new Issue();
        issue1.setNumber(1);
        issue1.setTitle("제목 1");
        issue1.setContents("첫 번째 이슈 내용");
        issue1.setState(true);
        issue1.setCreatedDate(localDateTime);
        issue1.setLastUpdatedDate(localDateTime);
        Milestone milestone1 = new Milestone();
        milestone1.setName("BE STEP1");
        milestone1.setDescriptionForLabel("BE 1주차 이슈들");
        milestone1.setScheduledCompletionDate(scheduledCompletionDate.toLocalDateTime());
        issue1.setMilestone(milestone1);
        User user1 = new User();
        user1.setId("1234");
        user1.setPassword("codesquad");
        user1.setNickname("BE");
        issue1.setUser(user1);

        // when
        List<Issue> issues = issueRepository.findAllIssuesWithoutLabels();

        // then
        assertThat(issues).usingRecursiveFieldByFieldElementComparator().contains(issue1);

    }

    @Test
    public void getSimpleLabelTest() throws ParseException {
        // given
        Label label1 = new Label();
        label1.setName("feature");
        label1.setDescription("기능을 만들었슴둥");
        label1.setBackgroundColor("#000000");
        label1.setTextColor("#004DE3");

        Label label2 = new Label();
        label2.setName("fix");
        label2.setDescription("버그를 고쳤음");
        label2.setBackgroundColor("#123456");
        label2.setTextColor("#654321");

        IssueLabelMap issueLabelMap1 = new IssueLabelMap();
        issueLabelMap1.setIssueNumber(1);
        issueLabelMap1.setLabel(label1);

        IssueLabelMap issueLabelMap2 = new IssueLabelMap();
        issueLabelMap2.setIssueNumber(1);
        issueLabelMap2.setLabel(label2);

        IssueLabelMap issueLabelMap3 = new IssueLabelMap();
        issueLabelMap3.setIssueNumber(1);
        issueLabelMap3.setLabel(label2);

        // when
        List<IssueLabelMap> allIssueLabelMap = issueRepository.findAllIssueLabelMap();

        // then
        assertThat(allIssueLabelMap).usingRecursiveFieldByFieldElementComparator().contains(issueLabelMap1, issueLabelMap2, issueLabelMap3);

    }

}
