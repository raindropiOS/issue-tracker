package com.example.BE.issue.dto;

import com.example.BE.issue.Issue;

import java.util.List;

public class IosIssueResponse {

    private List<Issue> issues;

    public IosIssueResponse(List<Issue> issues) {
        this.issues = issues;
    }

    public List<Issue> getIssues() {
        return issues;
    }

}
