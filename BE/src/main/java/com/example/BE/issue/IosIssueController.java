package com.example.BE.issue;

import com.example.BE.issue.dto.IosIssueResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-ios")
public class IosIssueController {

    private final IosIssueService issueService;

    @Autowired
    public IosIssueController(IosIssueService issueService) {
        this.issueService = issueService;
    }

    @GetMapping(value = "/issues")
    public IosIssueResponse findFilterIssues(@RequestParam(value = "open", required = false, defaultValue = "true") boolean state) {
        return issueService.findFilterIssues(state);
    }

}
