package com.example.be.issue;

import com.example.be.issue.dto.IssueCreateFormDTO;
import com.example.be.issue.dto.IssueSearchCondition;
import com.example.be.issue.mapper.IssueMapper;
import com.example.be.util.Paging;

import java.util.List;

public class IssueRepositoryImpl implements IssueRepositoryCustom{

    private final IssueMapper issueMapper;

    public IssueRepositoryImpl(IssueMapper issueMapper) {
        this.issueMapper = issueMapper;
    }

    @Override
    public List<Issue> findIssuesWithoutLabelsBy(IssueSearchCondition issueSearchCondition) {
        return issueMapper.findIssuesWithoutLabelsBy(issueSearchCondition);
    }

    @Override
    public int findIssueSize(IssueSearchCondition issueSearchCondition) {
        return issueMapper.findIssueSize(issueSearchCondition);
    }

    @Override
    public int save(IssueCreateFormDTO issueCreateFormDTO) {
        issueMapper.save(issueCreateFormDTO);

        if (issueCreateFormDTO.getLabelNames() != null) {
            issueMapper.saveIssueLabelRelation(issueCreateFormDTO);
        }

        if (issueCreateFormDTO.getAssignees() != null) {
            issueMapper.saveAssignee(issueCreateFormDTO);
        }

        return issueCreateFormDTO.getIssueNumber();
    }
}
