package taskSphere.task.application.port;

import taskSphere.task.domain.model.TaskEntity;

import java.util.List;
import java.util.UUID;

public interface TaskManagementUseCase {
    TaskEntity save(TaskEntity request);

    List<TaskEntity> getUserTasks(UUID userId);

    List<TaskEntity> getTeamTasks(UUID teamId);

    TaskEntity getTaskById(UUID id);

    TaskEntity updateTaskStatus(TaskEntity entity);

    TaskEntity assignTaskToUser(TaskEntity entity);
}
