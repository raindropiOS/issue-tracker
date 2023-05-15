package com.example.BE.issue;

import com.example.BE.issue.dto.FeIssueResponse;
import com.example.BE.issue.dto.IssueLabelMap;
import com.example.BE.label.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FeIssueService {

    private final IssueRepository issueRepository;

    @Autowired
    public FeIssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public FeIssueResponse makeFeIssueResponse() {
        List<Issue> issues = makeIssuesWithoutLabels();

        Map<Integer, List<Label>> issueWithLabels = issues.stream()
                .collect(Collectors.toMap(
                        Issue::getNumber,
                        Issue::getLabels
                ));


        System.out.println(issueWithLabels.keySet());

        return new FeIssueResponse(makeIssuesWithoutLabels(), null);
    }

    private List<Issue> makeIssuesWithoutLabels() {
        return issueRepository.findAllIssuesWithoutLabels();
    }

    private List<IssueLabelMap> makeIssueLabelMaps(Set<Integer> issueNumbers) {
        return issueRepository.findAllIssueLabelMap(issueNumbers);
    }

}
