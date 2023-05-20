package com.example.be.issue.dto;

import com.example.be.issue.Issue;

import java.util.Collection;

public class IosIssueResponseDTO {

    private Collection<Issue> issues;

    public IosIssueResponseDTO(Collection<Issue> issues) {
        this.issues = issues;
    }

    public Collection<Issue> getIssues() {
        return issues;
    }

}
