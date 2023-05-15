package com.example.BE.issue.dto;

import com.example.BE.issue.Issue;

import java.util.Collection;

public class FeIssueResponse {

    private Collection<Issue> issues;
    private Count counts;

    public FeIssueResponse(Collection<Issue> issues, Count counts) {
        this.issues = issues;
        this.counts = counts;
    }

    public Collection<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Collection<Issue> issues) {
        this.issues = issues;
    }

    public Count getCounts() {
        return counts;
    }

    public void setCounts(Count counts) {
        this.counts = counts;
    }
}
