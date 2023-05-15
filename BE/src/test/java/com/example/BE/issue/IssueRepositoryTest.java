package com.example.BE.issue;

import com.example.BE.dto.show.issue.detailed.FeSimpleIssue;
import com.example.BE.dto.show.issue.detailed.SimpleAuthor;
import com.example.BE.dto.show.issue.detailed.SimpleLabel;
import com.example.BE.dto.show.issue.detailed.SimpleMilestone;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class IssueRepositoryTest {

    @Autowired
    public IssueRepository issueRepository;

    @Test
    public void getFeSimpleIssueTest() throws ParseException {
        String strDate = "2023-05-14 03:27:55";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsedDate = dateFormat.parse(strDate);
        Timestamp timestamp = new Timestamp(parsedDate.getTime());

        // given
        FeSimpleIssue feSimpleIssue1 = new FeSimpleIssue(1,
                "제목 1",
                true,
                timestamp.toLocalDateTime(),
                new ArrayList<SimpleLabel>(List.of(new SimpleLabel("fix", "#654321", "#123456"))),
                new SimpleMilestone("BE STEP1"),
                new SimpleAuthor("BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"));

        FeSimpleIssue feSimpleIssue2 = new FeSimpleIssue(1,
                "제목 1",
                true,
                timestamp.toLocalDateTime(),
                new ArrayList<SimpleLabel>(List.of(new SimpleLabel("feature", "#004DE3", "#000000"))) ,
                new SimpleMilestone("BE STEP1"),
                new SimpleAuthor("BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"));

        FeSimpleIssue feSimpleIssue3 = new FeSimpleIssue(2,
                "제목 2",
                false,
                timestamp.toLocalDateTime(),
                new ArrayList<SimpleLabel>(List.of(new SimpleLabel("fix", "#654321", "#123456"))),
                new SimpleMilestone("BE STEP1"),
                new SimpleAuthor("BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"));

        FeSimpleIssue feSimpleIssue4 = new FeSimpleIssue(3,
                "제목 3",
                true,
                timestamp.toLocalDateTime(),
                new ArrayList<SimpleLabel>(List.of(new SimpleLabel(null, null, null))),
                new SimpleMilestone("BE STEP1"),
                new SimpleAuthor("BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg"));

        // when
        List<FeSimpleIssue> feSimpleIssues = issueRepository.getFeSimpleIssues();

        // then
        assertThat(feSimpleIssues).usingRecursiveFieldByFieldElementComparator().contains(feSimpleIssue1, feSimpleIssue2, feSimpleIssue3, feSimpleIssue4);

    }
}