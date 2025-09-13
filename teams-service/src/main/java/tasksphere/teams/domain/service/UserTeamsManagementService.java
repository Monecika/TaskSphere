package tasksphere.teams.domain.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tasksphere.teams.application.port.UserTeamsManagementUserCase;
import tasksphere.teams.domain.model.UserTeamsEntity;
import tasksphere.teams.infrastructure.persistance.UserTeamsRepositoryJpa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserTeamsManagementService implements UserTeamsManagementUserCase {
    private final UserTeamsRepositoryJpa userTeamsRepository;

    @Override
    public boolean userRelatesToTeam(UUID teamId,
                                     UUID userId) {
        Optional<UserTeamsEntity> entity = userTeamsRepository.findUserTeamsEntityByTeamIdAndUserId(teamId,
                                                                                                    userId
        );
        return entity.isPresent();
    }

    @Override
    public List<UUID> findUsersRelatedToTeam(UUID teamId) {

        return userTeamsRepository.findUsersRelatedToTeam(teamId);
    }

    @Override
    public void addUserToTeam(UserTeamsEntity entity) {
        if (!userRelatesToTeam(entity.getTeamId(),
                               entity.getUserId()
        )) {
            userTeamsRepository.save(entity);
        }
    }

    @Transactional
    @Override
    public void deleteUserFromTeam(UUID teamId,
                                   UUID userId) {
        userTeamsRepository.deleteUserTeamsEntityByTeamIdAndUserId(teamId,
                                                                   userId
        );
    }

    @Override
    public List<UUID> getUsersFromTeam(UUID id) {
        return userTeamsRepository.findUsersFromTeam(id);
    }

    @Override
    public List<UUID> findTeamsForUser(UUID id) {
        return userTeamsRepository.findTeamsForUser(id);
    }
}
