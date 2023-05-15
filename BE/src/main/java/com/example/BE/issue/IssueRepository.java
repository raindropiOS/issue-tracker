package com.example.BE.issue;

import com.example.BE.issue.dto.IssueLabelMap;
import com.example.BE.mapper.IssueLabelMapRowMapper;
import com.example.BE.mapper.IssueWithoutLabelsRowMapper;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface IssueRepository extends CrudRepository<Issue, Integer> {

    @Query(value = "select i.number, i.title, i.contents, i.state, i.created_date, " +
            "m.name, m.scheduled_completion_date, m.description_for_label, " +
            "u.id, u.password, u.nickname from ISSUE i " +
            "left outer join MILESTONE m on i.milestone_name = m.name " +
            "left outer join USER u on i.user_id = u.id",
            rowMapperClass = IssueWithoutLabelsRowMapper.class)
    List<Issue> findAllIssuesWithoutLabels();

    @Query(value = "select ir.issue_number, l.name, l.description, l.background_color, l.text_color " +
            "from ISSUE_LABEL_RELATION ir " +
            "left outer join LABEL l on ir.label_name = l.name " +
            "where ir.issue_number in (:issueNumbers)",
            rowMapperClass = IssueLabelMapRowMapper.class)
    List<IssueLabelMap> findAllIssueLabelMap(@Param("issueNumbers") Set<Integer> issueNumbers);
}
