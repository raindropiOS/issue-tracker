package com.example.BE.dto.show.issue.detailed;

public class SimpleLabel {

    private int issueNumber;

    private String labelName;

    private String textColor;
    private String backgroundColor;
    public SimpleLabel() {}

    public SimpleLabel(String name, String textColor, String backgroundColor) {
        this.labelName = name;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
    }

    public SimpleLabel(int issueNumber, String labelName, String textColor, String backgroundColor) {
        this.issueNumber = issueNumber;
        this.labelName = labelName;
        this.textColor = textColor;
        this.backgroundColor = backgroundColor;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
}
