package com.example.BE.issue;

import com.example.BE.issue.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
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

    public FeIssueResponse makeFeIssueResponse(IssueSearchCondition issueSearchCondition) {
        Collection<Issue> issues = mapIssueWithLabels(issueSearchCondition);
        Count count = calculateCount();
        return new FeIssueResponse(issues, count);
    }

    private Collection<Issue> mapIssueWithLabels(IssueSearchCondition issueSearchCondition) {
        List<Issue> issues = issueRepository.findAllIssuesWithoutLabelsBy(issueSearchCondition);

        if (issues.isEmpty()) {
            return Collections.emptyList();
        }

        Map<Integer, Issue> issueMap = issues.stream()
                .collect(Collectors.toMap(
                        Issue::getNumber,
                        Function.identity()
                ));

        List<IssueLabelMap> issueLabelMaps = issueRepository.findAllIssueLabelMap(issueMap.keySet());
        issueLabelMaps.forEach(issueLabel -> issueMap.get(issueLabel.getIssueNumber()).add(issueLabel));

        List<Assignee> assignees = issueRepository.findAssigneesIn(issueMap.keySet());
        assignees.forEach(assignee -> issueMap.get(assignee.getIssueNumber()).add(assignee));

        return issueMap.values();
    }

    private Count calculateCount() {
        return issueRepository.countEntities();
    }
}
