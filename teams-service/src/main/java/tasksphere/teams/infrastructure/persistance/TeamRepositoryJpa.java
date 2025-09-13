package tasksphere.teams.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tasksphere.teams.domain.model.TeamEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeamRepositoryJpa extends JpaRepository<TeamEntity, UUID> {
    void deleteByName(String name);

    List<TeamEntity> findTeamEntitiesByIdIn(List<UUID> ids);
}
