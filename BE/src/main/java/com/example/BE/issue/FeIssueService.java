package com.example.BE.issue;

import com.example.BE.issue.dto.Count;
import com.example.BE.issue.dto.FeIssueResponse;
import com.example.BE.issue.dto.IssueLabelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class FeIssueService {

    private final IssueRepository issueRepository;

    @Autowired
    public FeIssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public FeIssueResponse makeFeIssueResponse() {
        Collection<Issue> issues = mapIssueWithLabels();
        Count count = calculateCount();
        return new FeIssueResponse(issues, count);
    }

    private Collection<Issue> mapIssueWithLabels() {
        List<Issue> issues = issueRepository.findAllIssuesWithoutLabels();

        Map<Integer, Issue> issueMap = issues.stream()
                .collect(Collectors.toMap(
                        Issue::getNumber,
                        Function.identity()
                ));

        List<IssueLabelMap> issueLabelMaps = issueRepository.findAllIssueLabelMap(issueMap.keySet());
        issueLabelMaps.forEach(issueLabel -> issueMap.get(issueLabel.getIssueNumber()).add(issueLabel));

        return issueMap.values();
    }

    private Count calculateCount() {
        return issueRepository.countEntities();
    }
}