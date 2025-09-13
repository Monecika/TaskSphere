package taskSphere.task.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import project.taskSphere.openapi.model.TaskRequest;
import project.taskSphere.openapi.model.TaskResponse;
import taskSphere.task.domain.model.TaskEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.OffsetDateTime.now())")
    TaskResponse taskEntityToResponse(TaskEntity entity);
    TaskEntity taskRequestToEntity(TaskRequest request);
    List<TaskResponse> taskEntityListToResponseList(List<TaskEntity> entityList);
}
