package com.example.be.issue;

import com.example.be.issue.dto.IssueCreateFormDTO;
import com.example.be.issue.dto.IssueSearchCondition;
import com.example.be.issue.dto.IssueUpdateFormDTO;
import com.example.be.issue.mapper.IssueMapper;

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

    @Override
    public boolean update(IssueUpdateFormDTO issueUpdateFormDTO) {
        if (issueMapper.validIssue(issueUpdateFormDTO.getIssueNumber()) != 1) {
            return false;
        }
        if (issueUpdateFormDTO.getMilestoneName() != null && issueMapper.validMilestone(issueUpdateFormDTO.getMilestoneName()) != 1) {
            return false;
        }
        return issueMapper.updateIssue(issueUpdateFormDTO) == 1;
    }
}
