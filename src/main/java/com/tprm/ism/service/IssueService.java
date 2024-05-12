package com.tprm.ism.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tprm.ism.constants.IssueConstants;
import com.tprm.ism.dto.IssueDTO;
import com.tprm.ism.entity.Issue;
import com.tprm.ism.exception.IssueCreationFailureException;
import com.tprm.ism.repository.IssueRepository;

@Service
public class IssueService {
    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private ModelMapper modelMapper;

    public IssueDTO createIssue(IssueDTO issueDTO) {
        Issue createdIssue = issueRepository.save(convertToIssue(issueDTO));

        return convertToIssueDTO(createdIssue);

    }

    public List<IssueDTO> getAllIssues() {
        return issueRepository.findAll().stream().map(this::convertToIssueDTO).collect(Collectors.toList());
    }

    private Issue convertToIssue(IssueDTO issueDTO) {
        return modelMapper.map(issueDTO, Issue.class);
    }

    private IssueDTO convertToIssueDTO(Issue issue) {
        return modelMapper.map(issue, IssueDTO.class);
    }

    public List<IssueDTO> linkIssuestoIssueSource(List<IssueDTO> issueDTOs) throws IssueCreationFailureException {
        try {
            return issueRepository.saveAll(issueDTOs.stream().map(this::convertToIssue).collect(Collectors.toList()))
                    .stream()
                    .map(this::convertToIssueDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new IssueCreationFailureException(IssueConstants.ISSUES_CREATION_FAILED);
        }
    }

    public List<IssueDTO> getAllIssuesLinkedToIssueSourceObjectId(String issueSourceObjectId)
            throws IssuesFetchException {
        try {
            List<Issue> issues = issueRepository.findByIssueSourceObjectId(issueSourceObjectId);
            return issues.stream()
                    .map(this::convertToIssueDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new IssuesFetchException(IssueConstants.ISSUES_FETCH_FAILED);
        }
    }
}
