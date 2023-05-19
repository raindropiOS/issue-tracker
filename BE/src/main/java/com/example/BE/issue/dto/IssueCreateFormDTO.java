package com.example.BE.issue.dto;

import java.util.List;

public class IssueCreateFormDTO {

    private String title;
    private String contents;
    private List<String> assigness;
    private String milestoneName;
    private List<String> labelNames;

    public IssueCreateFormDTO(String title, String contents, List<String> assigness, String milestoneName, List<String> labelNames) {
        this.title = title;
        this.contents = contents;
        this.assigness = assigness;
        this.milestoneName = milestoneName;
        this.labelNames = labelNames;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public List<String> getAssigness() {
        return assigness;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public List<String> getLabelNames() {
        return labelNames;
    }
}
