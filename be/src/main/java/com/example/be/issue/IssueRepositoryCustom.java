package com.example.be.issue;

import com.example.be.issue.dto.IssueCreateFormDTO;
import com.example.be.issue.dto.IssueSearchCondition;

import java.util.List;

public interface IssueRepositoryCustom {

    List<Issue> findIssuesWithoutLabelsBy(IssueSearchCondition issueSearchCondition);

    void save(IssueCreateFormDTO issueCreateFormDTO);
}
