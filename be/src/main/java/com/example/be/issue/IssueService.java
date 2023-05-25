package com.example.be.issue;

import com.example.be.assignee.Assignee;
import com.example.be.issue.dto.*;
import com.example.be.label.LabelRepository;
import com.example.be.milestone.MilestoneRepository;
import com.example.be.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
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
        CountDTO countDTO = issueRepository.countEntities();
        AllEntitiesDTO allEntitiesDTO = gatherAllEntities();

        return new FeIssueResponseDTO(issues, countDTO, allEntitiesDTO.getAllLabels(), allEntitiesDTO.getAllMilestones(), allEntitiesDTO.getAllUsers());
    }

    public IosIssueResponseDTO makeIosIssueResponse(IssueSearchCondition issueSearchCondition) {
        Collection<Issue> issues = findIssues(issueSearchCondition);
        return new IosIssueResponseDTO(issues);
    }

    public AllEntitiesDTO gatherAllEntities() {
        return new AllEntitiesDTO(labelRepository.findAll(), milestoneRepository.findAll(), userRepository.findAll());
    }

    public int createIssue(IssueCreateFormDTO issueCreateFormDTO) {
        if (issueCreateFormDTO.getAssignees() != null) {
            issueCreateFormDTO.setAssignees(userRepository.validateNames(issueCreateFormDTO.getAssignees()));
        }

        if (issueCreateFormDTO.getLabelNames() != null) {
            issueCreateFormDTO.setLabelNames(labelRepository.validateNames(issueCreateFormDTO.getLabelNames()));
        }

        if (issueCreateFormDTO.getMilestoneName() != null) {
            issueCreateFormDTO.setMilestoneName(milestoneRepository.validateName(issueCreateFormDTO.getMilestoneName()));
        }

        return issueRepository.save(issueCreateFormDTO);
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
}
