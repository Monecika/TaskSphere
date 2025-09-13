package tasksphere.teams.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.apiservice.openapi.model.TeamRequest;
import project.apiservice.openapi.model.TeamResponse;
import tasksphere.teams.domain.model.TeamEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeamMapper {
    TeamEntity teamRequestToEntity(TeamRequest request);

    @Mapping(target = "createdAt", expression = "java(java.time.OffsetDateTime.now())")
    TeamResponse teamEntityToResponse(TeamEntity entity);

    List<TeamResponse> teamEntityListToResponseList(List<TeamEntity> list);
}
