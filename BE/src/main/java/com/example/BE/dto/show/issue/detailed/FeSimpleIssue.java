package com.example.BE.dto.show.issue.detailed;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FeSimpleIssue {

    private int number;
    private String title;
    private boolean state;
    private LocalDateTime createdDate;
    private List<SimpleLabel> labels;
    private SimpleMilestone milestone;

    private SimpleAuthor author;

    public FeSimpleIssue() {
        this.labels = new ArrayList<>();
    };

    public FeSimpleIssue(int number, String title, boolean state, LocalDateTime createdDate, List<SimpleLabel> labels, SimpleMilestone milestone, SimpleAuthor author) {
        this.labels = new ArrayList<>();
        this.number = number;
        this.title = title;
        this.state = state;
        this.createdDate = createdDate;
        this.labels = labels;
        this.milestone = milestone;
        this.author = author;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setLabels(List<SimpleLabel> labels) {
        this.labels = labels;
    }

    public void addLabel(SimpleLabel label) {
        if (label != null) {
            labels.add(label);
        }
    }

    public void setMilestone(SimpleMilestone milestone) {
        this.milestone = milestone;
    }

    public void setAuthor(SimpleAuthor author) {
        this.author = author;
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public boolean isState() {
        return state;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public List<SimpleLabel> getLabels() {
        return labels;
    }

    public SimpleMilestone getMilestone() {
        return milestone;
    }

    public SimpleAuthor getAuthor() {
        return author;
    }
}
