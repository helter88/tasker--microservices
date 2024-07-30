package com.tasker.issue.service.mapper;

import com.tasker.issue.model.Issue;
import com.tasker.issue.service.dto.IssueDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IssueMapper {
    Issue toEntity(IssueDto issueDto);
    IssueDto toDto(Issue issue);
    List<IssueDto> toDtoList(List<Issue> issues);
}
