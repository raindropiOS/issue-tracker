package com.example.be.issue.dto;

import com.example.be.issue.Issue;
import com.example.be.label.Label;
import com.example.be.milestone.Milestone;
import com.example.be.user.User;

import java.util.Collection;
import java.util.List;

public class FeIssueResponseDTO {

    private Collection<Issue> issues;
    private CountDTO counts;
    private List<Label> allLabels;
    private List<Milestone> allMilestones;
    private List<User> allUsers;

    public FeIssueResponseDTO(Collection<Issue> issues, CountDTO counts, List<Label> allLabels, List<Milestone> allMilestones, List<User> allUsers) {
        this.issues = issues;
        this.counts = counts;
        this.allLabels = allLabels;
        this.allMilestones = allMilestones;
        this.allUsers = allUsers;
    }

    public Collection<Issue> getIssues() {
        return issues;
    }

    public CountDTO getCounts() {
        return counts;
    }

    public List<Label> getAllLabels() {
        return allLabels;
    }

    public List<Milestone> getAllMilestones() {
        return allMilestones;
    }

    public List<User> getAllUsers() {
        return allUsers;
    }
}
