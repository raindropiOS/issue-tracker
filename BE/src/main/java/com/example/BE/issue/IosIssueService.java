package com.example.BE.issue;

import com.example.BE.issue.dto.Count;
import com.example.BE.issue.dto.IosIssueResponse;
import com.example.BE.issue.dto.IssueLabelMap;
import com.example.BE.issue.dto.IssueSearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class IosIssueService {
    private final IssueRepository issueRepository;

    @Autowired
    public IosIssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public IosIssueResponse findIssuesBy(IssueSearchCondition issueSearchCondition) {
        List<Issue> issues = issueRepository.findIssueWithoutLabelsBy(issueSearchCondition);
        List<IssueLabelMap> issueLabelMaps = issueRepository.findIssueLabelMapBy(issues);

        HashMap<Integer, Issue> issueHashMap = new HashMap<>();
        for (Issue issue : issues) {
            issueHashMap.put(issue.getNumber(), issue);
        }
        for (IssueLabelMap issueLabelMap : issueLabelMaps) {
            Issue issue = issueHashMap.get(issueLabelMap.getIssueNumber());
            if (issue != null) {
                issue.addLabel(issueLabelMap.getLabel());
            }
        }

        return new IosIssueResponse(new ArrayList<>(issueHashMap.values()));
    }

}
