package taskSphere.task.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.taskSphere.openapi.model.TaskRequest;
import project.taskSphere.openapi.model.TaskResponse;
import project.taskSphere.openapi.model.UpdateTaskAssignee;
import project.taskSphere.openapi.model.UpdateTaskStatus;
import taskSphere.task.application.port.TaskManagementUseCase;
import taskSphere.task.domain.enums.TaskStatus;
import taskSphere.task.domain.model.TaskEntity;
import taskSphere.task.infrastructure.mapper.TaskMapper;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskManagementUseCase taskManagementService;
    private final TaskMapper mapper;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest request) {
        final TaskEntity saved = taskManagementService.save(mapper.taskRequestToEntity(request));
        final TaskResponse response = mapper.taskEntityToResponse(saved);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/assigned/{userId}")
    public ResponseEntity<List<TaskResponse>> getUserTasks(@PathVariable UUID userId) {
        final List<TaskEntity> entityList = taskManagementService.getUserTasks(userId);
        final List<TaskResponse> responseList = mapper.taskEntityListToResponseList(entityList);

        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<TaskResponse>> getTeamTasks(@PathVariable UUID teamId) {
        final List<TaskEntity> entityList = taskManagementService.getTeamTasks(teamId);
        final List<TaskResponse> responseList = mapper.taskEntityListToResponseList(entityList);

        return ResponseEntity.ok(responseList);
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable UUID taskId,
                                                   @RequestBody UpdateTaskStatus request,
                                                   @RequestParam UUID userId) throws Exception {
        final TaskEntity entity = taskManagementService.getTaskById(taskId);
        if (!entity.getAssignedUserId()
                .equals(userId)) {
            throw new Exception();
        }

        entity.setStatus(TaskStatus.valueOf(request.getStatus()
                                                    .name()));

        final TaskEntity updated = taskManagementService.updateTaskStatus(entity);
        final TaskResponse response = mapper.taskEntityToResponse(updated);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{taskId}/assign")
    public ResponseEntity<TaskResponse> updateTaskAssign(@PathVariable UUID taskId,
                                                         @RequestBody UpdateTaskAssignee request) {
        final TaskEntity entity = taskManagementService.getTaskById(taskId);
        entity.setAssignedUserId(request.getUserId());
        final TaskEntity updated = taskManagementService.assignTaskToUser(entity);

        final TaskResponse response = mapper.taskEntityToResponse(updated);

        return ResponseEntity.ok(response);
    }
}

