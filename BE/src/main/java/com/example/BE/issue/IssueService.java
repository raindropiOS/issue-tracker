package com.example.BE.issue;

import com.example.BE.issue.dto.FeIssueResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

    private final IssueRepository issueRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public FeIssueResponse makeFeIssueResponse() {
        return new FeIssueResponse(makeIssuesWithoutLabels(), null);
    }

    private List<Issue> makeIssuesWithoutLabels() {
        return issueRepository.findAllIssuesWithoutLabels();
    }

}
