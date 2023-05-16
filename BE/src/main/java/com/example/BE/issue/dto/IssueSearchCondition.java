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

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public List<String> getLabelNames() {
        return labelNames;
    }

    public void setLabelNames(List<String> labelNames) {
        this.labelNames = labelNames;
    }
}
