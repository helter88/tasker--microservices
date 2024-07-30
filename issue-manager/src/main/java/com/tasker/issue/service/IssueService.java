package com.tasker.issue.service;

import com.tasker.issue.model.Issue;
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
            var entity = issueRepository.findAll();
            return mapper.toDtoList(entity);
        }

        public void addIssue(IssueDto issueDto) {
            issueRepository.save(mapper.toEntity(issueDto));
        }

        public void updateIssue(IssueDto issueDto, UUID id) {
            var found = issueRepository.findById(id);
            found.ifPresentOrElse( issue -> {
                issue.setName(issueDto.name());
                issue.setDescription(issueDto.description());
                issueRepository.save(issue);
            }, () -> {
                throw new NoSuchElementException();
            });
        }

        public void deleteIssue(UUID id) {
            var found = issueRepository.findById(id);
            found.ifPresentOrElse(issue -> {
                issueRepository.deleteById(id);
            }, () -> {
                throw new NoSuchElementException();
            });
        }
}
