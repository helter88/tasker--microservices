package com.tasker.issue.repository;

import com.tasker.issue.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface IssueRepository extends JpaRepository<Issue, UUID> {
}
