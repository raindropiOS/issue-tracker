package com.example.BE.issue;

public class Count {

    private int openedIssuesCount;
    private int closedIssuesCount;
    private int labelsCount;
    private int milestoneCount;

    public Count() {
    }

    public int getOpenedIssuesCount() {
        return openedIssuesCount;
    }

    public void setOpenedIssuesCount(int openedIssuesCount) {
        this.openedIssuesCount = openedIssuesCount;
    }

    public int getClosedIssuesCount() {
        return closedIssuesCount;
    }

    public void setClosedIssuesCount(int closedIssuesCount) {
        this.closedIssuesCount = closedIssuesCount;
    }

    public int getLabelsCount() {
        return labelsCount;
    }

    public void setLabelsCount(int labelsCount) {
        this.labelsCount = labelsCount;
    }

    public int getMilestoneCount() {
        return milestoneCount;
    }

    public void setMilestoneCount(int milestoneCount) {
        this.milestoneCount = milestoneCount;
    }

    @Override
    public String toString() {
        return "Count{" +
                "openedIssuesCount=" + openedIssuesCount +
                ", closedIssuesCount=" + closedIssuesCount +
                ", labelsCount=" + labelsCount +
                ", milestoneCount=" + milestoneCount +
                '}';
    }
}
