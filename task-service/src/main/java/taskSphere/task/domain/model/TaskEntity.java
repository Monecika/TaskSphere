package taskSphere.task.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import taskSphere.task.domain.enums.TaskStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "tasks")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    private UUID assignedUserId;
    private UUID teamId;
    private UUID createdBy;
    private OffsetDateTime createdAt;
}
