package com.example.BE.issue;

import com.example.BE.dto.show.issue.detailed.FeSimpleIssue;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IssueService {

    private final IssueRepository issueRepository;

    public IssueService (IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public ArrayList<FeSimpleIssue> findAllIssues() {
        List<FeSimpleIssue> feSimpleIssues = issueRepository.getFeSimpleIssues();
        HashMap<Integer, FeSimpleIssue> issueHashMap = new HashMap<>();

        for (FeSimpleIssue i : feSimpleIssues) {
            if (!issueHashMap.containsKey(i.getNumber())) {
                issueHashMap.put(i.getNumber(), i);
                continue;
            }
            FeSimpleIssue issue = issueHashMap.get(i.getNumber());
            issue.addLabel(i.getLabels().get(0));
        }
        return new ArrayList<>(issueHashMap.values());
    }

}
