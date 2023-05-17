package com.example.BE.issue;

import java.util.List;

public interface IssueRepositoryCustom {

    List<Issue> findAllIssuesWithoutLabels();
}
