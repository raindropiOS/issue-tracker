package com.example.BE.dto.show.issue.detailed;

public class SimpleMilestone {

    private String milestoneName;

    public SimpleMilestone() {
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public SimpleMilestone(String name) {
        this.milestoneName = name;
    }

    public String getMilestoneName() {
        return milestoneName;
    }
}
