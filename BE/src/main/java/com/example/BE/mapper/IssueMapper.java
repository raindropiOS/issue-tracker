package com.example.BE.mapper;

import com.example.BE.issue.Issue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IssueMapper {
    List<Issue> findAllIssuesWithoutLabels();
}
