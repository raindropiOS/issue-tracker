package com.example.be.comment.dto;

public class CommentInputFormDTO {

    private String contents;

    public CommentInputFormDTO() {
    }

    public CommentInputFormDTO(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }
}
