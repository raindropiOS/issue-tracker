package com.example.BE.issue;

import com.example.BE.issue.dto.FeIssueResponseDTO;
import com.example.BE.issue.dto.IosIssueResponseDTO;
import com.example.BE.issue.dto.IssueCreateFormDTO;
import com.example.BE.issue.dto.IssueSearchCondition;
import com.example.BE.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IssueController {

    private final IssueService issueService;

    @Autowired
    public IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping("/api-fe/issues")
    public FeIssueResponseDTO createFeIssueResponse(@ModelAttribute IssueSearchCondition issueSearchCondition) {
        return issueService.makeFeIssueResponse(issueSearchCondition);
    }

    @GetMapping("/api-ios/issues")
    public IosIssueResponseDTO createIosIssueResponse(@ModelAttribute IssueSearchCondition issueSearchCondition) {
        return issueService.makeIosIssueResponse(issueSearchCondition);
    }

    @PostMapping("/api/issues")
    public Issue createIssue(@ModelAttribute IssueCreateFormDTO issueCreateFormDTO) {
        User testUser = new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg");
        return issueService.createIssue(testUser, issueCreateFormDTO);
    }

}
