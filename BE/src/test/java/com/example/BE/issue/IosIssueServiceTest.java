package com.example.BE.issue;

import com.example.BE.issue.dto.IosIssueResponse;
import com.example.BE.issue.dto.IssueSearchCondition;
import com.example.BE.label.Label;
import com.example.BE.milestone.Milestone;
import com.example.BE.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class IosIssueServiceTest {
    @Autowired
    public IosIssueService issueService;

    private IssueSearchCondition issueSearchCondition;

    @BeforeEach
    public void beforeEach() {
        issueSearchCondition = new IssueSearchCondition();
        issueSearchCondition.setState(true);
        issueSearchCondition.setAuthor("BE");
        issueSearchCondition.setMilestoneName("BE STEP1");
        issueSearchCondition.setLabelNames(List.of("feature", "fix"));
    }

    @Test
    public void makeCompleteIssuesTest() throws ParseException {
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
        user1.setImgUrl("https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg");
        issue1.setUser(user1);

        Label label1 = new Label();
        label1.setName("feature");
        label1.setDescription("기능을 만들었슴둥");
        label1.setBackgroundColor("#000000");
        label1.setTextColor("#004DE3");
        issue1.addLabel(label1);

        Label label2 = new Label();
        label2.setName("fix");
        label2.setDescription("버그를 고쳤음");
        label2.setBackgroundColor("#123456");
        label2.setTextColor("#654321");
        issue1.addLabel(label2);

        // when
        IosIssueResponse iosIssueResponse = issueService.findIssuesBy(issueSearchCondition);
        List<Issue> issues = iosIssueResponse.getIssues();

        // then
        assertThat(issues).usingRecursiveFieldByFieldElementComparator().contains(issue1);

    }

}