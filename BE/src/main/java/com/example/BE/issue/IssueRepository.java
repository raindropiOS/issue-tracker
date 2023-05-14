package com.example.BE.issue;

import com.example.BE.dto.show.issue.detailed.*;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


@Repository
public class IssueRepository {
    private final NamedParameterJdbcTemplate template;

    public IssueRepository (DataSource dataSource) {
        template = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<FeSimpleIssue> getFeSimpleIssues() {
        StringBuilder sb = new StringBuilder();
        sb.append("select i.number, i.title, i.state, i.created_date, m.name as milestone_name, u.nickname,").append(" \n");
        sb.append("(select image.url from IMAGE_FOR_USER image where image.USER_id = u.id) as imageUrl, l.name as label_name, l.background_color, l.text_color").append(" \n");
        sb.append("FROM ISSUE i").append(" \n");
        sb.append("inner join USER u on i.user_id = u.id").append(" \n");
        sb.append("left join ISSUE_LABEL_RELATION r on i.number = r.issue_number").append(" \n");
        sb.append("left join LABEL l on r.label_name = l.name").append(" \n");
        sb.append("left join MILESTONE m on i.milestone_name = m.name;");
        String sql = sb.toString();

        return template.query(sql, feSimpleIssueRowMapper);
    }

    public Integer getCountOpenedIssueState() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT count(*) as count").append(" \n");
        sb.append("FROM ISSUE").append(" \n");
        sb.append("WHERE state = 1");
        String sql = sb.toString();

        return template.queryForObject(sql,new HashMap<>(), Integer.class);
    }

    public Integer getCountCloseIssueState() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT count(*) as count").append(" \n");
        sb.append("FROM ISSUE").append(" \n");
        sb.append("WHERE state = 0;");
        String sql = sb.toString();

        return template.queryForObject(sql,new HashMap<>(), Integer.class);
    }

    public Integer getCountLabel() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT count(*) as count").append(" \n");
        sb.append("FROM LABEL;");
        String sql = sb.toString();

        return template.queryForObject(sql,new HashMap<>(), Integer.class);
    }

    public Integer getCountMilestone() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT count(*) as count").append(" \n");
        sb.append("FROM MILESTONE;");
        String sql = sb.toString();

        return template.queryForObject(sql,new HashMap<>(), Integer.class);
    }
    private RowMapper<FeSimpleIssue> feSimpleIssueRowMapper = (rs, rowNum) -> {
        FeSimpleIssue issue = new FeSimpleIssue();
        int issueNumber = rs.getInt("number");
        String issueTitle = rs.getString("title");
        boolean issueState = rs.getBoolean("state");
        LocalDateTime issueCreatedDate = rs.getTimestamp("created_date").toLocalDateTime();
        String milestoneName = rs.getString("milestone_name");
        String userNickName = rs.getString("nickname");
        String userImageUrl = rs.getString("imageUrl");
        String labelName = rs.getString("label_name");
        String labelBackgroundColor = rs.getString("background_color");
        String labelTextColor = rs.getString("text_color");

        SimpleAuthor simpleAuthor = new SimpleAuthor(userNickName, userImageUrl);
        SimpleMilestone simpleMilestone = new SimpleMilestone(milestoneName);
        SimpleLabel simpleLabel = new SimpleLabel(labelName, labelBackgroundColor, labelTextColor);

        issue.setNumber(issueNumber);
        issue.setTitle(issueTitle);
        issue.setCreatedDate(issueCreatedDate);
        issue.setState(issueState);
        issue.setAuthor(simpleAuthor);
        issue.addLabel(simpleLabel);
        issue.setMilestone(simpleMilestone);

        return issue;
    };



}
