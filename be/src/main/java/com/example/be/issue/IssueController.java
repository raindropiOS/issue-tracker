package com.example.be.issue;

import com.example.be.issue.dto.FeIssueResponseDTO;
import com.example.be.issue.dto.IosIssueResponseDTO;
import com.example.be.issue.dto.IssueCreateFormDTO;
import com.example.be.issue.dto.IssueSearchCondition;
import com.example.be.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public Map<String, Integer> createIssue(@Validated @ModelAttribute IssueCreateFormDTO issueCreateFormDTO) {
        User testUser = new User("1234", "codesquad", "BE", "https://issue-tracker-03.s3.ap-northeast-2.amazonaws.com/cat.jpg");
        issueCreateFormDTO.setUserId(testUser.getId());
        int issueNumber = issueService.createIssue(issueCreateFormDTO);
        return Map.of("issueNumber", issueNumber);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Map<String, String> issueCreateFormExHandler(BindException e) {
        return Map.of("fail", e.getMessage());
    }

}
