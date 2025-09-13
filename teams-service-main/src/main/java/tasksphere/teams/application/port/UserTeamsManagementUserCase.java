package tasksphere.teams.application.port;

import tasksphere.teams.domain.model.UserTeamsEntity;

import java.util.List;
import java.util.UUID;

public interface UserTeamsManagementUserCase {
    boolean userRelatesToTeam(UUID teamId,
                              UUID userId);

    List<UUID> findUsersRelatedToTeam(UUID teamId);

    void addUserToTeam(UserTeamsEntity entity);

    void deleteUserFromTeam(UUID teamId,
                            UUID userId);

    List<UUID> getUsersFromTeam(UUID id);

    List<UUID> findTeamsForUser(UUID id);
}
