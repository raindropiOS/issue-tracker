package com.example.BE.issue;

import com.example.BE.label.Label;
import com.example.BE.milestone.Milestone;
import com.example.BE.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table("ISSUE")
public class Issue {

    @Id
    private Integer number;

    private String title;
    private String contents;
    private boolean state;
    private LocalDateTime createdDate;

    private Milestone milestone;
    private User user;

    private List<Label> labels = new ArrayList<>();

    public Issue() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void addLabel(Label label) {
        this.labels.add(label);
    }

    @Override
    public String toString() {
        return "Issue{" +
                "number=" + number +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", state=" + state +
                ", createdDate=" + createdDate +
                ", milestone=" + milestone +
                ", user=" + user +
                ", labels=" + labels +
                '}';
    }
}
