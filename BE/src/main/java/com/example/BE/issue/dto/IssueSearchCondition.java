package com.example.BE.issue.dto;

import java.util.List;

public class IssueSearchCondition {

    public boolean state;
    public String author;
    public String assignee;
    public String milestoneName;
    public List<String> labelNames;

    public IssueSearchCondition() {
        this.state = true;
    }

    public Integer getLabelNamesSize() {
        return labelNames.size();
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public void setLabelNames(List<String> labelNames) {
        this.labelNames = labelNames;
    }
}
