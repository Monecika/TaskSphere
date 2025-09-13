package tasksphere.teams.domain.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tasksphere.teams.application.port.TeamManagementUseCase;
import tasksphere.teams.domain.model.TeamEntity;
import tasksphere.teams.infrastructure.persistance.TeamRepositoryJpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TeamManagementService implements TeamManagementUseCase {
    private final TeamRepositoryJpa teamRepository;


    @Override
    public TeamEntity createTeam(TeamEntity request) {
        return teamRepository.save(request);
    }

    @Override
    @Transactional
    public void deleteTeam(TeamEntity request) {
        teamRepository.deleteByName(request.getName());
    }

    @Override
    public Optional<TeamEntity> getTeamById(UUID teamId) {
        return teamRepository.findById(teamId);
    }

    @Override
    public TeamEntity updateTeam(TeamEntity request) {
        return teamRepository.save(request);
    }

    @Override
    public List<TeamEntity> getTeamsByIds(List<UUID> list) {
        return teamRepository.findTeamEntitiesByIdIn(list);
    }

}
