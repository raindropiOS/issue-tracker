package com.example.BE.issue;

import com.example.BE.issue.dto.IosIssueResponse;
import com.example.BE.issue.dto.IssueLabelMap;
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

    public IosIssueResponse makeIosIssueResponse() {
        List<Issue> issues = issueRepository.findAllIssuesWithoutLabels();
        List<Issue> issuesWithLabels = addLabelInfo(issues);
        return new IosIssueResponse(issuesWithLabels);
    }

    public IosIssueResponse findFilterIssues(boolean state) {
        List<Issue> issues = issueRepository.findFilterIssuesWithoutLabels(state);
        List<Issue> issuesWithLabels = addLabelInfo(issues);
        return new IosIssueResponse(issuesWithLabels);
    }

    private List<Issue> addLabelInfo(List<Issue> issues) {
        List<IssueLabelMap> issueLabelMaps = issueRepository.findAllIssueLabelMap();

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
        return new ArrayList<>(issueHashMap.values());
    }

}
