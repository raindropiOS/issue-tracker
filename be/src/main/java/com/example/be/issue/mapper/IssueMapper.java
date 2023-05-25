package com.example.be.issue.mapper;

import com.example.be.issue.Issue;
import com.example.be.issue.dto.IssueCreateFormDTO;
import com.example.be.issue.dto.IssueSearchCondition;
import com.example.be.issue.dto.IssueUpdateFormDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IssueMapper {
    List<Issue> findIssuesWithoutLabelsBy(IssueSearchCondition issueSearchCondition);

    int findIssueSize(IssueSearchCondition issueSearchCondition);

    void save(IssueCreateFormDTO issueCreateFormDTO);

    void saveIssueLabelRelation(IssueCreateFormDTO issueCreateFormDTO);

    void saveAssignee(IssueCreateFormDTO issueCreateFormDTO);

    int updateIssue(IssueUpdateFormDTO issueUpdateFormDTO);

    int validIssue(int issueNumber);

    int validMilestone(String milestoneName);

    void insertAssigns(IssueUpdateFormDTO issueUpdateFormDTO);

    void deleteAssigns(IssueUpdateFormDTO issueUpdateFormDTO);

    void insertIssueLabelRelation(IssueUpdateFormDTO issueUpdateFormDTO);

    void deleteIssueLabelRelation(IssueUpdateFormDTO issueUpdateFormDTO);

}
