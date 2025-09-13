package tasksphere.teams.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.apiservice.openapi.model.TeamResponse;
import tasksphere.teams.application.port.TeamManagementUseCase;
import tasksphere.teams.application.port.UserTeamsManagementUserCase;
import tasksphere.teams.domain.model.TeamEntity;
import tasksphere.teams.domain.model.UserTeamsEntity;
import tasksphere.teams.infrastructure.mapper.TeamMapper;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user-teams")
public class UserTeamsController {
    private final UserTeamsManagementUserCase userTeamsService;
    private final TeamManagementUseCase teamService;
    private final TeamMapper teamMapper;

    @PostMapping("/{teamId}/user/{userId}")
    public void addUser(@PathVariable UUID teamId,
                        @PathVariable UUID userId) {
        final UserTeamsEntity entity = UserTeamsEntity.builder()
                .teamId(teamId)
                .userId(userId)
                .build();

        userTeamsService.addUserToTeam(entity);
    }

    @DeleteMapping("/{teamId}/user/{userId}")
    public void deleteUser(@PathVariable UUID teamId,
                           @PathVariable UUID userId) {
        userTeamsService.deleteUserFromTeam(teamId,
                                            userId
        );
    }

    @GetMapping("/{teamId}/users")
    public ResponseEntity<List<UUID>> listTeamMembers(@PathVariable UUID teamId) {
        return ResponseEntity.ok(userTeamsService.getUsersFromTeam(teamId));
    }

    @GetMapping("/{userId}/teams")
    public ResponseEntity<List<TeamResponse>> listTeams(@PathVariable UUID userId) {
        final List<UUID> teamsList = userTeamsService.findTeamsForUser(userId);
        final List<TeamEntity> teamEntities = teamService.getTeamsByIds(teamsList);

        final List<TeamResponse> response = teamMapper.teamEntityListToResponseList(teamEntities);

        return ResponseEntity.ok(response);
    }
}
