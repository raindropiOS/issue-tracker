package com.example.BE.issue;

import com.example.BE.dto.show.issue.detailed.FeSimpleIssue;
import com.example.BE.dto.show.issue.detailed.SimpleLabel;
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
            issueHashMap.put(i.getNumber(), i);
        }

        List<SimpleLabel> simpleLabels = issueRepository.getSimpleLabels();
        for (SimpleLabel l : simpleLabels) {
            FeSimpleIssue feSimpleIssue = issueHashMap.get(l.getIssueNumber());
            feSimpleIssue.addLabel(l);
        }

        return new ArrayList<>(issueHashMap.values());
    }

}
