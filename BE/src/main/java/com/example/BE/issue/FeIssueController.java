package com.example.BE.issue;

import com.example.BE.issue.dto.FeIssueResponse;
import com.example.BE.issue.dto.IssueSearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-fe")
public class FeIssueController {

    private final FeIssueService feIssueService;

    @Autowired
    public FeIssueController(FeIssueService feIssueService) {
        this.feIssueService = feIssueService;
    }

    @GetMapping("/issues")
    public FeIssueResponse createFeIssueResponse(@ModelAttribute IssueSearchCondition issueSearchCondition) {
        System.out.println(issueSearchCondition);
        return feIssueService.makeFeIssueResponse(issueSearchCondition);
    }
}
