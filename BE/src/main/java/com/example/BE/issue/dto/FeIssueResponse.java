package com.example.BE.issue.dto;

import com.example.BE.issue.Count;
import com.example.BE.issue.Issue;

import java.util.List;

public class FeIssueResponse {

    private List<Issue> issues;
    private Count counts;

    public FeIssueResponse(List<Issue> issues, Count counts) {
        this.issues = issues;
        this.counts = counts;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public Count getCounts() {
        return counts;
    }
}
