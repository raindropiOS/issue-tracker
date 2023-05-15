package com.example.BE.issue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IssueServiceTest {

    @Autowired
    private IssueService issueService;

    @Autowired
    private IssueRepository issueRepository;
    @Test
    void makeFeIssuesApi() {
        issueService.makeFeIssuesResponse();
    }
}