package com.example.BE.issue.mapper;

import com.example.BE.issue.dto.Count;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountRowMapper implements RowMapper<Count> {
    @Override
    public Count mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new BeanPropertyRowMapper<>(Count.class).mapRow(rs, rowNum);
    }
}
