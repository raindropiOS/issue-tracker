package com.example.BE.issue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class IssueRepositoryTest {

    @Autowired
    private IssueRepository issueRepository;

    @Test
    void findAllIssues() {

        List<Issue> all = issueRepository.findAllIssuesWithoutLabels();

        System.out.println(all);
    }

    @Test
    void findAllIssueLabelMap() {

        List<IssueLabelMap> all = issueRepository.findAllIssueLabelMap();

        System.out.println(all);
    }

    @Test
    void countAll() {
        Count count = issueRepository.countEntities();

        System.out.println(count);

    }
}