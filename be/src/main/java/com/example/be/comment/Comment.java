package com.example.be.comment;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("COMMENT")
public class Comment {

    @Id
    private Integer id;

    private String contents;
    private LocalDateTime createdData;
    private LocalDateTime lastUpdatedDate;
    private boolean deleted;

    public int getId() {
        return id;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreatedData() {
        return createdData;
    }

    public LocalDateTime getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

}
