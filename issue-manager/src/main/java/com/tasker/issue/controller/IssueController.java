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

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;

    @GetMapping
    public List<IssueDto> getIssues() {
        return issueService.getIssues();
    }

    @GetMapping("/data")
    public ResponseEntity<String> getData() {
        return ResponseEntity.ok("Data from Issue");
    }

    @PostMapping
    public void addIssue(@RequestBody IssueDto issueDto) {
        issueService.addIssue(issueDto);
    }

    @PutMapping("/{id}")
    public void updateIssue(@RequestBody IssueDto issueDto, @PathVariable UUID id)  {
        issueService.updateIssue(issueDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteIssue(@PathVariable UUID id) {
        issueService.deleteIssue(id);
    }
}
