package com.example.BE.issue;

import com.example.BE.issue.dto.FeIssueResponse;
import org.springframework.stereotype.Service;

@Service
public class IssueService {
    public FeIssueResponse makeFeIssueResponse() {
        return new FeIssueResponse(null, null);
    }
}
