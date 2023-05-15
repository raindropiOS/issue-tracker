package com.example.BE.issue;

import com.example.BE.issue.dto.FeIssueResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

    private IssueRepository issueRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public FeIssueResponse makeFeIssuesResponse() {
        return new FeIssueResponse(makeCompleteIssues(), issueRepository.countEntities());
    }

    private List<Issue> makeCompleteIssues() {
        List<Issue> issues = issueRepository.findAllIssuesWithoutLabels();
        List<IssueLabelMap> issueLabelMaps = issueRepository.findAllIssueLabelMap();

        System.out.println(issues);

        issues.forEach(issue -> {
            issueLabelMaps.stream()
                    .filter(map -> map.getIssueNumber() == issue.getNumber())
                    .forEach(map -> issue.addLabel(map.getLabel()));
        });

        System.out.println(issues);
        return issues;
    }
}
