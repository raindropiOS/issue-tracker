package com.example.be.issue;

import com.example.be.assignee.Assignee;
import com.example.be.issue.dto.*;
import com.example.be.label.LabelRepository;
import com.example.be.milestone.MilestoneRepository;
import com.example.be.user.User;
import com.example.be.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final LabelRepository labelRepository;
    private final UserRepository userRepository;
    private final MilestoneRepository milestoneRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository, LabelRepository labelRepository, UserRepository userRepository, MilestoneRepository milestoneRepository) {
        this.issueRepository = issueRepository;
        this.labelRepository = labelRepository;
        this.userRepository = userRepository;
        this.milestoneRepository = milestoneRepository;
    }

    public FeIssueResponseDTO makeFeIssueResponse(IssueSearchCondition issueSearchCondition) {
        Collection<Issue> issues = findIssues(issueSearchCondition);
        CountDTO countDTO = calculateCount();
        return new FeIssueResponseDTO(issues, countDTO);
    }

    public IosIssueResponseDTO makeIosIssueResponse(IssueSearchCondition issueSearchCondition) {
        Collection<Issue> issues = findIssues(issueSearchCondition);
        return new IosIssueResponseDTO(issues);
    }

    public Issue createIssue(User testUser, IssueCreateFormDTO issueCreateFormDTO) {
        Issue issue = new Issue(issueCreateFormDTO.getTitle(), issueCreateFormDTO.getContents(), testUser);

        if (issueCreateFormDTO.getLabelNames() != null) {
            issue.setLabels(labelRepository.findByNames(issueCreateFormDTO.getLabelNames()));
        }

        if (issueCreateFormDTO.getAssignees() != null) {
            issue.setAssignees(userRepository.findByNames(issueCreateFormDTO.getAssignees()));
        }

        if (issueCreateFormDTO.getMilestoneName() != null) {
            issue.setMilestone(milestoneRepository.findByName(issueCreateFormDTO.getMilestoneName()));
        }

        return null;
    }

    private Collection<Issue> findIssues(IssueSearchCondition issueSearchCondition) {
        List<Issue> issues = issueRepository.findIssuesWithoutLabelsBy(issueSearchCondition);

        if (issues.isEmpty()) {
            return Collections.emptyList();
        }

        Map<Integer, Issue> issueMap = issues.stream()
                .collect(Collectors.toMap(
                        Issue::getNumber,
                        Function.identity()
                ));

        List<IssueNumberWithLabelDTO> issueNumberWithLabelDTOs = issueRepository.findIssueLabelMapsBy(issueMap.keySet());
        issueNumberWithLabelDTOs.forEach(issueLabel -> issueMap.get(issueLabel.getIssueNumber()).add(issueLabel));

        List<Assignee> assignees = issueRepository.findAssigneesBy(issueMap.keySet());
        assignees.forEach(assignee -> issueMap.get(assignee.getIssueNumber()).add(assignee));

        return issueMap.values();
    }

    private CountDTO calculateCount() {
        return issueRepository.countEntities();
    }
}
