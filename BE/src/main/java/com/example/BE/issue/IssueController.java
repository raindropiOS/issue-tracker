package com.example.BE.issue;

import com.example.BE.issue.dto.FeIssueResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IssueController {

    private final IssueService issueService;

    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping("/api-fe/issues")
    public FeIssueResponse findIssues() {
        return issueService.makeFeIssuesResponse();
    }
}
