package com.example.be.issue.dto;

import java.util.List;

public class IssueCreateFormDTO {

    private int issueNumber;
    private String userId;
    private String title;
    private String contents;
    private List<String> assignees;
    private String milestoneName;
    private List<String> labelNames;

    public IssueCreateFormDTO(String title, String contents, List<String> assignees, String milestoneName, List<String> labelNames) {
        this.title = title;
        this.contents = contents;
        this.assignees = assignees;
        this.milestoneName = milestoneName;
        this.labelNames = labelNames;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public List<String> getAssignees() {
        return assignees;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public List<String> getLabelNames() {
        return labelNames;
    }
}
