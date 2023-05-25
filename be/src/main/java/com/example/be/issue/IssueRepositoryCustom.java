package com.example.be.issue;

import com.example.be.issue.dto.IssueAssignsUpdateFormDTO;
import com.example.be.issue.dto.IssueCreateFormDTO;
import com.example.be.issue.dto.IssueSearchCondition;
import com.example.be.issue.dto.IssueUpdateFormDTO;
import com.example.be.util.Paging;

import java.util.List;

public interface IssueRepositoryCustom {

    List<Issue> findIssuesWithoutLabelsBy(IssueSearchCondition issueSearchCondition);

    int findIssueSize(IssueSearchCondition issueSearchCondition);

    int save(IssueCreateFormDTO issueCreateFormDTO);

    boolean update(IssueUpdateFormDTO issueUpdateFormDTO);

    boolean updateAssigns(IssueAssignsUpdateFormDTO issueAssignsUpdateFormDTO);
}
