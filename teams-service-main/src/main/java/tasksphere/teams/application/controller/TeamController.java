package tasksphere.teams.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.apiservice.openapi.model.TeamRequest;
import project.apiservice.openapi.model.TeamResponse;
import tasksphere.teams.application.port.TeamManagementUseCase;
import tasksphere.teams.domain.model.TeamEntity;
import tasksphere.teams.domain.service.UserTeamsManagementService;
import tasksphere.teams.infrastructure.mapper.TeamMapper;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/teams")
@RestController
public class TeamController {
    private final TeamMapper mapper;
    private final TeamManagementUseCase teamManagement;
    private final UserTeamsManagementService userTeamsManagement;

    @PostMapping
    public ResponseEntity<TeamResponse> createTeam(@RequestBody TeamRequest request) {
        final TeamEntity entity = mapper.teamRequestToEntity(request);
        final TeamResponse response = mapper.teamEntityToResponse(teamManagement.createTeam(entity));

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public void deleteTeam(@RequestBody TeamRequest request) {
        final TeamEntity entity = mapper.teamRequestToEntity(request);

        teamManagement.deleteTeam(entity);
    }

    @GetMapping("/{teamId}")
    public ResponseEntity<TeamResponse> getTeamInfo(@PathVariable UUID teamId,
                                                    @RequestParam(value = "userId", required = false) UUID userId) {
        if (userId == null && userTeamsManagement.userRelatesToTeam(teamId,
                                                                    userId
        )) {
            return ResponseEntity.badRequest()
                    .body(null);
        }

        final TeamEntity entity = teamManagement.getTeamById(teamId)
                .orElseGet(null);
        final TeamResponse response = mapper.teamEntityToResponse(entity);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<TeamResponse> updateTeamInfo(@PathVariable UUID teamId,
                                                       @RequestBody TeamRequest request,
                                                       @RequestParam(value = "userId", required = false) UUID userId) {
        if (userId == null && userTeamsManagement.userRelatesToTeam(teamId,
                                                                    userId
        )) {
            return ResponseEntity.badRequest()
                    .body(null);
        }

        final TeamEntity entity = mapper.teamRequestToEntity(request);
        entity.setId(teamId);

        final TeamEntity savedEntity = teamManagement.updateTeam(entity);
        final TeamResponse response = mapper.teamEntityToResponse(savedEntity);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<List<UUID>> getUsers(@PathVariable UUID id) {
        return ResponseEntity.ok(userTeamsManagement.findUsersRelatedToTeam(id));
    }
}
