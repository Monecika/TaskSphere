package tasksphere.teams.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tasksphere.teams.domain.model.UserTeamsEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserTeamsRepositoryJpa extends JpaRepository<UserTeamsEntity, UUID> {
    Optional<UserTeamsEntity> findUserTeamsEntityByTeamIdAndUserId(UUID teamId,
                                                                   UUID userId);

    @Query("SELECT u.userId FROM UserTeamsEntity u WHERE u.teamId = :teamId")
    List<UUID> findUsersRelatedToTeam(@Param("teamId") UUID teamId);

    void deleteUserTeamsEntityByTeamIdAndUserId(UUID teamId,
                                                UUID userId);

    @Query("SELECT u.userId FROM UserTeamsEntity u WHERE u.teamId = :teamId")
    List<UUID> findUsersFromTeam(UUID teamId);

    @Query("SELECT u.teamId FROM UserTeamsEntity u WHERE u.userId = :userId")
    List<UUID> findTeamsForUser(UUID userId);
}
