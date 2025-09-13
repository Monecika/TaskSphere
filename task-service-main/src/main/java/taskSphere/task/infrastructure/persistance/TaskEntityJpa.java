package taskSphere.task.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taskSphere.task.domain.model.TaskEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskEntityJpa extends JpaRepository<TaskEntity, UUID> {
    List<TaskEntity> findTaskEntitiesByAssignedUserId(UUID assignedUserId);

    List<TaskEntity> findTaskEntityByTeamId(UUID teamId);

    TaskEntity getTaskEntityById(UUID id);
}
