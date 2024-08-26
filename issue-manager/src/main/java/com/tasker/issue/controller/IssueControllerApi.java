package com.tasker.issue.controller;

import com.tasker.issue.service.dto.IssueDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/api") //TODO: /issues
public interface IssueControllerApi {
    @GetMapping
    List<IssueDto> getIssues();

    @GetMapping("/data") //TODO: why??? tell me why? I'm bleeding....... :((((((((((((((((((((((((((((( DEAD
    ResponseEntity<String> getData();

    @PostMapping //TODO: save!!!, add is relating to the operations over the list :((((( R.I.P.
    void addIssue(@RequestBody IssueDto issueDto);
}
