package taskSphere.task.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import taskSphere.task.application.port.TaskManagementUseCase;
import taskSphere.task.domain.model.TaskEntity;
import taskSphere.task.infrastructure.persistance.TaskEntityJpa;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TaskManagementService implements TaskManagementUseCase {
    private final TaskEntityJpa repository;

    @Override
    public TaskEntity save(TaskEntity request) {
        return repository.save(request);
    }

    @Override
    public List<TaskEntity> getUserTasks(UUID userId) {
        return repository.findTaskEntitiesByAssignedUserId(userId);
    }

    @Override
    public List<TaskEntity> getTeamTasks(UUID teamId) {
        return repository.findTaskEntityByTeamId(teamId);
    }

    @Override
    public TaskEntity getTaskById(UUID id) {
        return repository.getTaskEntityById(id);
    }

    @Override
    public TaskEntity updateTaskStatus(TaskEntity entity) {
        return repository.save(entity);
    }

    @Override
    public TaskEntity assignTaskToUser(TaskEntity entity) {
        return repository.save(entity);
    }
}
