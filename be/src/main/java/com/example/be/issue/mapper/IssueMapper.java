package com.example.be.issue.mapper;

import com.example.be.issue.Issue;
import com.example.be.issue.dto.IssueCreateFormDTO;
import com.example.be.issue.dto.IssueSearchCondition;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IssueMapper {
    List<Issue> findIssuesWithoutLabelsBy(IssueSearchCondition issueSearchCondition);

    void save(IssueCreateFormDTO issueCreateFormDTO);

    void saveIssueLabelRelation(IssueCreateFormDTO issueCreateFormDTO);

    void saveAssignee(IssueCreateFormDTO issueCreateFormDTO);

}
