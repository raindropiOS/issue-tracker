package com.example.BE.issue;

import com.example.BE.issue.dto.Count;
import com.example.BE.issue.dto.IssueLabelMap;
import com.example.BE.issue.mapper.CountRowMapper;
import com.example.BE.issue.mapper.IssueLabelMapRowMapper;
import com.example.BE.issue.mapper.IssueMilestoneUserRowMapper;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IosIssueRepository extends CrudRepository<Issue, Integer> {

    @Query(value = "select i.number, i.title, i.contents, i.state, i.created_date, i.last_updated_date, " +
            "m.name, m.scheduled_completion_date, m.description_for_label, u.id, u.password, u.nickname from ISSUE i " +
            "left outer join MILESTONE m on i.milestone_name = m.name " +
            "left outer join USER u on i.user_id = u.id",
            rowMapperClass = IssueMilestoneUserRowMapper.class)
    List<Issue> findAllIssuesWithoutLabels();

    @Query(value = "select ir.issue_number, l.name, l.description, l.background_color, l.text_color " +
            "from ISSUE_LABEL_RELATION ir " +
            "left outer join LABEL l on ir.label_name = l.name",
            rowMapperClass = IssueLabelMapRowMapper.class)
    List<IssueLabelMap> findAllIssueLabelMap();

    @Query(value = "select " +
            "(select count(number) from ISSUE where state=true) as openedIssuesCount, " +
            "(select count(number) from ISSUE where state=false) as closedIssuesCount, " +
            "(select count(name) from LABEL) as labelsCount, " +
            "(select count(name) from MILESTONE) as milestoneCount",
            rowMapperClass = CountRowMapper.class)
    Count countEntities();
}
