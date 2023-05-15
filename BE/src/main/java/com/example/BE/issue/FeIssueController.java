package com.example.BE.issue;

import com.example.BE.issue.dto.FeIssueResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-fe")
public class FeIssueController {

    private final IssueService issueService;

    @Autowired
    public FeIssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping("/issues")
    public FeIssueResponse createFeIssueResponse() {
        return issueService.makeFeIssueResponse();
    }
}
