package com.tasker.project.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "issue-manager", url = "http://localhost:8080/api" )
public interface IssueClient {

    @GetMapping("/data")
    String getIssueData();
}
