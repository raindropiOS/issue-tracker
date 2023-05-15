package com.example.BE.issue;


import com.example.BE.issue.dto.FeIssueResponse;
import com.example.BE.issue.dto.IssueLabelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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

        HashMap<Integer, Issue> issueHashMap = new HashMap<>();
        for (Issue issue : issues) {
            issueHashMap.put(issue.getNumber(), issue);
        }

        for (IssueLabelMap issueLabelMap : issueLabelMaps) {
            Issue issue = issueHashMap.get(issueLabelMap.getIssueNumber());
            issue.addLabel(issueLabelMap.getLabel());
        }
        return new ArrayList<>(issueHashMap.values());
    }
}
