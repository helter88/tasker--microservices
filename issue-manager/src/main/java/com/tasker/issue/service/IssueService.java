package com.tasker.issue.service;

import com.tasker.issue.repository.IssueRepository;
import com.tasker.issue.service.dto.IssueDto;
import com.tasker.issue.service.mapper.IssueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IssueService {
    private final IssueRepository issueRepository;
    private final IssueMapper mapper;

    public List<IssueDto> getIssues() {
        return mapper.toDtoList(issueRepository.findAll());
    }

    public void addIssue(IssueDto issueDto) {
        issueRepository.save(mapper.toEntity(issueDto));
    }

    public void updateIssue(IssueDto issueDto) {
        issueRepository.findById(issueDto.uuid()).ifPresentOrElse(issue -> {
            issue.setName(issueDto.name());
            issue.setDescription(issueDto.description());
            issueRepository.save(issue);
        }, () -> {
            throw new NoSuchElementException(); //TODO: task, create handlers for the Exceptions deadline: 30.07.2024
        });
    }

    public void deleteIssue(UUID id) {
        issueRepository.findById(id).ifPresentOrElse(issue -> issueRepository.deleteById(id), () -> {
            throw new NoSuchElementException();
        });
    }
}
