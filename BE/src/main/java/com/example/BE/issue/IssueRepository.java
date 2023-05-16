package com.example.BE.issue;

import com.example.BE.issue.dto.Count;
import com.example.BE.issue.dto.IssueLabelMap;
import com.example.BE.issue.dto.IssueSearchCondition;
import com.example.BE.mapper.IssueMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IssueRepository {

    private final IssueMapper issueMapper;

    public IssueRepository(IssueMapper issueMapper) {
        this.issueMapper = issueMapper;
    }

    public List<Issue> findIssueWithoutLabelsBy(IssueSearchCondition issueSearchCondition) {
        return issueMapper.findIssueWithoutLabelsBy(issueSearchCondition);
    }

    public List<IssueLabelMap> findIssueLabelMapBy(IssueSearchCondition issueSearchCondition) {
        return issueMapper.findIssueLabelMapBy(issueSearchCondition);
    }

    public Count countEntities() {
        return issueMapper.countEntities();
    }

}
