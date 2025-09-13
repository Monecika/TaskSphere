package tasksphere.teams.application.port;

import tasksphere.teams.domain.model.TeamEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamManagementUseCase {
    TeamEntity createTeam(TeamEntity request);

    void deleteTeam(TeamEntity request);

    Optional<TeamEntity> getTeamById(UUID teamId);

    TeamEntity updateTeam(TeamEntity request);

    List<TeamEntity> getTeamsByIds(List<UUID> list);
}
