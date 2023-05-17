package com.example.BE.issue;

import com.example.BE.mapper.IssueMapper;

import java.util.List;

public class IssueRepositoryImpl implements IssueRepositoryCustom{

    private final IssueMapper issueMapper;

    public IssueRepositoryImpl(IssueMapper issueMapper) {
        this.issueMapper = issueMapper;
    }

    @Override
    public List<Issue> findAllIssuesWithoutLabels() {
        return issueMapper.findAllIssuesWithoutLabels();
    }
}