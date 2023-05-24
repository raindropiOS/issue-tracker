package com.example.be.label.dto;

public class LabelCreateFormDTO {

    private String name;
    private String description;
    private String backgroundColor;
    private String textColor;

    public LabelCreateFormDTO() {
        backgroundColor = "#B60205";
        textColor = "#E99695";
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }
}