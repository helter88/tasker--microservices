package com.tasker.issue.controller;

import com.tasker.issue.model.Issue;
import com.tasker.issue.service.IssueService;
import com.tasker.issue.service.dto.IssueDto;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

//TODO: remove issues
@RestController
@RequiredArgsConstructor
public class IssueController implements IssueControllerApi { //TODO: interface with REST-related annotations (being consistent with API-first approach?)
    private final IssueService issueService;

    public List<IssueDto> getIssues() {
        return issueService.getIssues();
    }

    public ResponseEntity<String> getData() {
        return ResponseEntity.ok("Data from Issue");
    }

    public void addIssue(IssueDto issueDto) {
        issueService.addIssue(issueDto);
    }

    @PutMapping
    public void updateIssue(@RequestBody IssueDto issueDto) {
        issueService.updateIssue(issueDto); //TODO: change releted to uuid, get the ID from dto
    }

    @DeleteMapping("/{id}")
    public void deleteIssue(@PathVariable UUID id) {
        issueService.deleteIssue(id);
    }
}
