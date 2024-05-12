package com.tprm.ism.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tprm.ism.dto.IssueDTO;
import com.tprm.ism.exception.IssueCreationFailureException;
import com.tprm.ism.service.IssueService;
import com.tprm.ism.service.IssuesFetchException;

@RestController
@RequestMapping(path = "/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping
    public ResponseEntity<IssueDTO> createIssue(@RequestBody IssueDTO issueDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(issueService.createIssue(issueDTO));
    }

    @GetMapping
    public ResponseEntity<List<IssueDTO>> getAllIssues() {
        return ResponseEntity.status(HttpStatus.OK).body(issueService.getAllIssues());
    }

    @PostMapping("/external-sources")
    public ResponseEntity<List<IssueDTO>> linkIssuesToSource(@RequestBody List<IssueDTO> issueDTOs)
            throws IssueCreationFailureException {
        return ResponseEntity.status(HttpStatus.CREATED).body(issueService.linkIssuestoIssueSource(issueDTOs));
    }

    @GetMapping("/external-sources")
    public ResponseEntity<List<IssueDTO>> getAllLinkedIssuesByIssueSourceObjectId(
            @RequestParam String issueSourceObjectId)
            throws IssuesFetchException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(issueService.getAllIssuesLinkedToIssueSourceObjectId(issueSourceObjectId));
    }
}
