package com.example.BE.mapper;

import com.example.BE.issue.dto.IssueLabelMap;
import com.example.BE.label.Label;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IssueLabelMapRowMapper implements RowMapper<IssueLabelMap> {

    @Override
    public IssueLabelMap mapRow(ResultSet rs, int rowNum) throws SQLException {
        Label label = new BeanPropertyRowMapper<>(Label.class).mapRow(rs, rowNum);
        IssueLabelMap issueLabelMap = new BeanPropertyRowMapper<>(IssueLabelMap.class).mapRow(rs, rowNum);
        issueLabelMap.setLabel(label);

        return issueLabelMap;
    }
}
